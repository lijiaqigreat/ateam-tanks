/*
 * This is a general class for anything that might
 * be placed, removed, or move or change in any way
 * on the field during play. The idea is that the main
 * game loop can iterate over a list of these and update
 * each one.
 * And then the graphical interface can iterate over the list
 * and display each one.
 *
 * So far only tanks use them, but the next obvious user would
 * be a bullet
 */

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
