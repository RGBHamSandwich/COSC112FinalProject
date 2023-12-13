import java.awt.*;

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
            if (Main.map1Screen || Main.map2Screen) {
                startPosition = new Pair(96, -20);
                balloon = new Balloon(startPosition, Color.red, 1, true);
            }
            else if (Main.map3Screen) {
                startPosition = new Pair(-20, 416);
                balloon = new Balloon(startPosition, Color.red, 1, false);
            }
        }
    }
    //this helps make the code in the big update loop look a little cleaner and more concise
    public void updateTowers(double time){
        for (Tower tower : Main.towers){
            if (tower != null){
                tower.fireBullet(this, time);
            }
        }
    }

    public void update(double time) {
        if (balloon != null) {
            if (Main.map1Screen) {
                balloon.updateMap1(time, levelNum);
                updateTowers(time);
            }
            if (Main.map2Screen) {
                balloon.updateMap2(time, levelNum);
                updateTowers(time);
            }
            if (Main.map3Screen) {
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
                    Main.lives--;
                }
            }
        }
    }

    //this draws all the components of a level
    public void draw(Graphics g) {
        if (balloon!= null){
            balloon.drawComponent(g);
        }
        for (Tower tower : Main.towers){
            if (tower != null){
                tower.drawComponent(g);
            }
        }
    }
}
