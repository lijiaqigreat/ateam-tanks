public class MoveOrder extends Order
{
    int direction;

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
