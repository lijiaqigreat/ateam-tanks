/*
 * An initial test of the class structures using a set of defined orders.
 *
 * No graphics -- the tank position is just printed to the screen after each frame.
 */

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class CoreTest
{
    public static void main ( String args[] )
    {
        test2();
    }
    private static void test1(){
        ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        SimpleTank tank1 = new SimpleTank ( sprites, new Vector3D ( 2000, 2000, 0 ), new Direction ( 0 ), 20, 5 );

        OrderQueue q = new OrderQueue();
        q.add ( new MoveOrder ( 10, 1 ) );
        q.add ( new TurnOrder ( 10, 1 ) );
        q.add ( new MoveOrder ( 20, 1 ) );
        q.add ( new MoveOrder ( 5, -1 ) );

        tank1.giveOrders ( q );
        
        for ( int i = 0; i < 200; i ++ )
        {
            for ( Sprite sprite : sprites )
            {
                sprite.update();
                System.out.println ( sprite.getPosition().toString() + " -- " + sprite.getDirection().toString() );
            }
        }
    }
    private static void test2(){
        final ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        SimpleTank tank1 = new SimpleTank ( sprites, new Vector3D ( 2000, 2000, 0 ), new Direction ( 0 ), 20, 5 );

        OrderQueue q = new OrderQueue();
        q.add ( new MoveOrder ( 10, 1 ) );
        q.add ( new TurnOrder ( 10, 1 ) );
        q.add ( new MoveOrder ( 20, 1 ) );
        q.add ( new MoveOrder ( 5, -1 ) );

        tank1.giveOrders ( q );

        final GamePanel panel=new GamePanel();
        panel.sprites=sprites;
        panel.gameViewRect=new Rectangle2D.Double(1000,1000,2000,2000);
        
        JFrame frame=new JFrame("ateam-tanks");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel);
        frame.setVisible(true);
        Timer timer=new Timer(1000,new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(Sprite s:sprites){
                    s.update();
                }
                panel.repaint();
            }
        });
        timer.start();

    }
}
