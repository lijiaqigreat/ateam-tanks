import java.util.ArrayList;

public abstract class Sprite
{
    ArrayList<Sprite> sprites;
    protected Position p;

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

    public void kill ()
    {
        sprites.remove ( this );
    }
}
