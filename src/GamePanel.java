
import java.util.*;
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.*;
import java.awt.Color;
import java.awt.event.*;

public class GamePanel extends JPanel implements InterfaceWithGame, KeyListener{
    /**
     * 0: display
     * 1: give order
     * 2: announce winner
     */
    int state;

    List<Sprite> sprites;
    Rectangle2D.Double gameViewRect;
    Game game;

    SimpleTank mainTank=null;
    int frameLimit=-1;
    OrderQueue orderQueue=null;

    String winnerName=null;
    @Override
    public boolean initializeDisplay ( ArrayList<Sprite> sprites, int mapSize ){
        this.sprites=sprites;
        gameViewRect=new Rectangle2D.Double(-mapSize,-mapSize,mapSize*2,mapSize*2);
        state=0;
        this.addKeyListener(this);
        return true;
    }
    @Override
    public void cleanUpAndDestroyDisplay (){
        return;
    }
    @Override
    public void updateDisplay (){
        repaint();
        for ( Sprite sprite : sprites )
        {
            System.out.println ( sprite.getPosition().toString() );
        }
    }
    @Override
    public OrderQueue askForOrders ( int frameLimit, SimpleTank tank){
        /*
        this.mainTank=tank;
        this.frameLimit=frameLimit;
        this.orderQueue=new OrderQueue(frameLimit);
        repaint();
        try{
            this.mainTank.wait();
        }catch(Exception e){
            //TODO
        }
        this.mainTank=null;
        this.state=0;
        this.frameLimit=-1;
        OrderQueue f=this.orderQueue;
        this.orderQueue=null;
        */

        OrderQueue q = new OrderQueue();
        q.add ( new MoveOrder ( 10, 1 ) );

        return q;
    }
    @Override
    public void announceWinner ( String winnerName ){
        state=2;
        this.winnerName=winnerName;
    }

    public void updateTransform(Graphics2D g2){
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
        g2.setTransform(new AffineTransform(1/rr,0,0,-1/rr,dx,dy));
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        if(state==2){
            g2.drawString(winnerName,100,100);
            return;
        }
        updateTransform(g2);
        //fill background
        g2.setColor(Color.black);
        g2.fill(gameViewRect);
        for(Sprite sprite:sprites){
            sprite.paint(g2);
        }
        if(state==1){
            mainTank.paint(g2);
        }
    }
    @Override
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){
        if(state!=1){
            return;
        }
        Order order=null;
        switch(e.getKeyCode()){
        case KeyEvent.VK_UP:
            order=new MoveOrder(1, 1);
        break;
        case KeyEvent.VK_DOWN:
            order=new MoveOrder(1,-1);
            
        break;
        case KeyEvent.VK_RIGHT:
            order=new TurnOrder(1,-1);
        break;
        case KeyEvent.VK_LEFT:
            order=new TurnOrder(1, 1);
        break;
        case KeyEvent.VK_SPACE:
            mainTank.notify();
            return;
        default:
        break;
        }
        //no valid order
        if(order==null){
            return;
        }
        //too many frames
        if(orderQueue.add(order)==1){
            return;
        }
        order=(Order)order.clone();
        order.exec(mainTank);
    }
    @Override
    public void keyReleased(KeyEvent e){

    }
}
