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

import java.awt.Color;
import java.awt.Graphics2D;

public class SimpleBulletExplosion extends Sprite
{
    int frames;
    Color c;

    public SimpleBulletExplosion ( SpriteList sprites, Vector3D position )
    {
        super ( sprites, position, new Direction (0, 0), 4 );
        frames = 9;
        c = Color.yellow;
    }

    public int update ()
    {
        switch ( frames )
        {
            case 9:
                for ( Sprite coll : this . getAllCollisions () )
                {
                    coll . damage ( 40 );
                }
                break;
            case 8:
            case 7:
                break;
            case 6:
            case 5:
            case 4:
                c = Color.orange;
                this . hitboxRadius = 3;
                break;
            case 3:
            case 2:
            case 1:
                c = Color.red;
                this . hitboxRadius = 2;
                break;
            case 0:
                this . kill ();
                break;
            default:
                break;
        }

        frames --;

        return 1;
    }

    public void damage ( int intensity )
    {
        // you can't hurt an explosion!
        // explosion hurt you!
    }

    public void paint ( Graphics2D g )
    {
        double radius = this . hitboxRadius;
        g . setColor ( this . c );
        g . fill ( Sprite.getCircle(position.getX(),position.getY(),radius ) );
    }
}
