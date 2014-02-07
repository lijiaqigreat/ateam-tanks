/**
 * This is a sprite representing a non-moving object
 * on the playing field.
 *
 * These will be the main pieces that give the map
 * variance
 *
 */

public abstract class Obstacle extends Sprite
{
    public Obstacle ( ArrayList<Sprite> sprites, Vector3D position, Direction direction )
    {
        super ( sprites, position, direction );
    }

    /**
     * An obstacle does nothing and is affected by nothing
     */
    public int update ()
    {
        return 0;
    }
}
