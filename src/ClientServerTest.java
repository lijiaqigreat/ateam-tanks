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

public class ClientServerTest extends Thread
{
    public static void main(String[] args)
    {

        GameServer server = new GameServer(3, 8887);

        GameClient c1 = new GameClient("joe", 8887);
        GameClient c2 = new GameClient("nick", 8887);
        GameClient c3 = new GameClient("nick", 8887);
        GameClient c4 = new GameClient("roxy", 8887);
        GameClient c5 = new GameClient("c5", 8887);

        try {
            sleep(1000);
            c1.push(new JoinClientEvent("localhost"));
            sleep(1000);
            c2.push(new JoinClientEvent("localhost"));
            c3.push(new JoinClientEvent("localhost"));
            sleep(1000);
            c4.push(new JoinClientEvent("localhost"));
            c5.push(new JoinClientEvent("localhost"));

            server.push( new AnnouncementServerEvent("just checking in again"));
            sleep(1000);
            c2.push(new PartClientEvent());
            c3.push(new JoinClientEvent("localhost"));
            sleep(1000);
            server.push( new AnnouncementServerEvent("just checking in"));
        } catch (InterruptedException e) {}

        System.out.println("----- Tests complete");
    }

}
