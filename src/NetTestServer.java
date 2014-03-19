import java.net.*;
import java.io.*;

public class NetTestServer
{

public static void main ( String args[] )
{
    try {

    ServerSocket server = new ServerSocket(8887);

    Socket connection = server . accept ();


    InputStream in = connection . getInputStream ();
    BufferedReader br = new BufferedReader ( new InputStreamReader ( in ) );

    String line = br . readLine ();

    String outLine = "pong";
    if ( line . equals ( "ping" ) )
    {
        // keep pong
    }
    else
    {
        outLine = line;
    }
    System . out . println ( outLine );

    }
    catch ( IOException e )
    {
        // who really cares..
    }
}

}
