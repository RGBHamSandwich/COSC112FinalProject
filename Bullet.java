import java.awt.*;

public class Bullet implements Drawable {
    //I'm thinking we might also need a boolean for "has target" or a boolean for ballons called "is targetted"
    //prolly determined by position of tower
    private Pair position;
    private Pair velocity;
    //damage also determined by tower
    private int damage;
    //    public Bullet nextBullet;
    private int radius = 3;
    public Bullet(Pair p, int d, Pair v){
        position = new Pair(p.x, p.y);
        velocity = new Pair(v.x, v.y);
        damage = d;
    }
    public Pair getPosition(){return position;}
    public int getDamage() {return damage;}
    public Pair getVelocity() {return velocity;}
    public void setVelocity(Pair target){
        //using this to determine the initial firing direction of the balloon
        //this is also what we're gonna use if we make "tracking balloons"
        this.velocity.x = (target.x-position.x)/0.3;
        this.velocity.y = (target.y-position.y)/0.3;
    }
    public void update(double time) {
        position = position.add(velocity.times(time));
    }

    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
    }
}
