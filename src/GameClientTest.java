
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
import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.BorderLayout;

import java.io.Console;
import java.io.BufferedReader;
import java.io.FileReader;

public class GameClientTest
{

    public static void main ( String args[] )
    {
        DemoPanel ui=new DemoPanel();
        JFrame frame=new JFrame("ateam-tanks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.add(ui,BorderLayout.CENTER);
        frame.setVisible(true);

        FakeClient c = new FakeClient (ui);

        // c . start ();
    }

}
