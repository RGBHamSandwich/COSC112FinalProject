import java.awt.*;

public class Level {
    Balloon balloon;
    int levelNum;
    static Pair startPosition;

    public Level(int levelNum, TowerDefense towerDefense){
        this.levelNum = levelNum;
        if(levelNum == 0){
            balloon = null;
        }
        else{
            if(towerDefense.map1Screen||towerDefense.map2Screen) {
                startPosition = new Pair(96,-Balloon.radius);
                balloon = new Balloon(startPosition.x,startPosition.y, Color.red, 1,true);
            }
            else if(towerDefense.map3Screen){
                startPosition = new Pair(-Balloon.radius,416);
                balloon = new Balloon(startPosition.x,startPosition.y, Color.red, 1,false);
            }
        }
    }

    public void update(double time, TowerDefense towerDefense){
        if(balloon != null) {
            if(towerDefense.map1Screen) {
                balloon.updateMap1(time,levelNum);
            }
            if(towerDefense.map2Screen){
                balloon.updateMap2(time,levelNum);
            }
            if(towerDefense.map3Screen){
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