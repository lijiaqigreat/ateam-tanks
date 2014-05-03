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

/* The GameServer is intended to handle connections and disconnections and
 * create and start games, all in the form of responses to "events"
 */
public class GameServer extends ConcreteDropBox<GameServer>
{
    
    private Map<String,User> users;
    private Map<String,Room> rooms;
    private int userCapacity;

    public GameServer(int userCapacity, int port)
    {
        this.users = new HashMap<String,User>();
        this.userCapacity = userCapacity;
        this.rooms = new HashMap<String,Room>();
        this.rooms.put("Lobby", new Room(this, "Lobby"));
        new CollectServer(this, port);
        this.start();
    }

    public DropBox<Room> getLobby()
    {
        return this.rooms.get("Lobby");
    }

    public Set<String> getUserNames()
    {
        return this.users.keySet();
    }

    public void shutdown()
    {
        this.shutdown("OP shutdown");
    }

    public void shutdown(String reason)
    {
        announce("Server is going down: " + reason);
        try {
            sleep(500);
        } catch (InterruptedException e) {}
        for (String uname : this.users.keySet())
        {
            this.users.get(uname).push(new event.user.PartEvent("Server shutdown"));
        }
        try {
            sleep(500);
        } catch (InterruptedException e) {}
        this.killingYou();
    }


    public void announce(String announcement)
    {
        for(String uname : this.users.keySet())
        {
            this.users.get(uname).push(new event.user.FwdClientEvent(new event.client.ChatEvent("Server", "public", announcement)));
        }
    }

    public void removeUser(String name, String reason)
    {
        this.users.get(name).push(new event.user.PartEvent(reason));
        this.users.remove(name);
    }

    public Room getRoom(String name)
    {
        return this.rooms.get(name);
    }

    public void toUser(String name, Event<User> ev)
    {
        this.users.get(name).push(ev);
    }

    public boolean addRoom(User creator, String name, SpriteList initList)
    {
        if(this.rooms.containsKey(name))
        {
            creator.push(new event.user.FwdClientEvent(new event.client.ChatEvent("Server", "private", "A game with that name already exists")));
            return false;
        }
        else
        {
            this.rooms.put(name, new GameRoom(this, name, creator, initList));
            return true;
        }
    }

    public boolean addUser(User u)
    {
        if(this.users.containsKey(u.getPlayerName()))
        {
            u.push(new event.user.PartEvent("Username already in use"));
            return false;
        }
        else if(this.users.size() == this.userCapacity)
        {
            u.push(new event.user.PartEvent("Server is full"));
            return false;
        }
        else
        {
            this.users.put(u.getPlayerName(), u);
            System.out.println(u.getPlayerName() + " has joined!!");
            return true;
        }
    }

    public int getUserCapacity()
    {
        return this.userCapacity;
    }

}
