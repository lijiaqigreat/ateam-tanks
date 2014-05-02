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

import java.util.concurrent.*;
import java.util.*;

public class Room extends Thread implements DropBox<GameEvent>
{
    
    protected String name;
    protected String creator;
    private BlockingQueue<GameEvent> events;
    protected Map<String,User> users;
    protected Map<String,Player> players;
    private DropBox<ServerEvent> server;

    public Room(GameServer s, String name, String creator)
    {
        this.name = name;
        this.server = s;
        this.creator = creator;
        this.events = new LinkedBlockingDeque<GameEvent>();
        this.users = new HashMap<String,User>();
        this.players = new HashMap<String,Player>();
        this.start();
    }

    public void push(GameEvent ev)
    {
        try {
            this.events.put(ev);
        } catch (InterruptedException e) {}
    }

    public void run()
    {
        while(true)
        {
            try {
                this.events.take().handle(this);
            } catch (InterruptedException e) {}
        }
    }

    public boolean isGameRunning()
    {
        return false;
    }

    public void startGame() {}

    public boolean depositOrders(String playerName, ArrayList<OrderQueue> os)
    {
        return false;
    }

}
