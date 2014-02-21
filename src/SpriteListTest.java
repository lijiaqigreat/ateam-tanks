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

public class SpriteListTest
{
public static void main ( String args[] )
{
    SpriteList list = new SpriteList ();

    System.out.println ( "Size of empty list is " + list.getSprites().size() );

    Obstacle o1 = new Obstacle ( list, new Vector3D(0,0,0), new Direction(0,0), 4 );
    list.add(o1);

    System.out.println ( "Size of empty list is " + list.getSprites().size() );
    list.update();
    System.out.println ( "Size of non-empty list is " + list.getSprites().size() );
    list.remove(o1);
    System.out.println ( "Size of non-empty list is " + list.getSprites().size() );
    list.update();
    System.out.println ( "Size of empty list is " + list.getSprites().size() );
}
}
