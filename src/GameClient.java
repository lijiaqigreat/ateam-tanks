
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

public class GameClient
{
    static Socket con;
    static OutputStream out;
    static PrintStream ps;
    static InputStream in;
    static BufferedReader br;

    public static void main ( String args[] )
    {
        start ();

        while ( true )
        {
            send ( line () );
            System.out.println ( read () );
        }
    }

    public static void start ()
    {
        try
        {
            con = new Socket ( "localhost", 8887 );
            out = con . getOutputStream ();
            ps = new PrintStream ( out, true );
            in = con . getInputStream ();
            br = new BufferedReader ( new InputStreamReader ( in ) );
        }
        catch ( IOException e )
        {
            System.out.println ( "this client has died" );
        }
    }
    
    private static String read ()
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

    public static String line ()
    {
        System . out . print ( "send something: " );
        return System . console () . readLine ();
    }

    public static void send ( String line )
    {
        ps . println ( line );
        System . out . println ( "message sent" );
    }

}
