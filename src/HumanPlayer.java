/**
 * A player class for a human
 */

import java.util.ArrayList;

public class HumanPlayer extends Player
{
    private InterfaceWithGame display;

    public HumanPlayer ( InterfaceWithGame iwg, String playerName, ArrayList<SimpleTank> tanks )
    {
        super ( playerName, tanks );
        this.display = iwg;
    }

    public void giveOrders ( int frameLimit )
    {
        for ( SimpleTank tank : ownedTanks )
        {
            tank.giveOrders ( display.askForOrders ( frameLimit, tank) );
        }
    }
}
