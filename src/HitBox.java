/**
 * A rectangular prism in which a sprite resides.
 *
 * If two sprites' HitBoxes intersect, they are
 * considered to be colliding.
 *
 */

public class HitBox
{
    private double length;
    private double width;
    private double height;

    public HitBox ()
    {
        length = 0;
        width = 0;
        height = 0;
    }
    public HitBox ( double l, double w, double h )
    {
        length = l;
        width = w;
        height = h;
    }
    public HitBox ( HitBox other )
    {
        length = other.length;
        width = other.width;
        height = other.height;
    }

    public double getLength ()
    {
        return length;
    }
    public double getWidth ()
    {
        return width;
    }
    public double getHeight ()
    {
        return height;
    }
}
