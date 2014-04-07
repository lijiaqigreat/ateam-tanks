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
 * This class represents a player, for the purposes
 * of orders allocation.
 */

import java.util.ArrayList;

public abstract class Player
{
    protected String playerName;
    protected int id;

    public Player(int id)
    {
        this.id = id;
        this.playerName = this.askForPlayerName();
    }

    public abstract ArrayList<OrderQueue> getOrders();

    public abstract void giveGameState(SpriteList s);

    public abstract void giveSettledGameState(SpriteList s);

    protected abstract String askForPlayerName();

    public String getName()
    {
        return playerName;
    }

    public int ID()
    {
        return this.id;
    }

    public void kill()
    {
        // does nothing for now
    }
}
