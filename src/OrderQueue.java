import java.util.LinkedList;

class OrderQueue
{
    LinkedList<Order> orders;

    public OrderQueue ()
    {
        orders = new LinkedList<Order> ();
    }

    public void add ( Order o )
    {
        orders.add ( o );
    }
    public void exec ( SimpleTank tank )
    {
        if ( orders.size() > 0 && orders.element().getFrames() <= 0 )
        {
            orders.remove();
        }
        if ( orders.size() > 0 )
        {
            orders.element().exec( tank );
        }
    }
}
