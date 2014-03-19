import java.net.*;
import java.io.*;

public class NetTestServer
{
    static ServerSocket server;
    static Socket conn;
    static InputStream in;
    static BufferedReader br;

    public static void main ( String args[] )
    {
        start ();
        // foreverrrr
        while ( true )
        {
            System . out . println ( read () );
        }
    }

    private static void start ()
    {
        try
        {
            server = new ServerSocket(8887);
            conn = server . accept ();
            in = conn . getInputStream ();
            br = new BufferedReader ( new InputStreamReader ( in ) );
        }
        catch ( IOException e )
        {
            // ..
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

}
