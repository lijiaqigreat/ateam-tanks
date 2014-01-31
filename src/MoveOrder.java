/*
 * An order class for moving forward and backward
 */

public class MoveOrder extends Order
{
    int direction; // 1 for forward, -1 for back

    public MoveOrder ( int frames, int direction )
    {
        super ( frames );
        this.direction = direction;
    }

    public void execSpecific ( SimpleTank tank )
    {
        tank.setPosition ( new Position ( tank.getPosition(), new Velocity ( tank.getSpeed() * direction, tank.getDirection(), 0 ) ) );
    }
}
