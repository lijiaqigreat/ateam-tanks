/**
 * An order class for moving forward and backward
 *
 * each frame, it moves tank by <code>tank.getSpeed()()</code> units.
 */

public class MoveOrder extends Order
{
    private int direction; // 1 for forward, -1 for back
    /**
     * @param frames number of frames to turn
     * @param either be 1 to move forward or -1 move backward
     */
    public MoveOrder ( int frames, int direction )
    {
        super ( frames );
        this.direction = direction;
    }
    public int getDirection(){
        return direction;
    }

    public void execSpecific ( SimpleTank tank )
    {
        tank.setPosition ( new Vector3D ( tank.getPosition(), new Vector3D ( tank.getSpeed() * ( double ) direction, tank.getDirection(), new Direction () ) ) );
    }
}
