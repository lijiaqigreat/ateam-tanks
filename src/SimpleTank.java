import java.util.ArrayList;

class SimpleTank extends Sprite
{
    private int speed;
    private int handling;
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
