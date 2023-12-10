import java.awt.*;
import java.util.ArrayList;
import java.util.List;


abstract class Tower implements Drawable{
    Pair position;
    //determine perspective, and use these if the base of the tower is different from where the bullets will be coming from.
//    int type;
    List<Bullet> bullets;
    Pair ogBulletPos;
    Pair ogBulletVel;


    //implement a purchase/upgrade button for each tower?
    public Pair determineVelocity(Pair target){
        //using this to determine the initial firing direction of the balloon
        //this is also what we're gonna use if we make "tracking balloons"
//        this.velocity.x = (target.x-position.x)/0.3;
//        this.velocity.y = (target.y-position.y)/0.3;
        return new Pair((target.x-position.x), (target.y-position.y));
    }

    public void fireBullet(Level l, double time) {
        for (int bullet = 0; bullet < this.bullets.size(); bullet++){
            Balloon temp = l.balloon;
            while (temp != null){
                if (temp.isShot(this.bullets.get(bullet))) {
                    //we stop drawing it
                    temp.radius = 0;
                    TowerDefense.popped++;
                    TowerDefense.coins++;
                    this.bullets.get(bullet).damage--;
                    if (temp.nextBalloon == null) {
                        //this part I need Kiara to look at cuz rn im just creating rank 1 balloons - Hena
                        temp = new Balloon(l.startPosition.x, l.startPosition.y, Color.red, 1, true);
                        // temp = temp.determineLevel(l.levelNum); (i wanna mess around w this)
                    } else {
                        //this line isnt doing much rn.. its more so for if we decide to mess around w
                        //indexes later on
//                                prev.nextBalloon = temp.nextBalloon;
                        temp = temp.nextBalloon;
                    }
                }
                temp = temp.nextBalloon;
            }

            //instead of some arbitrary number (10) I need to make a variable that keeps track of the og position of the bullet
            if (this.bullets.get(bullet).getPosition().x < ogBulletPos.x - 300){
                this.bullets.remove(bullet);
                this.bullets.add(new Bullet(ogBulletPos, 5, ogBulletVel));
            }
//                this.bullets.get(bullet).setVelocity(new Pair(l.startPosition.x, l.startPosition.y));
            this.bullets.get(bullet).update(time);
        }
    }


    @Override
    public void drawComponent(Graphics g) {
        for (Bullet bullet : bullets){
            bullet.drawComponent(g);
        }
//        g.setColor(Color.blue);
//        //I set these two variables up to test switching from mouseX, mouseY to a stationary point (i.e. placing the tower)
//        //feel free to move everything around/delete as needed; these integers will be unnecessary once we actually implement button 7 to create a tower that has its respective x and y
//        //let me know when you're ready for me to swoop in and play with mouse controls again haha
//        int tower1PositionX = (int) TowerDefense.mouseX;
//        int tower1PositionY = (int)TowerDefense.mouseY;
//        g.fillRect(tower1PositionX, tower1PositionY, 50, 50);
//        if (type == 1) BasicTower.drawBasicTower(g,tower1PositionX,tower1PositionY);
    }

}


class BasicTower extends Tower {
    public BasicTower(double x, double y, Level l) {
        position = new Pair(x, y);
        bullets = new ArrayList<>();
        ogBulletPos = this.position;
        ogBulletVel = this.determineVelocity(new Pair(l.startPosition.x/20, l.startPosition.y/20));
        //need a method that determines the og velocity of the bullet
        bullets.add(new Bullet(ogBulletPos, 1, ogBulletVel));
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
