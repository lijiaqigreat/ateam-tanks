/**
 * Copyright 2014 A-Team Games
 *
 * This file is part of ateam-tanks.
 *
 *    ateam-tanks is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    ateam-tanks is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with ateam-tanks.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * This is a superclass for all types of bullets and shells that tanks can shoot.
 *
 */

import java.util.ArrayList;
import java.awt.Color;

public abstract class Projectile extends Sprite
{
    protected Vector3D velocity;
    protected Vector3D gravity;

    public Projectile ( SpriteList sprites, Vector3D position, Direction direction, HitBox hitbox, Color color, Vector3D velocity, Vector3D gravity )
    {
        super ( sprites, position, direction, hitbox, color );
        this.velocity = velocity;
        this.gravity = gravity;
    }

    /**
     * A method for exploding or inflicting damage or splitting in two or whatever
     * this particular subclass of projectile is supposed to do upon hitting
     * another sprite. There is no default behavior.
     */
    public abstract void reactToCollision ( ArrayList<Sprite> collisions );

    public abstract void damage ( int intensity ); // inherited but still not implemented

    public int update ()
    {
        this.position = new Vector3D ( this.position, this.velocity );
        this.velocity = new Vector3D ( this.velocity, this.gravity );
        ArrayList<Sprite> coll = getAllCollisions ();
        if ( coll . size () > 0 )
        {
            reactToCollision ( coll );
        }
        return 1;
    }
}
