/**
 * Copyright 2014 A-Team Games
 *
 * This file is part of ateam-tanks.
 *
 *    ateam-tanks is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    ateam-tanks is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with ateam-tanks.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    public boolean initializeDisplay ( SpriteList sprites, int mapSize );

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
     *
     * it is important to note that while this method does
     * recieve the sprite, it does NOT give the OrderQueue
     * to the sprite directly -- it returns the OrderQueue
     * to the player and the player applies it
     */
    public OrderQueue askForOrders ( int frameLimit, Sprite sprite );

    /**
     * Perform some display to congratulate the winning
     * player.
     */
    public void announceWinner ( String winnerName );
}
