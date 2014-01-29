class SimpleTank extends Sprite
{
    int speed;
    int turning;
    OrdersQueue queue;

    public SimpleTank ( Position p, int speed, int turning )
    {
        super ( p );
        this.speed = speed;
        this.turning = turning;
        OrdersQueue = new OrdersQueue ();
    }
