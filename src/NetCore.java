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

import java.io.*;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;

public class NetCore<I,O> implements DropBox<O>
{

    ObjectInputStream in;
    ObjectOutputStream out;
    BlockingQueue<O> toSend;

    public NetCore(Socket c, DropBox<I> d)
    {
        try {
            ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(c.getInputStream());
        } catch (IOException e) {
            System.out.println("NetCore stream init failed :/");
        }
        this.toSend = new LinkedBlockingDeque<O>();
        new ReceiveThread<I>(in, d).start();
        new SendThread<O>(out, toSend).start();
    }

    public void push(O o)
    {
        try {
            this.toSend.put(o);
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
                    drop.push((I) in.readObject());
                } catch (IOException e) {
                } catch (ClassNotFoundException e) {}
            }
        }

    }

    private class SendThread<O> extends Thread
    {

        ObjectOutputStream out;
        BlockingQueue<O> toSend;

        public SendThread(ObjectOutputStream o, BlockingQueue<O> q)
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

