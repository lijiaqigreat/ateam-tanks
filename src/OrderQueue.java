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
 * Each order will have a method that paints the appropriate thing and returns
 * a change. Move orders will paint a line from start to end and return their
 * magnitude.
 * Turns will paint some circly thing and return their turn distance.
 * Shoots will draw a line in the direction of the shot and return nothing.
 *
 * The orderqueue uses these returns to keep a model of the position and direction
 * of the tank which it passes to each order in turn..
 *
 * actually, the orderqueue makes a model of the tank and passes (by reference) it to the first order.
 * the order uses it to paint in the right place and then modifies it accordingly. it is then
 * passed on to the next order which does the same thing. after all the orders have been passed through,
 * the orderqueue uses the model it has made to print a rectangle in the approximate end location of the
 * tank.
 *
 * As the user edits an order, this rectangle moves accordingly until they confirm it, at which point
 * the order is added and the rectangle is painted (hopefully to the same spot it was just in). This is
 * a better solution than constantly adding and removing a new order of slightly different length with 
 * each press of the arrow keys.
 *
 * The user should be able to delete every order they have made ( in sort of a backing-up fashion ) by hitting
 * the escape key the correct number of times. With each press of the key, the latest order is kicked off the end
 * of the queue (it's not implemented as a strict queue) and the screen updates to show the order path without it.
 *
 * (These removed orders should also be put in a stack that can be restored one at a time with a different key. This
 *  stack would be deleted as soon as a new order is added in place of one in the deleted stack. This is basically the
 *  functionality of the "undo, redo" buttons in an editor)
 *
 * There should be a visual queue being built on the side of the screen as orders are assigned. It will be a vertical bar
 * representing the total number of frames availible for the turn, and it will fill up as orders are assigned. Each order type
 * will have a color, and the bar will fill up with little blocks of those colors.
 *
 * At the bottom of the screen will be a number of rectangles of the appropriate colors containing the name of each type of order
 * (and indicating what key to press to select it). These rectangles will highlight when their order is selected. To deselect an
 * order (and remove whatever "progress" has been made in defining that order), the user hits escape. Escape is the general "go back"
 * key.
 */

import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * A containing class for a list of orders that
 * each tank recieves at the beginning of each turn.
 *
 * All it does is take care of discarding orders when they
 * are out of frames
 *
 * The orderqueue is filled during the player's ordering
 * phase before each turn.
 *
 * @author Nick Lewchenko, Jiaqi Li
 *
 */

class OrderQueue implements Serializable
{
    private int framesLeft;
    private ArrayDeque<Order> orders;
    private String uid;

    public OrderQueue ()
    {
        this(100, "null-id");
    }
    public OrderQueue(int framesAllowed, String u){
        this.framesLeft=framesAllowed;
        this.uid = new String(u);
        orders = new ArrayDeque<Order> ();
    }

    public OrderQueue clone()
    {
        OrderQueue output = new OrderQueue();
        output.framesLeft = this.framesLeft;
        output.uid = new String(this.uid);
        ArrayDeque<Order> o = new ArrayDeque<Order>(this.orders);
        while (o.size() > 0)
        {
            output.orders.add(o.removeFirst());
        }
        //for (Order order : orders)
        //{
        //    output.add(order.clone());
        //}
        return output;
    }

    public int getFramesLeft(){
        return framesLeft;
    }

    /**
     * @return 0 when success, 1 when no more frames left.
     */
    public int add ( Order o )
    {
        if(o.getFrames()>framesLeft){
            return 1;
        }else{
            orders.add ( o );
            framesLeft-=o.getFrames();
            return 0;
        }
    }

    public void walkModel ( UnitModel model, Graphics2D g )
    {
        for ( Order o : this . orders )
        {
            o . walk ( model, g );
        }
    }

    /**
     * @return 0 when success, 1 when queue is empty.
     */
    public int exec ( SpriteList sprites, SimpleTank tank )
    {
        Order o=orders.peek();
        while(o!=null&&o.getFrames()==0){
            orders.remove();
            o=orders.peek();
        }
        if ( o !=null )
        {
            o.exec( sprites, tank );
            return 0;
        }else{
            return 1;
        }
    }
    public String uid()
    {
        return this.uid;
    }
}
