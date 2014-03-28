
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

/* This class is constructed with a single
 * client connection to listen to -- it
 * posts everything the client says to a
 * thread-safe queue that the game-server
 * reads
 */
public class AideServer extends Thread
{

    Socket con;
    InputStream in;
    BufferedReader br;
    BlockingQueue reportDump;

    public AideServer ( BlockingQueue r, Socket c )
    {
        try
        {
            con = c;
            in = con . getInputStream ();
            br = new BufferedReader ( new InputStreamReader ( in ) );
        }
        catch ( IOException e )
        {
            System.out.println ( "Failed aide-server init" );
        }
        reportDump = r;
    }

    public void run ()
    {
        while ( true )
        {
            this . report ( this . read () );
        }
    }

    private String read ()
    {
        String outLine = "error";
        try
        {
            String line = br . readLine ();
            if ( line . equals ( "ping" ) )
            {
                outLine = "pong";
            }
            else
            {
                outLine = line;
            }
        }
        catch ( IOException e )
        {
            // meh
        }
        return outLine;
    }

    private void report ( String message )
    {
        try
        {
            reportDump . put ( message );
        }
        catch ( InterruptedException e )
        {
            System.out.println ( "boo" );
        }
    }

}
