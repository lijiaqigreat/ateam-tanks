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

/*
 * A tank.
 *
 * Not sure why I called it SimpleTank -- I did not
 * have a more complicated form in mind
 * (it might get renamed to Tank)
 */

import java.util.ArrayList;
import java.awt.*;
import java.awt.geom.*;

class SimpleTank extends Sprite
{
    private ArrayList<SimpleTank> playerTanks;
    private double speed; //how far a frame of MoveOrder will move the tank
    private double handling; //how far a frame of TurnOrder will turn the tank
    private OrderQueue orders;

    public SimpleTank ( ArrayList<Sprite> sprites, ArrayList<SimpleTank> playerTanks, Vector3D position, Direction direction, double speed, double handling )
    {
        super ( sprites, position, direction, new HitBox ( 10, 5, 5 ) );
        this.speed = speed;
        this.handling = handling;
        OrderQueue orders = new OrderQueue ();
        this.playerTanks = playerTanks;
    }
    
    public void giveOrders ( OrderQueue newOrders )
    {
        orders = newOrders;
    }

    public double getSpeed ()
    {
        return speed;
    }
    public double getHandling ()
    {
        return handling;
    }

    public void kill ()
    {
        sprites.remove ( this );
        playerTanks.remove ( this );
    }

    public int update ()
    {
        orders.exec( this );
        return 0;
    }
    /*
    public void paint(Graphics2D g){
        double radius=100;
        g.setColor(Color.red);
        g.fill(Sprite.getCircle(position.getX(),position.getY(),radius));
        g.setColor(Color.blue);
        double direction=this.direction.getValue()*Math.PI/180;
        g.draw(new Line2D.Double(position.getX(),position.getY(),position.getX()+Math.cos(direction)*radius,
                                                   position.getY()+Math.sin(direction)*radius));
    }
    */
    
}
