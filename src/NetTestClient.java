import java.net.*;
import java.io.*;

public class NetTestClient
{

public static void main ( String args[] )
{
    try {

    Socket connection = new Socket ( "localhost", 8887 );

    OutputStream out = connection . getOutputStream ();
    PrintStream ps = new PrintStream ( out, true );

    ps . println ( "ping" );

    System . out . println ( "Ping sent" );

    }
    catch ( IOException e )
    {
        // ...
    }
}

}
