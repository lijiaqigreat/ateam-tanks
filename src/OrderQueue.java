/*
 * A containing class for a list of orders that
 * each tank recieves at the beginning of each turn.
 *
 * All it does is take care of discarding orders when they
 * are out of frames
 *
 * The orderqueue is filled during the player's ordering
 * phase before each turn.
 *
 */

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
