/**
 * Copyright 2014 A-Team Games
 *
 * This file is part of ateam-tanks.
 *
 *    ateam-tanks is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    ateam-tanks is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with ateam-tanks.  If not, see <http://www.gnu.org/licenses/>.
 */

package gameinterface;

import game.*;
import event.*;
import network.*;

import java.util.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Point;

import java.io.Console;

public class DemoPanel extends JPanel implements DisplaysGame, GetsOrders, MouseListener{
    /**
     * 0: display
     * 1: give order
     * 2: announce winner
     */


    Rectangle2D.Double gameViewRect;

    int state;
    SpriteList sprites;
    OrderQueue queue=null;
    UnitModel model=null;
    Object queueLock=new Object();

    SimpleTank mainTank=null;
    OrderQueue orderQueue=null;

    String winnerName=null;

    ArrayList<OrderQueue> orders = new ArrayList<OrderQueue>();
    ArrayList<UnitModel> models = new ArrayList<UnitModel>();

    @Override
    public boolean initializeDisplay ( int mapSize ){
        gameViewRect=new Rectangle2D.Double(-mapSize,-mapSize,mapSize*2,mapSize*2);
        state=0;
        sprites = new SpriteList();
        this.addMouseListener(this);
        //this.addKeyListener(this);
        return true;
    }
    @Override
    public void cleanUpAndDestroyDisplay (){
        return;
    }
    @Override
    public void show (SpriteList s){
        this.sprites = s;
        repaint();
        try {
        Thread.sleep(60);
        } catch (InterruptedException e) {
            // ..
        }
        //for ( Sprite sprite : sprites.getSprites() )
        //{
        //    System.out.println ( sprite.getPosition().toString() );
        //}
    }

    @Override
    public ArrayList<OrderQueue> askForOrders (SpriteList s, int id, String playerName){
        this.sprites = s;
        this.models = new ArrayList<UnitModel>();
        this.orders = new ArrayList<OrderQueue>();
        this.repaint();
        for (Sprite sprite : sprites.getOwnedBy(id))
        {
            this.queue = new OrderQueue(sprites.getFramesPerTurn(), sprite.uid());
            this.model = new UnitModel(sprite);
            this.models.add(this.model);
            this.orders.add(this.queue);
            boolean keepgoing = true;
            int frames = 0;
            int frameLimit = sprites.getFramesPerTurn();
            Console in = System.console();
            System.out.println ();
            System.out.println ();
            System.out.println ( playerName + ", please allocate orders to your tank." );
            while ( keepgoing && frames < frameLimit)
            {
                try{
                    queueLock.wait();
                }catch(Exception ex){
                    keepgoing=false;
                }
                this.repaint ();
            }
            this.model=null;
            System.out.println();
            System.out.println();
            System.out.println();
            this.repaint ();
        }
        ArrayList<OrderQueue> output = new ArrayList<OrderQueue>(this.orders);
        this.orders = new ArrayList<OrderQueue>();
        this.models = new ArrayList<UnitModel>();
        this.repaint();
        return output;
    }

    public AffineTransform getTransform(){
        Rectangle2D.Double rect1=gameViewRect;
        Rectangle2D.Double rect2=new Rectangle2D.Double(0,0,getWidth(),getHeight());
        double rr=0;
        double rx=rect1.width/rect2.width;
        double ry=rect1.height/rect2.height;
        if(rx>ry){
            rr=rx; 
            rect2.y+=(rect2.height-rect1.height/rr)/2; 
            rect2.height=rect1.height/rr; 
        }else{ 
            rr=ry; 
            rect2.x+=(rect2.width-rect1.width/rr)/2; 
            rect2.width=rect1.width/rr; 
        } 
        double dx=-rect1.x/rr+rect2.x;
        double dy=(rect1.y+rect1.height)/rr+rect2.y;
        return new AffineTransform(1/rr,0,0,-1/rr,dx,dy);
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        if(state==2){
            g2.drawString(winnerName,100,100);
            return;
        }
        g2.setTransform(getTransform());
        //fill background
        g2.setColor(Color.black);
        g2.fill(gameViewRect);
        for(Sprite sprite:sprites.getSprites()){
            sprite.paint(g2);
            //System.out.println ( sprite.getClass().getName() + " painted!!!" );
        }
        if(state==1){
            //mainTank.paint(g2);
        }
        for (int x = 0; x < orders.size(); x++)
        {
            UnitModel m = new UnitModel(models.get(x));
            orders.get(x).walkModel ( m, g2 );
        }
    }
    public void mouseClicked(MouseEvent e){
        if(this.model==null){
            return;
        }
        Point _p=e.getPoint();
        Point2D.Double p1=new Point2D.Double(_p.x,_p.y);
        Point2D.Double p2=new Point2D.Double();
        try{
            getTransform().inverseTransform(p1,p2);
        }catch(Exception ex){
            return;
        }
        Vector3D position = this.model.getPosition();
        p1.setLocation(p2.x-position.getX(),p2.y-position.getY());
        double theta2=Math.atan2(p1.x,p1.y)*180/Math.PI;
        if(e.getButton()==MouseEvent.BUTTON1){
          double theta1=this.model.getDirection().getTheta();
          double dt=theta2-theta1;
          if(dt>180){
            dt-=360;
          }else if(dt<-180){
            dt+=360;
          }
          dt=(int)(dt/this.model.getHandling()+0.5);
          if(dt>0){
            queue.add(new TurnOrder((int)dt,1));
          }else{
            queue.add(new TurnOrder((int)-dt,-1));
          }
          dt=Math.hypot(p1.x,p1.y);
          dt=(int)(dt/this.model.getSpeed()+0.5);
          queue.add(new MoveOrder((int)dt,1));
          queueLock.notify();
        }else if(e.getButton()==MouseEvent.BUTTON3){
          queue.add(new ShootOrder(theta2));
          queueLock.notify();
        }
        
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
}
