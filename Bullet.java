import java.awt.*;
public class Bullet implements Drawable {
    //I'm thinking we might also need a boolean for "has target" or a boolean for ballons called "is targetted"
    //prolly determined by position of tower
    private Pair position;
    private Pair velocity;
    //damage also determined by tower
    private int damage;
    public Bullet nextBullet;
    private int radius = 5;

    public Bullet(Pair p, int d){
        position = new Pair(p.x, p.y);
        velocity = new Pair(0, 0);
        damage = d;
    }
    public Bullet(Pair p, int d, Pair v){
        position = new Pair(p.x, p.y);
        velocity = new Pair(v.x, v.y);
        damage = d;
    }
    public Rectangle bounds(){
        return new Rectangle((int)this.position.x, (int)this.position.y, radius, radius);
    }
    public Pair getPosition(){return position;}

    public int getDamage() {return damage;}

    public Pair getVelocity() {return velocity;}
    //this setVelocity method is made so that it tracks the position of the "target"
    //the target can be still or moving it doesn't matter
    public void setVelocity(Pair target){
        //this makes it so that we don't have to "predict" the path of the balloon
        this.velocity.x = (target.x-position.x)/0.3;
        this.velocity.y = (target.y-position.y)/0.3;
    }
    public void deadVelocity(Pair zero){
        //this is temporarily being used to display "collision"
        this.velocity = zero;
    }
public void deadVelocity(Balloon target){
    if (this.collides(target)){
        this.velocity = new Pair(0,0);
    }
    if (nextBullet != null){
        nextBullet.deadVelocity(target);
    }
}

    public boolean collides(Balloon target){
        if ((position.x > target.position.x-target.radius-2) && (position.x < target.position.x-target.radius-2)){
            if ((position.y > target.position.y-target.radius-2) && (position.y < target.position.y-target.radius-2)){
                return true;
            }
        }
        return false;
    }

    public void update(double time) {
        position = position.add(velocity.times(time));
        if (nextBullet == null && position.x < 10) {
            // this is where I would make the next bullet shoot
            nextBullet = new Bullet(new Pair(300, 80), 5, new Pair(-200, 0));
        }
        if (nextBullet != null) {
            nextBullet.update(time);
        }
    }

    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
        if (nextBullet !=null){
            nextBullet.drawComponent(g);
        }
    }
}

