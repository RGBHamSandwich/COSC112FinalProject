import java.awt.*;

public class Bullet implements Drawable {
    private Pair position;
    private Pair velocity;
    int radius = 3;
    public Bullet(Pair p, Pair v){
        position = new Pair(p.x, p.y);
        velocity = new Pair(v.x, v.y);
    }
    public Pair getPosition(){return position;}
    public Pair getVelocity() {return velocity;}
    public void update(double time) {
        position = position.add(velocity.times(time));
    }
    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
    }
}
