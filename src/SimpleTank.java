/*
 * A tank.
 *
 * Not sure why I called it SimpleTank -- I did not
 * have a more complicated form in mind
 * (it might get renamed to Tank)
 */

import java.util.ArrayList;

class SimpleTank extends Sprite
{
    private int speed; //how far a frame of MoveOrder will move the tank
    private int handling; //how far a frame of TurnOrder will turn the tank
    private OrderQueue orders;

    public SimpleTank ( ArrayList<Sprite> sprites, Position p, int speed, int handling )
    {
        super ( sprites, p );
        this.speed = speed;
        this.handling = handling;
        OrderQueue orders = new OrderQueue ();
    }
    
    public void giveOrders ( OrderQueue newOrders )
    {
        orders = newOrders;
    }

    public int getSpeed ()
    {
        return speed;
    }
    public int getHandling ()
    {
        return handling;
    }

    public int update ()
    {
        orders.exec( this );
        return 0;
    }
}
