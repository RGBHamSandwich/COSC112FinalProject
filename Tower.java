import java.awt.*;
import java.util.ArrayList;
import java.util.List;


abstract class Tower implements Drawable{
    Pair position;
    //determine perspective, and use these if the base of the tower is different from where the bullets will be coming from.
    List<Bullet> bullets;
    Pair originalBulletPosition;

    public void fireBullet(Level l, double time) {
        for (int bullet = 0; bullet < this.bullets.size(); bullet++){
            Balloon temp = l.balloon;
            while (temp != null){
                if (temp.isShot(this.bullets.get(bullet)) && bullets.get(bullet).radius !=0) {
                    //we stop drawing it
                    temp.radius = 0;
                    bullets.get(bullet).radius = 0;
                    TowerDefense.popped++;
                    TowerDefense.coins += 10;
                    if (temp.nextBalloon == null) {
                        temp = new Balloon(l.startPosition.x, l.startPosition.y, temp.color, temp.rank + 1, true);
//                        temp = temp.determineLevel(l.levelNum);
                    } else {
                        temp = temp.nextBalloon;
                    }
                }
                temp = temp.nextBalloon;
            }
            if (Math.sqrt(Math.pow(bullets.get(bullet).getPosition().x - originalBulletPosition.x, 2) + Math.pow(bullets.get(bullet).getPosition().y - originalBulletPosition.y, 2)) > 400){
                bullets.add(new Bullet(originalBulletPosition, bullets.get(bullet).getVelocity()));
                bullets.remove(bullet);
            }
            this.bullets.get(bullet).update(time);
        }
    }
    @Override
    public void drawComponent(Graphics g) {
        for (Bullet bullet : bullets){
            bullet.drawComponent(g);
        }

    }

}


class PawnTower extends Tower {
    public PawnTower(Pair p) {
        position = new Pair(p.x, p.y);
        bullets = new ArrayList<>();
        originalBulletPosition = new Pair(p.x + 31, p.y+30);
        bullets.add(new Bullet(originalBulletPosition, new Pair(0, -400)));

    }
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
        position = new Pair(p.x, p.y);
        originalBulletPosition = new Pair(position.x+30, position.y+30);
        bullets = new ArrayList<>();
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
        position = new Pair(p.x, p.y);
        originalBulletPosition = new Pair(position.x+30, position.y+30);
        bullets = new ArrayList<>();
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
