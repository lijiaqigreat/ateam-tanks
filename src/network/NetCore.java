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

import java.io.*;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;
import event.Event;

public class NetCore<I,O> implements DropBox<O>
{

    ObjectInputStream in;
    ObjectOutputStream out;
    BlockingQueue<Event<O>> toSend;

    public NetCore(Socket c, DropBox<I> d)
    {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(c.getOutputStream());
            out.flush();
            in = new ObjectInputStream(c.getInputStream());
        } catch (IOException e) {
            System.out.println("NetCore stream init failed :/");
        }
        this.toSend = new LinkedBlockingDeque<Event<O>>();
        new ReceiveThread<I>(in, d);
        new SendThread<O>(out, toSend);
    }

    public void push(Event<O> ev)
    {
        try {
            this.toSend.put(ev);
        } catch (InterruptedException e) {}
    }

    private class ReceiveThread<I> extends Thread
    {

        ObjectInputStream in;
        DropBox<I> drop;

        public ReceiveThread(ObjectInputStream i, DropBox<I> d)
        {
            this.in = i;
            this.drop = d;
            this.start();
        }

        public void run()
        {
            while(true)
            {
                try {
                    Event<I> ev = (Event<I>) in.readObject();
                    drop.push(ev);
                } catch (IOException e) {
                    System.out.println("uh oh");
                } catch (ClassNotFoundException e) {}
            }
        }

    }

    private class SendThread<O> extends Thread
    {

        ObjectOutputStream out;
        BlockingQueue<Event<O>> toSend;

        public SendThread(ObjectOutputStream o, BlockingQueue<Event<O>> q)
        {
            this.out = o;
            this.toSend = q;
            this.start();
        }

        public void run()
        {
            while(true)
            {
                try {
                    out.writeObject(this.toSend.take());
                } catch (IOException e) {
                    System.out.println("netcore network failure?");
                } catch (InterruptedException e) {
                    System.out.println("netcore interrupt failure?");
                }
            }
        }

    }

}
