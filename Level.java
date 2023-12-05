import java.awt.*;

public class Level {
    Balloon balloon;
    int levelNum;
    static Pair startPosition;

    public Level(int levelNum) {
        this.levelNum = levelNum;
        if (levelNum == 0) {
            balloon = null;
        } else {
            if (TowerDefense.map1Screen || TowerDefense.map2Screen) {
                startPosition = new Pair(96, -20);
                balloon = new Balloon(startPosition.x, startPosition.y, Color.red, 1, true);
            } else if (TowerDefense.map3Screen) {
                startPosition = new Pair(-20, 416);
                balloon = new Balloon(startPosition.x, startPosition.y, Color.red, 1, false);
            }
        }
    }

    public void update(double time){
        if(balloon != null) {
            if(TowerDefense.map1Screen) {
                balloon.updateMap1(time,levelNum);
            }
            if(TowerDefense.map2Screen){
                balloon.updateMap2(time,levelNum);
            }
            if(TowerDefense.map3Screen){
                balloon.updateMap3(time,levelNum);
            }
            if (balloon.pathCleared) {
                if (balloon.nextBalloon != null) {
                    balloon = balloon.nextBalloon;

                } else {
                    balloon = null;
                }
                TowerDefense.lives--;
            }
        }
    }

    public void draw(Graphics g){
        if(balloon != null){
            balloon.draw(g);
        }
    }
}
