/*
 * An order for turning the tank
 */

public class TurnOrder extends Order
{
    int direction; // 1 is clockwise, -1 is counter-clockwise

    public TurnOrder ( int frames, int direction )
    {
        super ( frames );
        this.direction = direction;
    }

    protected void execSpecific ( SimpleTank tank )
    {
        tank.setDirection ( tank.getDirection () + direction * tank.getHandling () );
    }
}
