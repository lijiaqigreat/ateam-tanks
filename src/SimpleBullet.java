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

import java.util.ArrayList;
import java.awt.Color;

public class SimpleBullet extends Projectile
{
    public SimpleBullet ( SpriteList sprites, Vector3D position, Direction direction, HitBox hitbox )
    {
        super ( sprites, position, direction, hitbox, Color.black, new Vector3D ( 5, direction ), new Vector3D ( 0, 0, -1 ) );
        System.out.println ( "Bullet fired!" );
    }
    
    public void reactToCollision ( ArrayList<Sprite> collisions )
    {
        for ( Sprite hitSprite : collisions )
        {
            hitSprite . damage ( 5 ); // inflicts 5 damage on sprites (or tries to, anyway)
        }
        this . kill (); // deletes self from the game
        System.out.println ( "Boom!" );
    }
    public void damage ( int intensity )
    {
        this . kill (); // deletes self from game upon any sort of damage
    }
}
