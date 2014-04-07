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
import java.io.*;
import java.util.concurrent.*;
import java.util.ArrayList;

/* This guy listens for new client connections
 * and then hands them off to helper threads that
 * listen to the clients and report their messages
 * to a thread-safe queue that the game-server
 * reads
 */
 
public class CollectServer extends Thread
{
    ServerSocket server = null;
    BlockingQueue<RemotePlayer> playerDump;
    int aim;
    int aimClientCount;

    public CollectServer (int r)
    {
        try
        {
            server = new ServerSocket(8887);
        }
        catch ( IOException e )
        {
            System.out.println ( "meh says the collectserver" );
        }
        playerDump = new LinkedBlockingDeque<RemotePlayer>();
        aimClientCount = r;
        aim = r;
    }
        
    public ArrayList<RemotePlayer> getClients()
    {
        ArrayList<RemotePlayer> ps = new ArrayList<RemotePlayer>();
        for (int i=1; i<=aim; i++)
        {
            try {
                ps.add(playerDump.take());
            } catch (InterruptedException e) {
                // ...
            }
        }
        return ps;
    }

    public void run ()
    {
        System.out.println("--- Awaiting clients");
        while (this.aimClientCount > 0)
        {
            Socket con = null;
            try
            {
                con = server . accept ();
                RemotePlayer p = new RemotePlayer((this.aim-this.aimClientCount)+1, con);
                try
                {
                    playerDump . put ( p );
                    System.out.println ( "Client connected" );
                    aimClientCount --;
                }
                catch ( InterruptedException e )
                {
                    // ...
                }
            }
            catch ( IOException e )
            {
                // ...
            }
            
        }
        System.out.println("--- All clients connected");
    }
}
