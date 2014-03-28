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

public class GameServer
{

    BlockingQueue<String> reportDump;
    BlockingQueue<Socket> clientDump;
    ArrayList<PrintStream> outputs;

    public GameServer ()
    {
        reportDump = new LinkedBlockingDeque<String> ();
        clientDump = new LinkedBlockingDeque<Socket> ();
        outputs = new ArrayList<PrintStream> ();
        CollectServer collector = new CollectServer ( reportDump, clientDump );
        collector . start ();
    }

    public void play ()
    {
        while ( true )
        {
            try
            {
                while ( clientDump . size () != 0 )
                {
                    outputs . add ( new PrintStream ( clientDump . take () . getOutputStream () ) );
                }
                while ( reportDump . size () != 0 )
                {
                    System.out.println ( "recieved: " );
                    System.out.println ( reportDump . take () );
                }
                System.out.println ( "send announcement: " );
                String o = System . console () . readLine ();
                for ( PrintStream output : outputs )
                {
                    output . println ( o );
                }
            }
            catch ( InterruptedException i )
            {
                System.out.println ( "main loop fail" );
            }
            catch ( IOException i )
            {
                System.out.println ( "main loop fail" );
            }
        }
    }

}
