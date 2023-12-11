import java.awt.*;
import java.util.ArrayList;
import java.util.List;


abstract class Tower implements Drawable{
    Pair position;
    //determine perspective, and use these if the base of the tower is different from where the bullets will be coming from.
//    int type;
    List<Bullet> bullets;
    Pair ogBulletVel;
    Pair originalBulletPosition;

    //implement a purchase/upgrade button for each tower?
    public Pair determineVelocity(Pair target){
        return new Pair((target.x-position.x), (target.y-position.y));
    }

    public void fireBullet(Level l, double time) {
        for (int bullet = 0; bullet < this.bullets.size(); bullet++){
            Balloon temp = l.balloon;
            while (temp != null){
                if (temp.isShot(this.bullets.get(bullet)) && bullets.get(bullet).radius !=0) {
                    //we stop drawing it
                    temp.radius = 0;
                    bullets.get(bullet).radius = 0;
                    TowerDefense.popped++;
                    TowerDefense.coins++;
                    this.bullets.get(bullet).damage--;
                    if (temp.nextBalloon == null) {
                        //this part I need Kiara to look at cuz rn im just creating rank 1 balloons - Hena
                        temp = new Balloon(l.startPosition.x, l.startPosition.y, temp.color, temp.rank + 1, true);
                        //(i wanna mess around w this)

//                        temp = temp.determineLevel(l.levelNum);
                    } else {
                        temp = temp.nextBalloon;
                    }
                }
                temp = temp.nextBalloon;
            }

            //instead of some arbitrary number (10) I need to make a variable that keeps track of the og position of the bullet
            if (Math.sqrt(Math.pow(bullets.get(bullet).getPosition().x - originalBulletPosition.x, 2) + Math.pow(bullets.get(bullet).getPosition().y - originalBulletPosition.y, 2)) > 300){
                bullets.add(new Bullet(originalBulletPosition, 1, bullets.get(bullet).getVelocity()));
                bullets.remove(bullet);
            }
            this.bullets.get(bullet).update(time);
        }
    }
//    public abstract void addBullets(int index);


    @Override
    public void drawComponent(Graphics g) {
        for (Bullet bullet : bullets){
            bullet.drawComponent(g);
        }

    }

}


class PawnTower extends Tower {
    public PawnTower(Pair p, Level l) {
        position = new Pair(p.x, p.y);
        bullets = new ArrayList<>();
        originalBulletPosition = new Pair(p.x + 31, p.y+30);
        //need a method that determines the og velocity of the bullet
        bullets.add(new Bullet(originalBulletPosition, 1, new Pair(0, -350)));

    }
    @Override
    public void fireBullet(Level l, double time) {
        super.fireBullet(l, time);
    }//this might be unnecessary
    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
        g.drawImage(ImageHolder.tower1,(int)position.x,(int)position.y,64,64,null);
    }

}

class BishopTower extends Tower {
    public BishopTower(Pair p){
        position = new Pair(p.x, p.y);
        originalBulletPosition = new Pair(position.x+30, position.y+30);
        bullets = new ArrayList<>();
        int randomFireDirection = 1 + (int)(Math.random()*4);
        switch (randomFireDirection){
            case 1:
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(-250, 250)));
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(250, 250)));
                break;
            case 2:
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(250, 250)));
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(250, -250)));
                break;
            case 3:
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(250, -250)));
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(-250, -250)));
                break;
            case 4:
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(-250, 250)));
                bullets.add(new Bullet(originalBulletPosition, 1, new Pair(-250, -250)));
                break;
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
        position = new Pair(p.x, p.y);
        originalBulletPosition = new Pair(position.x+30, position.y+30);
        bullets = new ArrayList<>();
        bullets.add(new Bullet(originalBulletPosition, 1, new Pair(-350, 0)));
        bullets.add(new Bullet(originalBulletPosition, 1, new Pair(350, 0)));
        bullets.add(new Bullet(originalBulletPosition, 1, new Pair(0, 350)));
        bullets.add(new Bullet(originalBulletPosition, 1, new Pair(0, -350)));
    }
    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
        g.drawImage(ImageHolder.tower3,(int)position.x,(int)position.y,64,64,null);
    }
}
