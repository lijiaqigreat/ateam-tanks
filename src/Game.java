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
    private int mapsize;

    public Game ( ArrayList<Player> p, ArrayList<Sprite> s, InterfaceWithGame d, int frames, int turns, int mapsize )
    {
        players = p;
        sprites = s;
        display = d;

        framesPerTurn = frames;
        turnLimit = turns;

        this.mapsize = mapsize;
    }

    public int run ()
    {
        display.initializeDisplay ( sprites, mapsize );

        boolean running = true;

        for ( int t = 1; t <= turnLimit && running; t ++ )
        {
            GameTest.debug("starting turn");
            for ( Player player : players )
            {
                if ( player.stillAlive() )
                {
                    GameTest.debug("giving orders");
                    player.giveOrders ( framesPerTurn );
                    GameTest.debug("order given");
                }
                else
                {
                }
            }

            boolean unfinishedBusiness = false; // used to check if bullets are still flying
            for ( int f = 0; f < framesPerTurn || unfinishedBusiness; f ++ )
            {
                try{
                    Thread.sleep(1000);
                //TODO
                unfinishedBusiness = false;
                for ( Sprite sprite : sprites )
                {
                    if ( sprite.update() == 1 )
                    {
                        unfinishedBusiness = true;
                    }
                }
                GameTest.debug("update!");
                
                }catch(Exception e){}
                display.updateDisplay ();
            }

            int alive = 0;
            String winner = "Nobody";
            for ( Player player : players )
            {
                if ( player.stillAlive() )
                {
                    alive ++;
                    winner = player.getName();
                }
            }
            if ( alive <= 1 )
            {
                display.announceWinner ( winner );
                running = false;
            }
        }

        display.cleanUpAndDestroyDisplay ();
        return 0;
    }
}
