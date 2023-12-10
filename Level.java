import java.awt.*;
public class Level {
    PawnTower tower;
    RookTower rookTower;
    BishopTower bishopTower;

    //A linked list of balloons, keeps track of all balloons in a level
    Balloon balloon;

    //tracks which level the player is in
    int levelNum;

    //The position where balloons start in a given map
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
                tower = new PawnTower(Math.random()*768, Math.random()*768, this);
                rookTower = new RookTower(new Pair(Math.random()*768, Math.random()*768));
                bishopTower = new BishopTower(new Pair(Math.random()*768, Math.random()*768));
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
                rookTower.fireBullet(this, time);
                bishopTower.fireBullet(this, time);
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

    //draws all the components of a level
    public void draw(Graphics g) {
        if (balloon != null) {
            balloon.drawComponent(g);
        }
        if (tower != null) {
            tower.drawComponent(g);
            rookTower.drawComponent(g);
            bishopTower.drawComponent(g);
//            for (Bullet b : tower.bullets) {
//                b.drawComponent(g);
//            }
        }
    }
}
