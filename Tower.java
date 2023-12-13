import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//although we preciously had tower be an abstract method (since fireBullet and draw were going to be different for each tower)
//we changed to just a normal class that extends drawable after making the methods "universal"
class Tower implements Drawable{
    //represents the position of each tower determined by where the user places (using mouse input)
    Pair position;
    //each tower has its own arraylist of bullets and the number of elements for each tower differs
    List<Bullet> bullets;
    //og bullet position helps us better align the bullets to the images of the towers (w/o it bullets would appear a bit
    //further than tower in the initial draw
    Pair originalBulletPosition;

    public Tower(Pair p){
        position = new Pair(p.x, p.y);
        originalBulletPosition = new Pair(p.x + 31, p.y+30); //centers (basically hides it) bullet in reference to tower
    }

    //fire bullet is the method in which we both update the bullets of each tower, but also the method in which the bullets
    //interact with the balloons
    public void fireBullet(Level l, double time) {
        //the for-loop and while-loop check for all possible collisions between every balloon and bullet
        for (int bullet = 0; bullet < this.bullets.size(); bullet++){
            Balloon temp = l.balloon;
            while (temp != null){
                if (temp.isShot(this.bullets.get(bullet)) && bullets.get(bullet).radius !=0) {//the second boolean statement is to ensure that a bullet doesnt pop multiple balloons
                    //we stop drawing it by making radius zero
                    temp.radius = 0;
                    bullets.get(bullet).radius = 0; //also stop drawing the bullet so as to ensure that each bullet only strikes a single balloon
                    Main.popped++;
                    Main.coins += 10;
                    if (temp.nextBalloon == null) {//if the balloon struck down isn't linked to another balloon, a new balloon is created
                        temp = new Balloon(Level.startPosition,temp.color, temp.rank + 1, true);
                    } else {//other wise, we change the pointer to the balloon it's linked to
                        temp = temp.nextBalloon;
                    }
                }
                temp = temp.nextBalloon;//this line makes sure we iterate through all balloons
            }
            //distance formula helps us limit the range of the bullets
            if (Math.sqrt(Math.pow(bullets.get(bullet).getPosition().x - originalBulletPosition.x, 2) + Math.pow(bullets.get(bullet).getPosition().y - originalBulletPosition.y, 2)) > 400){
                bullets.add(new Bullet(originalBulletPosition, bullets.get(bullet).getVelocity())); //makes sure that the bullets in the way their towers indicate
                bullets.remove(bullet);//once a bullet isnt really "needed" (cuz it's out of range) it gets removed
                //this saves memory (we don't just keep adding more bullets), makes the collision smoother (dont iterate through useless bullets), etc.
            }
            this.bullets.get(bullet).update(time);//makes bullet move
        }
    }
    @Override
    public void drawComponent(Graphics g) {
        for (Bullet bullet : bullets){
            bullet.drawComponent(g);//self explanatory
        }

    }

}

//all towers behave as their respective piece's movement
class PawnTower extends Tower {     //our cheapest, most basic tower.
    public PawnTower(Pair p) {
        super(p);
        bullets = new ArrayList<>();
        bullets.add(new Bullet(originalBulletPosition, new Pair(0, -400))); //all bullets shoot up

    }
    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
        g.drawImage(ImageHolder.tower1,(int)position.x,(int)position.y,64,64,null);
    }
}

class BishopTower extends Tower {
    public BishopTower(Pair p){
        super(p);
        bullets = new ArrayList<>();
        //since i wanted the number of bullets to increase with each tower, the bishop's bullet's velocity is randomly determined
        //to account for both ways of movement w/o shooting 4 bullets
        if (Math.random() < 0.5){
            bullets.add(new Bullet(originalBulletPosition, new Pair(-300, 300)));
            bullets.add(new Bullet(originalBulletPosition, new Pair(300, -300)));
        }
        else {
            bullets.add(new Bullet(originalBulletPosition, new Pair(300, 300)));
            bullets.add(new Bullet(originalBulletPosition, new Pair(-300, -300)));
        }
    }

    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
        g.drawImage(ImageHolder.tower2,(int)position.x,(int)position.y,64,64,null);

    }
}
class RookTower extends Tower{
    public RookTower(Pair p){
        super(p);
        bullets = new ArrayList<>(); //shoots all 4 ways (vertical and horizontal)
        bullets.add(new Bullet(originalBulletPosition, new Pair(-400, 0)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(400, 0)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(0, 400)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(0, -400)));
    }
    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
        g.drawImage(ImageHolder.tower3,(int)position.x,(int)position.y,64,64,null);
    }
}
class QueenTower extends Tower{
    public QueenTower(Pair p){
        super(p);
        bullets = new ArrayList<>(); //shoots all 8 directions
        bullets.add(new Bullet(originalBulletPosition, new Pair(-400, 0)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(400, 0)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(0, 400)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(0, -400)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(-300, 300)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(300, -300)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(300, 300)));
        bullets.add(new Bullet(originalBulletPosition, new Pair(-300, -300)));
    }
    @Override
    public void drawComponent(Graphics g){
        super.drawComponent(g);
        g.drawImage(ImageHolder.tower4,(int)position.x,(int)position.y,64,64,null);

    }
}
