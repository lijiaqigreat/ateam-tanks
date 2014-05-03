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

package network;

import game.*;
import gameinterface.*;

import java.util.*;
import event.Event;

public class Room extends ConcreteDropBox<Room>
{
    
    protected String name;
    protected Map<String,User> users;
    protected Map<String,Player> players;
    private DropBox<GameServer> server;

    public Room(GameServer s, String name)
    {
        this.name = name;
        this.server = s;
        this.users = new HashMap<String,User>();
        this.players = new HashMap<String,Player>();
        this.start();
    }

    public void addUser(User user)
    {
        this.users.put(user.getPlayerName(), user);
    }

    public boolean isCreator(String name)
    {
        return false;
    }

    public boolean isGameRunning()
    {
        return false;
    }

    public String getRoomName()
    {
        return this.name;
    }

    public void startGame() {}

    public boolean depositOrders(String playerName, ArrayList<OrderQueue> os)
    {
        return false;
    }

}
