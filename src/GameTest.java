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
 * A new core test for the Game/Player structure,
 * as well as the dummy ui. This test is acting in
 * place of the main gui menu that will eventually
 * be written to launch the games.
 */

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class GameTest
{
    public static void main ( String args[] )
    {
        test2();
    }
    public static void test1(){
        System.out.println ( "/// GameTest Starting ///\n" );

        int framesPerTurn = 10;
        int turnLimit = 3;
        int mapsize = 200;

        DummyUI ui = new DummyUI ();

        SpriteList sprites = new SpriteList();
        ArrayList<SimpleTank> p1tanks = new ArrayList<SimpleTank>();
        ArrayList<SimpleTank> p2tanks = new ArrayList<SimpleTank>();
        SimpleTank t1 = new SimpleTank ( sprites, p1tanks, new Vector3D ( 20, 20, 0 ), new Direction ( 60 ), 5, 5, Color.red );
        SimpleTank t2 = new SimpleTank ( sprites, p2tanks, new Vector3D ( 50, 20, 0 ), new Direction ( 90 ), 10, 5, Color.blue );
        Obstacle o1 = new Obstacle ( sprites, new Vector3D ( 100, 40, 0 ), new Direction ( 0 ), new HitBox ( 5, 5, 5 ), Color.black );

        sprites.add ( t1 );
        sprites.add ( t2 );
        sprites.add ( o1 );
        p1tanks.add ( t1 );
        p2tanks.add ( t2 );

        ArrayList<Player> players = new ArrayList<Player>();
        players.add ( new HumanPlayer ( ui, "player1", p1tanks, Color.red ) );
        players.add ( new HumanPlayer ( ui, "player2", p2tanks, Color.blue ) );

        Game game = new Game ( players, sprites, ui, framesPerTurn, turnLimit, mapsize );

        System.out.println ( "//  Game running now  //\n" );

        int result = game.run ();

        System.out.println ( "/// Game finished with code " + result + " ///\n" );

    }
    public static void test2(){
        System.out.println ( "/// GameTest Starting ///\n" );

        int framesPerTurn = 20;
        int turnLimit = 3;
        int mapsize = 200;

        GamePanel ui=new GamePanel();
        JFrame frame=new JFrame("ateam-tanks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.add(ui,BorderLayout.CENTER);
        frame.setVisible(true);

        System.out.println ( "/// GameTest Starting ///\n" );


        SpriteList sprites = new SpriteList();
        ArrayList<SimpleTank> p1tanks = new ArrayList<SimpleTank>();
        ArrayList<SimpleTank> p2tanks = new ArrayList<SimpleTank>();
        SimpleTank t1 = new SimpleTank ( sprites, p1tanks, new Vector3D ( 20, 20, 50 ), new Direction ( 60 ), 5, 5, Color.red );
        SimpleTank t2 = new SimpleTank ( sprites, p2tanks, new Vector3D ( 50, 20, 50 ), new Direction ( 90 ), 10, 5, Color.blue );
        Obstacle o1 = new Obstacle ( sprites, new Vector3D ( 100, 40, 0 ), new Direction ( 0 ), new HitBox ( 5, 5, 5 ), Color.white );

        sprites.add ( t1 );
        sprites.add ( t2 );
        sprites.add ( o1 );
        p1tanks.add ( t1 );
        p2tanks.add ( t2 );

        ArrayList<Player> players = new ArrayList<Player>();
        players.add ( new HumanPlayer ( ui, "player1", p1tanks, Color.red ) );
        players.add ( new HumanPlayer ( ui, "player2", p2tanks, Color.red ) );

        Game game = new Game ( players, sprites, ui, framesPerTurn, turnLimit, mapsize );

        System.out.println ( "//  Game running now  //\n" );

        int result = game.run ();

        System.out.println ( "/// Game finished with code " + result + " ///\n" );


    }
    public static void debug(String s){
        System.out.println(s);
    }
}
