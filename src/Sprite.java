import java.util.ArrayList;

public abstract class Sprite
{
    ArrayList<Sprite> sprites;
    protected Position p;
    protected int direction;

    public Sprite ( ArrayList<Sprite> sprites, Position p )
    {
        this.sprites = sprites;
        this.sprites.add ( this );

        this.p = new Position ( p );
    }

    public abstract int update ();

    public Position getPosition ()
    {
        return p;
    }
    public void setPosition ( Position p )
    {
        this.p = p;
    }
    public int getDirection ()
    {
        return direction;
    }
    public void setDirection ( int d )
    {
        direction = d;
    }

    public void kill ()
    {
        sprites.remove ( this );
    }
}
