import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


//Need a start button, a shop, towers,
//Start button should activate the next level, finishing a level should reward money to be used in the shop
//Need to display how much money we have, how much hp we have, and the level number
//Start with three different types of towers (?), differently priced, different capabilities


public class TowerDefenseTest extends JPanel {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    static int boxSize = 64;
    static Map map1 = new Map1();
    static Map map2 = new Map2();
    World world;


    public TowerDefenseTest(){
        world = new World(map1);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Amherst Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TowerDefenseTest mainInstance = new TowerDefenseTest();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        world.draw(g);
        //makest it go faster?
//        world.update(1.0/(double) FPS);
    }


    class Runner implements Runnable{
        public void run() {
            while(true){
                //but i could make it go just as fast by making it 2 instead of one so update in draw is redundant
                world.update(1.0 / (double) FPS);
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }


        }


    }
}


class World{
    Map map;
    BalloonHolder bH;
    Balloon balloon;
    List<Bullet> bullets;
    Bullet bullet;
    int hp;


    public World(Map map){
        this.map = map;
        this.balloon = new Balloon(96,0,Color.red,0,100,1);
//        this.bH = new BalloonHolder();
//        bH.append(balloon);
        this.bullet = new Bullet(new Pair(300, 80), 5, new Pair(-200, 0));
        bullets = new ArrayList<>();
        bullets.add(new Bullet(new Pair(300, 80), 5, new Pair(-200, 0)));
    }


    public void draw(Graphics g){
        map.drawComponent(g);
        balloon.draw(g);
//        Node temp = bH.end;
//        while (temp != null){
//            temp.b.draw(g);
//            temp = temp.prev;
//        }
        for (Bullet bullet : bullets) {
            bullet.drawComponent(g);
        }
        bullet.drawComponent(g);
    }


    public void update(double time){
        if(this.map == TowerDefenseTest.map1) {
//            if (balloon.nextBalloon!= null){
//                bullet.deadVelocity(balloon.nextBalloon);
//                System.out.println(23);
//            }
//            if (balloon.shot ) {
//                Balloon temp = balloon;
//                balloon = temp.nextBalloon;
//            }
//            balloon.shot(bullet);
//            if (bullet.nextBullet!= null){
//                System.out.println(2);
//                balloon.shot(bullet.nextBullet);
//            }

//            Node temp = bH.end;
//            while (temp != null){
//                temp.b.updateMap1(temp, time, bH);
//                System.out.println(8);
//                temp = temp.prev;
//            }
            balloon.updateMap1(time);
            bullet.update(time);
            for (Bullet bullet : bullets){
//                if (bullet.getPosition().x <10){
//                    bullets.add(new Bullet(new Pair(300, 80), 5, new Pair(-200, 0)));
//                }
                if (bullet.bounds().intersects(balloon.bounds())){
                    System.out.println(8);
                    bullets.add(new Bullet(new Pair(300, 80), 5, new Pair(-200, 0)));
                }
                if (bullet.collides(balloon)){
                    bullets.add(new Bullet(new Pair(300, 80), 5, new Pair(-200, 0)));
                }
                bullet.update(time);
            }
        }
        else if(this.map == TowerDefenseTest.map2){
            balloon.updateMap2(time);
        }
//        if(balloon.pathCleared){
//            if(balloon.nextBalloon != null) {
//                balloon = balloon.nextBalloon;
//                hp--;
//            }
//            else{
//                hp--;
//            }
//        }
    }
}


abstract class Map implements Drawable{
    static int boxSize = TowerDefenseTest.boxSize;
    static int HEIGHT = TowerDefenseTest.HEIGHT;
    static int WIDTH = TowerDefenseTest.WIDTH;
    Color backgroundColor;

    @Override
    public void drawComponent(Graphics g) {

    }
}
class Map1 extends Map{
    @Override
    public void drawComponent(Graphics g) {
        //gives a dark green background (8,144,0)
        Color darkGreen = new Color(8,144,0);
        backgroundColor = darkGreen;
        g.setColor(backgroundColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.lightGray);
        g.fillRect(boxSize,0,boxSize,HEIGHT-boxSize);
        g.fillRect(boxSize,HEIGHT-(boxSize*2),boxSize*5,boxSize);
        g.fillRect(boxSize*5,boxSize,boxSize,HEIGHT-boxSize*2);
        g.fillRect(boxSize*5,boxSize,boxSize*5,boxSize);
        g.fillRect(boxSize*9,boxSize,boxSize,HEIGHT-boxSize*2);
        g.fillRect(boxSize*9,HEIGHT-(boxSize*2),boxSize*5,boxSize);
        g.fillRect(boxSize*13,boxSize,boxSize,HEIGHT-boxSize*2);
        g.fillRect(boxSize*13,boxSize,boxSize*3,boxSize);
    }
}
class Map2 extends Map{
    @Override
    public void drawComponent(Graphics g) {
        super.drawComponent(g);
    }
}


