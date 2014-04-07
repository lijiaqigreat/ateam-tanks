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
import java.util.*;
import java.util.concurrent.*;
import java.io.*;

/*
 * This is a dummy client that just returns empty
 * orderqueues.
 */
public class FakeClient
{
    ConnectionThread con;

    public FakeClient()
    {
        this.con = new ConnectionThread();
        System.out.println("past ct setup");
        con.start();
        con.sendName(System.console().readLine("enter name: "));
    }

    private class ConnectionThread extends Thread
    {
        ObjectInputStream in;
        ObjectOutputStream out;
        LinkedBlockingDeque<ArrayList<OrderQueue>> orderDump;
        LinkedBlockingDeque<SpriteList> spriteDump;
        LinkedBlockingDeque<SpriteList> settledSpriteDump;
        LinkedBlockingDeque<String> playerNameDump;

        public ConnectionThread()
        {
            try {
                Socket c = new Socket("localhost", 8887);
                System.out.println("creating streams");
                this.out = new ObjectOutputStream(c.getOutputStream());
                this.out.flush();
                System.out.println("...");
                this.sleep(500);
                this.in = new ObjectInputStream(c.getInputStream());
                System.out.println("connection to server established");
            } catch (IOException e) {
                System.out.println("ConnectionThread init failed");
            } catch (InterruptedException e) {
                // ...
            }
            this.orderDump = new LinkedBlockingDeque<ArrayList<OrderQueue>>();
            this.spriteDump = new LinkedBlockingDeque<SpriteList>();
            this.settledSpriteDump = new LinkedBlockingDeque<SpriteList>();
            this.playerNameDump = new LinkedBlockingDeque<String>();
        }

        public void sendName(String name)
        {
            try {
                this.playerNameDump.put(name);
            } catch (InterruptedException e) {
                System.out.println("i dont even know");
            }
        }

        public void run()
        {
            // send name for test
            try {
                out.writeObject(this.waitGet(playerNameDump));
                System.out.println("name sent");
            } catch (IOException e) {
                System.out.println("???");
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
    }
}
