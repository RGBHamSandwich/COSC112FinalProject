import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Level {
    BasicTower tower;
    Balloon balloon;
    int levelNum;
    static Pair startPosition;

    // start position can also be used to determine the direction of the bullts firing - hena
    public Level(int levelNum) {
        this.levelNum = levelNum;
        if (levelNum == 0) {
            balloon = null;
        } else {
            if (TowerDefense.map1Screen || TowerDefense.map2Screen) {
                startPosition = new Pair(96, -20);
                balloon = new Balloon(startPosition.x, startPosition.y, Color.red, 1, true);
                //let's move the initialization of the tower outside of this so we can place towers before pressing "start game"
                tower = new BasicTower(Math.random()*768, Math.random()*768, this);
            } else if (TowerDefense.map3Screen) {
                startPosition = new Pair(-20, 416);
                balloon = new Balloon(startPosition.x, startPosition.y, Color.red, 1, false);
//                tower = new BasicTower(300, 80);
            }
        }
    }

    public void update(double time) {
        if (balloon != null) {
            if (TowerDefense.map1Screen) {
                balloon.updateMap1(time, levelNum);
                //for some reason the update gets faster and slower?
                tower.fireBullet(this, time);
                for (int i = 0; i < tower.bullets.size(); i++) {
                    Balloon temp = balloon;
                    double velocity = Math.sqrt(Math.pow(balloon.velocity.x, 2) + Math.pow(balloon.velocity.y, 2));
                    Balloon prev = null;
                    //the location of the while-loop makes it so that I'm checking for collision between any bullet
                    //and any balloon... basically a double for-loop checking every row and col in a double array
                    while (temp != null) {
                        if (temp.isShot(tower.bullets.get(i))) {
                            //we stop drawing it
                            temp.radius = 0;
                            if (temp.nextBalloon == null) {
                                //this part I need Kiara to look at cuz rn im just creating rank 1 balloons - Hena
                                temp = new Balloon(startPosition.x, startPosition.y, Color.red, 1, true);
                            } else {
                                //this line isnt doing much rn.. its more so for if we decide to mess around w
                                //indexes later on
//                                prev.nextBalloon = temp.nextBalloon;
                                temp = temp.nextBalloon;
                            }
                        }
//                        else {
//                            prev = temp;
//                        }
                        temp = temp.nextBalloon;
                    }

                    if (tower.bullets.get(i).getPosition().x < 10) {
                        tower.bullets.remove(i);
                        tower.bullets.add(new Bullet(new Pair(tower.ogBulletPos.x, tower.ogBulletPos.y), 5, tower.ogBulletVel));
                    }
                    tower.bullets.get(i).update(time);
                }
            }
            if (TowerDefense.map2Screen) {
                balloon.updateMap2(time, levelNum);
            }
            if (TowerDefense.map3Screen) {
                balloon.updateMap3(time, levelNum);
            }
            if (balloon.pathCleared) {
                if (balloon.nextBalloon != null) {
                    balloon = balloon.nextBalloon;

                } else {
                    balloon = null;
                }
                if(balloon!= null && balloon.radius != 0) {
                    TowerDefense.lives--;
                }
            }
        }
    }

    public void draw(Graphics g) {
        if (balloon != null) {
            balloon.drawComponent(g);
        }
        if (tower != null) {
            tower.drawComponent(g);
//            for (Bullet b : tower.bullets) {
//                b.drawComponent(g);
//            }
        }
    }
}
