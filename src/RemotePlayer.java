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

import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.io.*;

/*
 * This is a player that communicates with
 * a remote client
 */
public class RemotePlayer extends Player
{
    ConnectionThread con;

    public RemotePlayer(int id, Socket c)
    {
        super(id);
        this.con = new ConnectionThread(c);
        con.start();
    }

    public ArrayList<OrderQueue> getOrders()
    {
        return this.con.getOrders();
    }

    public void giveGameState(SpriteList s)
    {
        this.con.sendGameState(s);
    }

    public void giveSettledGameState(SpriteList s)
    {
        this.con.sendSettledGameState(s);
    }

    protected String askForPlayerName()
    {
        return con.askForPlayerName();
    }

    private class ConnectionThread extends Thread
    {
        ObjectInputStream in;
        ObjectOutputStream out;
        LinkedBlockingDeque<ArrayList<OrderQueue>> orderDump;
        LinkedBlockingDeque<SpriteList> spriteDump;
        LinkedBlockingDeque<SpriteList> settledSpriteDump;
        LinkedBlockingDeque<String> playerNameDump;

        public ConnectionThread(Socket c)
        {
            try {
                this.in = new ObjectInputStream(c.getInputStream());
                this.out = new ObjectOutputStream(c.getOutputStream());
            } catch (IOException e) {
                System.out.println("ConnectionThread init failed");
            }
            this.orderDump = new LinkedBlockingDeque<ArrayList<OrderQueue>>();
            this.spriteDump = new LinkedBlockingDeque<SpriteList>();
            this.settledSpriteDump = new LinkedBlockingDeque<SpriteList>();
            this.playerNameDump = new LinkedBlockingDeque<String>();
        }

        public String askForPlayerName()
        {
            String name = this.waitGet(playerNameDump);
            return name;
        }

        public ArrayList<OrderQueue> getOrders()
        {
            ArrayList<OrderQueue> o = this.waitGet(orderDump);
            return o;
        }

        public void sendGameState(SpriteList s)
        {
            try {
                this.spriteDump.put(s);
            } catch (InterruptedException e) {
                // meh
            }
        }

        public void sendSettledGameState(SpriteList s)
        {
            try {
                this.settledSpriteDump.put(s);
            } catch (InterruptedException e) {
                // meh
            }
        }

        private <E> E waitGet(LinkedBlockingDeque<E> bd)
        {
            E o;
            try {
                o = bd.take();
            } catch (InterruptedException e) {
                System.out.println("uh oh");
                o = null;
            }
            return o;
        }


        public void run()
        {
            // get the player's name to start
            try {
                this.playerNameDump.put((String) in.readObject());
            } catch (IOException e) {
                // meh
            } catch (ClassNotFoundException e) {
                System.out.println("i dont even know");
            } catch (InterruptedException e) {
                System.out.println("i dont even know");
            }
            // send the intital game state
            try {
                out.writeObject(this.waitGet(settledSpriteDump));
            } catch (IOException e) {
                // meh
            }
            while(!!!false) // hue
            {
                try {
                    // get the orders
                    this.orderDump.put((ArrayList<OrderQueue>) in.readObject());
                    // send the unplayed sprite-list
                    out.writeObject(this.waitGet(spriteDump));
                    // send the played sprite-list
                    out.writeObject(this.waitGet(settledSpriteDump));
                } catch (IOException e) {
                    System.out.println("spanner in works, pls fix");
                } catch (ClassNotFoundException e) {
                    System.out.println("i dont even know");
                } catch (InterruptedException e) {
                    // ...
                }
            }
        }
    }
}
