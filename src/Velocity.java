/*
 * This was intended to be something a bullet had as state
 * to represent its current velocity.
 *
 * Right now it is just used to produce adjacent positions
 * for tanks as they iterate through a MoveOrder
 *
 * Will likely be combined with Position for a more general Vector3D,
 * as per lijiaqigreat's suggestion
 */


import java.lang.Math;

class Velocity
{
    private int vx;
    private int vy;
    private int vz;

    public Velocity ( int speed, int direction, int inclination ) //direction and inclination are in degrees
    {
        vx = ( int ) ( Math.cos ( Math.toRadians ( ( double ) direction ) ) * ( double ) speed ) ;
        vy = ( int ) ( Math.sin ( Math.toRadians ( ( double ) direction ) ) * ( double ) speed ) ;
        vz = ( int ) ( Math.sin ( Math.toRadians ( ( double ) inclination ) ) * ( double ) speed ) ;
    }
    public Velocity ( Velocity v )
    {
        this.vx = v.vx;
        this.vy = v.vy;
        this.vz = v.vz;
    }

    public void add ( Velocity v )
    {
        vx += v.vx;
        vy += v.vy;
        vz += v.vz;
    }
    public int getX ()
    {
        return vx;
    }
    public int getY ()
    {
        return vy;
    }
    public int getZ ()
    {
        return vz;
    }
}
