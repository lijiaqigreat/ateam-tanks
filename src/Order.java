public abstract class Order
{
    private int frames;

    public Order ( int frames )
    {
        this.frames = frames;
    }

    public void exec ( SimpleTank tank )
    {
        this.execSpecific ( tank );
        frames --;
    }

    protected abstract void execSpecific ( SimpleTank tank );

    public int getFrames ()
    {
        return frames;
    }
}
