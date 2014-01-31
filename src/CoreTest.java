/*
 * An initial test of the class structures using a set of defined orders.
 *
 * No graphics -- the tank position is just printed to the screen after each frame.
 */

import java.util.ArrayList;

public class CoreTest
{
    public static void main ( String args[] )
    {
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        SimpleTank tank1 = new SimpleTank ( sprites, new Position ( 2000, 2000, 0 ), 20, 5 );

        OrderQueue q = new OrderQueue();
        q.add ( new MoveOrder ( 10, 1 ) );
        q.add ( new TurnOrder ( 10, 1 ) );
        q.add ( new MoveOrder ( 20, 1 ) );
        q.add ( new MoveOrder ( 5, -1 ) );

        tank1.giveOrders ( q );
        
        for ( int i = 0; i < 200; i ++ )
        {
            for ( Sprite sprite : sprites )
            {
                sprite.update();
                System.out.println ( sprite.getPosition().toString() + " -- " + sprite.getDirection() );
            }
        }
    }
}
