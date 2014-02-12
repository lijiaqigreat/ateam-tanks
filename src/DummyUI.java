/**
 * A do-nothing standin for testing the Player
 * and Game classes ( well, it does print position information )
 */

import java.util.ArrayList;

class DummyUI implements InterfaceWithGame
{
    private ArrayList<Sprite> sprites;
    
    public DummyUI ()
    {
        sprites = new ArrayList<Sprite>();
    }

    public boolean initializeDisplay ( ArrayList<Sprite> sprites, int mapsize )
    {
        this.sprites = sprites;
        System.out.println ( "All initialized with a mapsize of " + mapsize + "!" );

        return true;
    }
    public void cleanUpAndDestroyDisplay ()
    {
        System.out.println ( "Everything is cleaned up\n" );
    }
    public void updateDisplay ()
    {
        for ( Sprite sprite : sprites )
        {
            System.out.println ( sprite.getPosition().toString() );
        }
    }
    public OrderQueue askForOrders ( int frameLimit, Sprite sprite )
    {
        System.out.println ( "There are only " + frameLimit + " frames in this turn." );
        System.out.println ( "You wanted to move forward for " + frameLimit/2 + " frames, right?" );

        OrderQueue q = new OrderQueue();
        q.add ( new MoveOrder ( frameLimit/2, 1 ) );

        return q;
    }
    public void announceWinner ( String winnerName )
    {
        System.out.println ( "The winner appears to be " + winnerName + "." );
    }
}

