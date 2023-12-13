import java.awt.*;
import java.util.ArrayList;

public class Level {
    //A linked list of balloons, keeps track of all balloons in a level
    Balloon balloon;
    //tracks which level the player is in
    int levelNum;
    //The position where balloons start in a given map
    static Pair startPosition = new Pair(0, 0);

    public Level(int levelNum) {
        this.levelNum = levelNum;
        if (levelNum == 0) {
            balloon = null;
        } else {
            if (TowerDefense.map1Screen || TowerDefense.map2Screen) {
                startPosition = new Pair(96, -20);
                balloon = new Balloon(startPosition, Color.red, 1, true);
            }
            else if (TowerDefense.map3Screen) {
                startPosition = new Pair(-20, 416);
                balloon = new Balloon(startPosition, Color.red, 1, false);
            }
        }
    }
    //this helps make the code in the big update loop look a little cleaner and more concise
    public void updateTowers(double time){
        for (Tower tower : TowerDefense.towers){
            if (tower != null){
                tower.fireBullet(this, time);
            }
        }
    }

    public void update(double time) {
        if (balloon != null) {
            if (TowerDefense.map1Screen) {
                balloon.updateMap1(time, levelNum);
                updateTowers(time);
            }
            if (TowerDefense.map2Screen) {
                balloon.updateMap2(time, levelNum);
                updateTowers(time);
            }
            if (TowerDefense.map3Screen) {
                balloon.updateMap3(time, levelNum);
                updateTowers(time);
            }
            //this conditional tracks the balloons that have cleared the screen and subtracts lives accordingly
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

    //this draws all the components of a level
    public void draw(Graphics g) {
        if (balloon!= null){
            balloon.drawComponent(g);
        }
        for (Tower tower : TowerDefense.towers){
            if (tower != null){
                tower.drawComponent(g);
            }
        }
    }
}
