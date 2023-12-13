import java.awt.*;

public class Bullet implements Drawable {
    //position of the bullet (depends on the position of its tower)
    private Pair position;
    //velocity of the bullet (also indicated by the tower it "lives" in)
    private Pair velocity;
    int radius = 3; //this is NOT static because we need to tamper with it in fireBullet method

    public Bullet(Pair p, Pair v){  //given by tower
        position = new Pair(p.x, p.y);
        velocity = new Pair(v.x, v.y);
    }

    public Pair getPosition(){return position;}
    public Pair getVelocity() {return velocity;}
    public void update(double time) {
        position = position.add(velocity.times(time));
    }   //updates bullet

    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.black);
        //we dont need to cast to int but since the field is public it's there just in case
        g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
    }
}
