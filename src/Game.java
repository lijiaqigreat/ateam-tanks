/**
 * A main game class that holds all relevant
 * game data and the game interface and runs
 * the main game loop.
 *
 * The current idea is that this is spawned
 * from a higher up gui-centric MainMenu type
 * class, and this runs independantly until
 * the game ends and it returns control to
 * that menu
 */

import java.util.ArrayList;

public class Game
{
    private ArrayList<Player> players;
    private ArrayList<Sprite> sprites;
    private InterfaceWithGame display;  // dat alignment

    private int framesPerTurn;
    private int turnLimit;

    public Game ( ArrayList<Player> p, ArrayList<Sprite> s, InterfaceWithGame d, int frames, int turns )
    {
        players = p;
        sprites = s;
        display = d;

        framesPerTurn = frames;
        turnLimit = turns;
    }

    public int run ()
    {
        display.initializeDisplay ();

        boolean running = true;

        for ( int t = 1; t <= turnLimit && running; t ++ )
        {
            for ( Player player : players )
            {
                if ( player.stillAlive() )
                {
                    player.giveOrders ( framesPerTurn );
                }
            }

            boolean unfinishedBusiness = false; // used to check if bullets are still flying
            for ( int f = 0; f < framesPerTurn || unfinishedBusiness; f ++ )
            {
                boolean unfinishedBusiness = false;
                for ( Sprite sprite : sprites )
                {
                    if ( sprite.update() == 1 )
                    {
                        unfinishedBusiness = true;
                    }
                }
                
                display.updateDisplay ( sprites );
            }

            int alive = 0;
            Player winner;
            for ( Player player : players )
            {
                if ( player.stillAlive() )
                {
                    alive ++;
                    winner = player;
                }
            }
            if ( alive < 1 )
            {
                display.announceWinner ( "Nobody" );
                running == false;
            }
            else if ( alive == 1 )
            {
                display.announceWinner ( winner.getName() );
                running == false;
            }
        }

        display.cleanUpAndDestroyDisplay ();
    }
}
