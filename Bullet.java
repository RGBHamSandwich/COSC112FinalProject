public class Bullet {
    //I'm thinking we might also need a boolean for "has target" or a boolean for ballons called "is targetted"
    //prolly determined by position of tower
    private Pair position;
    private Pair velocity;
    //damage also determined by tower
    private int damage;
    //    public Bullet nextBullet;
    private int radius = 5;
    public Bullet(Pair p, int d, Pair v){
        position = new Pair(p.x, p.y);
        velocity = new Pair(v.x, v.y);
        damage = d;
    }
    public Pair getPosition(){return position;}
    public int getDamage() {return damage;}
    public Pair getVelocity() {return velocity;}
}
