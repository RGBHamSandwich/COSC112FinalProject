import java.awt.*;

public class Level {
//    PawnTower pawnTower;
//    RookTower rookTower;
//    BishopTower bishopTower;

    //A linked list of balloons, keeps track of all balloons in a level
    Balloon balloon;

    //tracks which level the player is in
    int levelNum;

    //The position where balloons start in a given map
    static Pair startPosition = new Pair(0, 0);

    // start position can also be used to determine the direction of the bullets firing - hena
    public Level(int levelNum) {
        this.levelNum = levelNum;
        if (levelNum == 0) {
            balloon = null;
        } else {
            if (TowerDefense.map1Screen || TowerDefense.map2Screen) {
                startPosition = new Pair(96, -20);
                balloon = new Balloon(startPosition.x, startPosition.y, Color.red, 1, true);
                //let's move the initialization of the tower outside of this so we can place towers before pressing "start game"
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
//                if (pawnTower != null){
//                    pawnTower.fireBullet(this, time);
//                }
//                if (bishopTower != null){
//                   bishopTower.fireBullet(this, time);
//                }
//                if (pawnTower != null){
//                    pawnTower.fireBullet(this, time);
//                }
                for (Tower tower : TowerDefense.towers){
                    if (tower != null){
                        tower.fireBullet(this, time);
                    }
                }
                //for some reason the update gets faster and slower?
//                rookTower.fireBullet(this, time);
//                bishopTower.fireBullet(this, time);
            }
            if (TowerDefense.map2Screen) {
                balloon.updateMap2(time, levelNum);
//                if (pawnTower != null){
//                    pawnTower.fireBullet(this, time);
//                }
//                if (bishopTower != null){
//                    bishopTower.fireBullet(this, time);
//                }
            }
            if (TowerDefense.map3Screen) {
                balloon.updateMap3(time, levelNum);
//                if (pawnTower != null){
//                    pawnTower.fireBullet(this, time);
//                }
//                if (bishopTower != null){
//                    bishopTower.fireBullet(this, time);
//                }
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
//        if (pawnTower != null) {
//            pawnTower.drawComponent(g);
//        }
//        if (bishopTower != null){
//            bishopTower.drawComponent(g);
//        }
//        if (rookTower != null){
//            rookTower.drawComponent(g);
//        }

        for (Tower tower : TowerDefense.towers){
            if (tower != null){
                tower.drawComponent(g);
            }
        }
    }
}
