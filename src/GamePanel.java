
import javax.swing.*;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.List;
import java.awt.geom.*;
import java.awt.Color;

public class GamePanel extends JPanel{
    List<Sprite> sprites;
    Rectangle2D.Double gameViewRect;
    public void updateTransform(Graphics2D g2){
        Rectangle2D.Double rect1=gameViewRect;
        Rectangle2D.Double rect2=new Rectangle2D.Double(0,0,getWidth(),getHeight());
        double rr=0;
        double rx=rect1.width/rect2.width;
        double ry=rect1.height/rect2.height;
        if(rx>ry){
            rr=rx; 
            rect2.y+=(rect2.height-rect1.height/rr)/2; 
            rect2.height=rect1.height/rr; 
        }else{ 
            rr=ry; 
            rect2.x+=(rect2.width-rect1.width/rr)/2; 
            rect2.width=rect1.width/rr; 
        } 
        double dx=-rect1.x/rr+rect2.x;
        double dy=(rect1.y+rect1.height)/rr+rect2.y;
        g2.setTransform(new AffineTransform(1/rr,0,0,-1/rr,dx,dy));
    }
    @Override
    public void paint(Graphics g){
        Graphics2D g2=(Graphics2D) g;
        updateTransform(g2);
        //fill background
        g2.setColor(Color.black);
        g2.fill(gameViewRect);
        for(Sprite sprite:sprites){
            sprite.paint(g2);
        }
    }
}
