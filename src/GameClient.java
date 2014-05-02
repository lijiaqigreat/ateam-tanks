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

public class GameClient extends Thread implements DropBox<ClientEvent>
{

    private String name;
    private BlockingQueue<ClientEvent> events;
    private DropBox<UserEvent> outBox;
    private DropBox<GWEvent> gameWindow;
    int port;

    public GameClient(String name, int port)
    {
        this.name = name;
        this.port = port;
        this.events = new LinkedBlockingDeque<ClientEvent>();
        this.outBox = new FakeBox<UserEvent>();
        this.gameWindow = new FakeWindow();
        this.start();
    }

    public void push(ClientEvent ev)
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

    public void toUser(UserEvent ev)
    {
        this.outBox.push(ev);
    }

    public void toGW(GWEvent ev)
    {
        this.gameWindow.push(ev);
    }

    public String getPlayerName()
    {
        return this.name;
    }

    public void connect(String hostname)
    {
        this.outBox = new NetWorker<ClientEvent,UserEvent>().connect(this, hostname, this.port);
    }

    public void disconnect()
    {
        this.toUser(new ServerEventUserEvent(new DisconnectionServerEvent(this.name, "Client quit")));
        this.outBox = new NetWorker<ClientEvent,UserEvent>().disconnect();
    }

}
