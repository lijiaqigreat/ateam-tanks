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

/* This guy listens for new client connections
 * and then hands them off to helper threads that
 * listen to the clients and report their messages
 * to a thread-safe queue that the game-server
 * reads
 */
 
public class CollectServer extends Thread
{
    ServerSocket server = null;
    BlockingQueue<String> reportDump;
    BlockingQueue<Socket> clientDump;
    int clientCount;

    public CollectServer ( BlockingQueue r, BlockingQueue c )
    {
        try
        {
            server = new ServerSocket(8887);
        }
        catch ( IOException e )
        {
            System.out.println ( "meh" );
        }
        reportDump = r;
        clientDump = c;
        clientCount = 0;
    }
        

    public void run ()
    {
        while ( clientCount < 10 ) // Safety measure for early testing
        {
            Socket con = null;
            try
            {
                con = server . accept ();
            }
            catch ( IOException e )
            {
                // ...
            }
            AideServer aide = new AideServer ( reportDump, con );
            try
            {
                clientDump . put ( con );
            }
            catch ( InterruptedException e )
            {
                // ...
            }
            aide . start ();
            System.out.println ( "Client connected" );
            clientCount ++;
        }
    }
}
