/**
 * This is a sprite representing a non-moving object
 * on the playing field.
 *
 * These will be the main pieces that give the map
 * variance
 *
 */

import java.util.ArrayList;
import java.awt.Graphics2D;

public class Obstacle extends Sprite
{
    public Obstacle ( ArrayList<Sprite> sprites, Vector3D position, Direction direction, HitBox box )
    {
        super ( sprites, position, direction, box );
    }

    /**
     * An obstacle does nothing and is affected by nothing
     */
    public int update ()
    {
        return 0;
    }
    public void paint(Graphics2D g){}
}
