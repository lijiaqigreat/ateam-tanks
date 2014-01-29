import java.lang.Math;

class Velocity
{
    private int vx;
    private int vy;
    private int vz;

    public Velocity ( int speed, int direction, int inclination ) //direction and inclination are in degrees
    {
        vx = ( int ) Math.cos ( Math.toRadians ( ( double ) direction ) ) * ( double ) speed;
        vy = ( int ) Math.sin ( Math.toRadians ( ( double ) direction ) ) * ( double ) speed;
        vz = ( int ) Math.sin ( Math.toRadians ( ( double ) inclination ) ) * ( double ) speed;
    }
    public Velocity ( Velocity v )
    {
        this.vx = v.vx;
        this.vy = v.vy;
        this.vz = v.vz;
    }

    public add ( Velocity v )
    {
        vx += v.vx;
        vy += v.vy;
        vz += v.vz;
    }
    public getX ()
    {
        return vx;
    }
    public getY ()
    {
        return vy;
    }
    public getZ ()
    {
        return vz;
    }
}
