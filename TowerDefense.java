import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TowerDefense extends JPanel implements MouseListener, KeyListener, MouseMotionListener {
    //change panel width and height so that we can have a shop panel and game panel
    //which are independent of the final width and height.
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static int popped = 0;
    public static int lives = 10;
    public static int coins = 300;

    //The original bug testing goat
    //static Image image = Toolkit.getDefaultToolkit().getImage("goat.jpg");
    private ButtonHolder buttonHolder;
    static boolean basicTowerPresent;

    //these doubles will keep track of the current x and y position of the mouse)
    static double mouseX = 0;
    static double mouseY = 0;

    //Should these booleans be moved to screen? (we're only creating one "screen" so it should be able to hold them right?0
    //these booleans will keep track of which screen is displaying (i.e. which screen should be drawn)
    Screen screen;
    int boxSize = Screen.boxSize;
    static Level level;

    //If following booleans are true, draws a specific screen. Only one should be true at a time
    static boolean titleScreen = true;
    static boolean chooseMapScreen = false;
    static boolean map1Screen = false;
    static boolean map2Screen = false;
    static boolean map3Screen = false;
    static boolean gameOverScreen = false;

    //if true, draws play again button
    static boolean playAgain = false;


    public TowerDefense(){
        //these three listeners allow us to take input from the mouse and keyboard
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        //these Objects are the defaults we need to create to set up all of the little pieces in the game
        screen = new Screen();
        level = new Level(0);
        buttonHolder = new ButtonHolder();
        //this sets up the JPanel and gets everything started
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }

    //This in the main method, duh
    public static void main(String[] args){
        //here we create the instance of TowerDefense and move the focus from "main" to "TowerDefense"
        JFrame frame = new JFrame("Amherst Tower Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TowerDefense mainInstance = new TowerDefense();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
    }

    //draws all the different parts of the Tower Defense Game
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        // let's replace these if/else statements with a switch(case) setup
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
            screen.drawShopScreen(g);
        }

        if(basicTowerPresent) {
            g.drawRect((int) mouseX, (int)mouseY, 50, 50);
        }

    }

    //updates the components of the game
    public void update(){
        level.update(1.0 / (double) FPS);
        //this will end the game once you've run out of lives
        if(lives == 0){
            endGame();
        }
    }

    //this will reset the game so that it can be played again
    static void resetGame(){
        titleScreen = false;
        chooseMapScreen = true;
        map1Screen = false;
        map2Screen = false;
        map3Screen = false;
        gameOverScreen = false;
        playAgain = false;
        //sets the level number back to zero
        TowerDefense.level = new Level(0);
        TowerDefense.lives = 20;
        TowerDefense.coins = 300;
    }

    //loads Game Over screen and gets rid of extra balloons
    static void endGame(){
            level.balloon = null;
            gameOverScreen = true;
            map1Screen = false;
            map2Screen = false;
            map3Screen = false;
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


// I (Beck) think that a mouse-related class would be good to hold all of these things ...
// In the end, let's empty out the methods that aren't used in our game
//the following are all of the methods that must be overridden from the three imported Listeners
    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        System.out.println("The mouse has been clicked at " + x + ", " + y + ".");
        buttonHolder.handleButtonClick(x, y);

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
    public void mouseExited(MouseEvent e) {
        //if we use click/drag mechanics, this is important so the program doesn't crash when the mouse exits the screen
        double x = WIDTH/2;
        double y = HEIGHT/2;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //we need to use this in tandem with mouseExited to get the drag and drop working again
        //on the other hand, we could just cancel the drag and drop when the mouse exits the screen
        double x = e.getX();
        double y = e.getY();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //this will update the current x and y of the mouse as it moves
        double x = e.getX();
        double y = e.getY();
        mouseX = x;
        mouseY = y;

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
