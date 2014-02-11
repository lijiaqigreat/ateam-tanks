/**
 * An interface for a general ui (graphical or not)
 * that the game would use during the order-getting
 * and running phases.
 *
 * This does not include functionality of the main
 * menu.
 */

public interface InterfaceWithGame
{
    /**
     * Contains any initialization code such as
     * opening a window.
     *
     * Returns True if successfull
     */
    public boolean initializeDisplay ( ArrayList<Sprite> sprites, int mapSize );

    /**
     * Contains any code needed for closing windows
     * or cleaning up other interface elements.
     *
     * This is basically a destructor -- nothing
     * else is called after this.
     */
    public void cleanUpAndDestroyDisplay ();

    /**
     * Updates display with current positions and states
     * of sprites
     */
    public void updateDisplay ();

    /**
     * Performs some arbitrary actions to prompt a human 
     * player for orders for a particular unit and gets
     * those orders.
     *
     * frameLimit is the number of total frames that can
     * be distributed among those orders.
     *
     * sprite is the sprite that is currently being ordered 
     * (this method gets it so that it can use its state
     *  data to preview how each order will be executed by
     *  the sprite)
     */
    public OrderQueue askForOrders ( int frameLimit, Sprite sprite );

    /**
     * Perform some display to congratulate the winning
     * player.
     */
    public void announceWinner ( String winnerName );
}
