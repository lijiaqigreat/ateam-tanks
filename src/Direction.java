/**
 * A class to abstract sprite directions
 *
 */
public class Direction
{
    private double dir;

    public Direction ()
    {
        dir = 0;
    }
    public Direction ( double dir )
    {
        this.dir = round ( dir );
    }
    public Direction ( Direction other )
    {
        this.dir = other.dir;
    }
    public Direction ( Direction other, Direction offset )
    {
        this.dir = round ( other.dir + offset.dir );
    }

    public void add ( Direction other )
    {
        this.dir = round ( this.dir + other.dir );
    }
    public double getValue ()
    {
        return dir;
    }

    private void round ( double dir )
    {
        return dir % 360.0;
    }
}
