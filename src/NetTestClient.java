import java.net.*;
import java.io.*;

public class NetTestClient
{
    static Socket conn;
    static OutputStream out;
    static PrintStream ps;

    public static void main ( String args[] )
    {
        start ();

        while ( true )
        {
            send ( line () );
        }
    }

    public static void start ()
    {
        try
        {
            conn = new Socket ( "localhost", 8887 );
            out = conn . getOutputStream ();
            ps = new PrintStream ( out, true );
        }
        catch ( IOException e )
        {
            // ...
        }
    }

    public static String line ()
    {
        System . out . print ( "send something" );
        return System . console () . readLine ();
    }

    public static void send ( String line )
    {
        ps . println ( line );
        System . out . println ( "Ping sent" );
    }

}
