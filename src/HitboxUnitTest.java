
public class HitboxUnitTest{
    public static boolean test(double w1,double h1,double a1,double x1,double y1,double z1, double d1, double w2,double h2,double a2,double x2,double y2,double z2, double d2,boolean prediction){
        Hitbox b1=new Hitbox(w1,h1,a1);
        Vector3D v1=new Vector3D(x1,y1,z1);
        Hitbox b2=new Hitbox(w2,h2,a2);
        Vector3D v2=new Vector3D(x2,y2,z2);
        boolean hit=Hitbox.ifHit(b1,v1,d1,b2,v2,d2);
        boolean f=hit==prediction;
        System.out.printf("[%s]:%.1f x %.1f x %.1f box at (%.1f, %.1f, %.1f) facing %.1f\n"+
                          "     %.1f x %.1f x %.1f box at (%.1f, %.1f, %.1f) facing %.1f\n"+
                          "     code prediction: %s, human prediction: %s\n",
                          f?"  ":"X ",w1,h1,a1,x1,y1,z1,d1,w2,h2,a2,x2,y2,z2,d2,hit,prediction);

        return f;
    }
    public static void main(String[] args){
        test(1,1,1,0,0,0,0,1,1,1,3,0,0,0,false);
        test(1,1,1,0,0,0,0,1,1,1,0.8,0,0,0,true);
    }

}
