public class TurnOrder extends Order
{
    int direction;

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
