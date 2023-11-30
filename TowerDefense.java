import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TowerDefense extends JPanel implements MouseListener, KeyListener {
    //change panel width and height so that we can have a shop panel and game panel
    //which are independent of the final width and height.
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static int popped = 0;
    public static int lives = 1;
    public static int coins = 300;

    static Image image = Toolkit.getDefaultToolkit().getImage("goat.jpg");
    private ButtonHolder buttonHolder;


    //these booleans and buttons create a relationship between buttons and screen to determine which screens are displaying
    Screen screen;
    int boxSize = Screen.boxSize;
    static Level level;
    static boolean titleScreen = true;
    static boolean chooseMapScreen = false;
    static boolean map1Screen = false;
    static boolean map2Screen = false;
    static boolean map3Screen = false;
    static boolean gameOverScreen = false;
    static boolean playAgain = false;


    public TowerDefense(){
        addKeyListener(this);
        addMouseListener(this);
        screen = new Screen();
        level = new Level(0);
        buttonHolder = new ButtonHolder();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }


    public static void main(String[] args){
        JFrame frame = new JFrame("Amherst Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TowerDefense mainInstance = new TowerDefense();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);


        if (titleScreen){
            screen.drawTitleScreen(g,getWidth(),getHeight());
            //this is the OG bugtesting goat; leave him be. <3
//            g.drawImage(TowerDefense.image, 10, 10, this);
        }
        else if(chooseMapScreen){
            screen.drawChooseMapScreen(g,getWidth(),getHeight());
        }
        else if(gameOverScreen){
            screen.drawGameOverScreen(g,getWidth(),getHeight());
        }
        else{
            if(map1Screen){
                screen.drawMap1Screen(g,0,0,768,768);
            }
            else if(map2Screen){
                screen.drawMap2Screen(g,0,0,768,768);
            }
            else if(map3Screen){
                screen.drawMap3Screen(g,0,0,768,768);
            }
            level.draw(g);
            screen.drawShopScreen(g,getWidth(),getHeight());
        }
    }

    public void update(){
        level.update(1.0 / (double) FPS);
        if(lives == 0){
            level.balloon = null;
            gameOverScreen = true;
            map1Screen = false;
            map2Screen = false;
            map3Screen = false;
        }
    }


    class Runner implements Runnable{
        public void run() {
            while(true){
                update();
                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }
    }




    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        System.out.println("The mouse has been clicked at " + x + ", " + y + ".");

        buttonHolder.handleButtonClick(x, y);

        if(gameOverScreen){             //Beck, make sure to come back to this to figure out what's happening ~ Beck
            if(!playAgain) {
                playAgain = true;
            }
//            else if ((y > playAgainButton.top) && (y < playAgainButton.bottom) && (x > playAgainButton.left) && (x < playAgainButton.right)) {
//                gameOverScreen = false;
//                chooseMapScreen = true;
//                playAgain = false;
//                level.levelNum = 0;
//                lives = 100;
//                coins = 300;
//            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();


    }

    @Override
    public void mouseExited(MouseEvent e) {
        //make sure to think about this method in case someone can't figure out drag/drop
        double x = e.getX();
        double y = e.getY();


    }

    @Override
    public void keyTyped(KeyEvent e) {
        char k = e.getKeyChar();
        System.out.println("The key " + k + " has been pressed.");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char k = e.getKeyChar();

    }


    @Override
    public void keyReleased(KeyEvent e) {
        char k = e.getKeyChar();

    }


}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//This is the end of the TowerDefense Class


abstract class Tower{
    double x;
    double y;
    double damage;
    //determine perspective, and use these if the base of the tower is different from where the bullets will be coming from.
    double fireX;
    double fireY;
    int type;


    //implement a purchase/upgrade button for each tower?


    public void fireBullet(double fireX, double fireY, int damage) {
        //add to an arrayList of bullets?
    }


    public void drawTower(int type, Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, 50, 50);
        if (type == 1) BasicTower.drawBasicTower();
        //I'm not sure if x, y will work how I'm thinking they'll work ngl
    }
}


class BasicTower extends Tower {




    public BasicTower(double x, double y) {
        this. x = x;
        this.y = y;
        damage = 1;
        type = 1;
    }


    @Override
    public void fireBullet(double fireX, double fireY, int damage) {
        super.fireBullet(fireX, fireY, (int) this.damage);
    }


    @Override
    public void drawTower(int type, Graphics g) {
        super.drawTower(this.type, g);
    }


    public static void drawBasicTower() {
        //here we can add the "basic" graphics to the tower
    }
}

class Pair{
    public double x;
    public double y;
    public Pair(double initX, double initY){
        x = initX;
        y = initY;
    }


    public Pair add(Pair toAdd){
        return new Pair(x + toAdd.x, y + toAdd.y);
    }


    public Pair divide(double denominator){
        return new Pair(x / denominator, y / denominator);
    }


    public Pair times(double val){
        return new Pair(x * val, y * val);
    }
}


class Button {
    double x;
    double y;
    double width;
    double height;
    //The (y) coordinate for the upper side corner of the button
    double top;
    //The (y) coordinate for the lower side of the button
    double bottom;
    //The (x) coordinate for the left side corner of the button
    double left;
    //The (x) coordinate for the right side of the button
    double right;

    public Button(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        right = x + width;
        left = x;
        bottom = y + height;
        top = y;

    }

    public void ifClicked(int num) {
//      if a button is clicked, these are the functions
        if (num == 0) {
            TowerDefense.titleScreen = false;
            TowerDefense.chooseMapScreen = true;
        }
        if (num == 1) {
            TowerDefense.chooseMapScreen = false;
            TowerDefense.map1Screen = true;
        }
        if (num == 2) {
            TowerDefense.chooseMapScreen = false;
            TowerDefense.map2Screen = true;
        }
        if (num == 3) {
            TowerDefense.chooseMapScreen = false;
            TowerDefense.map3Screen = true;
        }
        if (num == 4) {
            if (TowerDefense.map1Screen || TowerDefense.map2Screen || TowerDefense.map3Screen){
                if (TowerDefense.level.balloon == null) {
                    System.out.println(TowerDefense.level.levelNum);
                    TowerDefense.level.levelNum++;
                    TowerDefense.level = new Level(TowerDefense.level.levelNum);
                    System.out.println(TowerDefense.level.levelNum);
                }
            }
        }
        if (num == 5) {
            TowerDefense.gameOverScreen = false;
            TowerDefense.chooseMapScreen = true;
            TowerDefense.playAgain = false;
            TowerDefense.level.levelNum = 0;
            TowerDefense.lives = 100;
            TowerDefense.coins = 300;
        }
        else if(num == 6){
            if(TowerDefense.gameOverScreen && !TowerDefense.playAgain){
                TowerDefense.playAgain = true;
            }
        }
    }

}

 class ButtonHolder {
    Button[] buttonArray;
    int WIDTH = TowerDefense.WIDTH;
    int HEIGHT = TowerDefense.HEIGHT;
    int boxSize = Screen.boxSize;

    public ButtonHolder() {
        buttonArray = new Button[10]; // here we can edit the number of buttons

        //this is the Start button
        buttonArray[0] = new Button(WIDTH/2-20,HEIGHT*5/8-20,50,30);
        //these are the buttons to choose between map 1, 2, and 3
        buttonArray[1] = new Button(boxSize*3/2,boxSize*2, boxSize*4,boxSize*3);
        buttonArray[2] = new Button(boxSize*13/2,boxSize*2, boxSize*4,boxSize*3);
        buttonArray[3] = new Button(boxSize*23/2,boxSize*2, boxSize*4, boxSize*3);
        //this is the button to start the level
        buttonArray[4] = new Button(WIDTH - 256,HEIGHT - boxSize, boxSize*4, boxSize);
        //this is the "play again" button
        buttonArray[5] = new Button(WIDTH/2-30,HEIGHT*5/8 - 20,85,30);
        buttonArray[6] = new Button(0,0,WIDTH,HEIGHT);
    }

    public void handleButtonClick(double x, double y) {
        //this will help us figure out if clicks are on the button
        for (int i = 0; i < buttonArray.length; i++) {
            Button button = buttonArray[i];
            if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
                button.ifClicked(i); // Perform button-specific actions
                break; // Assuming only one button can be clicked at a time
            }
        }
    }
}