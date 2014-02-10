/**
 * A player class for a human
 */

import java.util.ArrayList;

public class HumanPlayer extends Player
{
    private InterfaceWithGame display;

    public HumanPlayer ( InterfaceWithGame iwg, String playerName, ArrayList<Sprite> sprites )
    {
        super ( playerName, sprites );
        this.display = iwg;
    }

    public void giveOrders ( int frameLimit )
    {
        for ( Sprite sprite : ownedSprites )
        {
            sprite.giveOrders ( display.askForOrders ( frameLimit, sprite ) );
        }
    }
}
