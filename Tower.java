import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


abstract class Tower implements Drawable{
    double x;
    double y;
    double damage;
    //determine perspective, and use these if the base of the tower is different from where the bullets will be coming from.
    int type;
    List<Bullet> bullets;


    //implement a purchase/upgrade button for each tower?


    public void fireBullet(double fireX, double fireY, int damage) {
        //add to an arrayList of bullets?
    }


    @Override
    public void drawComponent(Graphics g) {
        g.setColor(Color.blue);
        //I set these two variables up to test switching from mouseX, mouseY to a stationary point (i.e. placing the tower)
        //feel free to move everything around as needed; these integers will be unnecessary once we actually create a Tower object that has its respective x and y
        //let me know when you're ready for me to swoop in and play with mouse controls again haha
        int tower1PositionX = (int) TowerDefense.mouseX;
        int tower1PositionY = (int)TowerDefense.mouseY;
        g.fillRect(tower1PositionX, tower1PositionY, 50, 50);
        if (type == 1) BasicTower.drawBasicTower();
    }

}


class BasicTower extends Tower {
    public BasicTower(double x, double y) {
        this. x = x;
        this.y = y;
        damage = 1;
        type = 1;
        bullets = new ArrayList<>();
        bullets.add(new Bullet(new Pair(300, 80), 5, new Pair(-350, 0)));
    }


    @Override
    public void fireBullet(double fireX, double fireY, int damage) {
        super.fireBullet(fireX, fireY, (int) this.damage);
    }


    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
    }


    public static void drawBasicTower() {
        //here we can add the "basic" graphics to the tower
    }
}
