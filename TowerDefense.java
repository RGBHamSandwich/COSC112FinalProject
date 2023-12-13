import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class TowerDefense extends JPanel {
    //change panel width and height so that we can have a shop panel and game panel
    //which are independent of the final width and height.
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static int popped = 0;
    public static int lives = 20;
    public static int coins = 325;

    //when we create a ButtonHolder, it creates all of the necessary buttons for our project
    private ButtonHolder buttonHolder;

    //these booleans will keep track of which screen is displaying (i.e. which screen should be drawn)
    Screen screen;
    static Level level;

    //If following booleans are true, draws a specific screen. Only one should be true at a time
    static boolean titleScreen = true;
    static boolean instructionsScreen = false;
    static boolean chooseMapScreen = false;
    static boolean map1Screen = false;
    static boolean map2Screen = false;
    static boolean map3Screen = false;
    static boolean gameOverScreen = false;
    //if true, draws play again button
    static boolean playAgain = false;
    //since all maps and levels have the same 4 types of towers, we thought it best for the list of towers to live in the main class
    static List<Tower> towers = new ArrayList<>();



    public TowerDefense(){
        //these three lines allow us to use all of the mouse functions within the MouseFunctions class
        MouseFunctions mouse = new MouseFunctions();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        //these Objects are the defaults we need to create to set up all of the little pieces in the game
        screen = new Screen();
        level = new Level(0);
        buttonHolder = new ButtonHolder();
        //this sets up the JPanel and gets everything started
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        Thread mainThread = new Thread(new Runner());
        mainThread.start();
    }


    //This is the main method, duh
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

        //with these if-statements, we determine which map to draw
        if (titleScreen){
            screen.drawTitleScreen(g);
        }

        else if (instructionsScreen) {
            screen.drawInstructionsScreen(g);
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

        //these are adjusted mouseX and mouseY values so the mouse looks like it's holding the pieces
        int mouseXadj = (int) MouseFunctions.mouseX - 30;
        int mouseYadj = (int) MouseFunctions.mouseY - 15;

        //here's the start of the tower logic
        if (ButtonHolder.Tower1InAir) g.drawImage(ImageHolder.tower1,mouseXadj,mouseYadj,64,64,null);
//        else if (ButtonHolder.Tower1Placed) ButtonHolder.disableShop(g, 8);
        if (ButtonHolder.Tower2InAir) g.drawImage(ImageHolder.tower2,mouseXadj,mouseYadj,64,64,null);
//        else if (ButtonHolder.Tower2Placed) ButtonHolder.disableShop(g, 8);
        if (ButtonHolder.Tower3InAir) g.drawImage(ImageHolder.tower3,mouseXadj,mouseYadj,64,64,null);
//        else if (ButtonHolder.Tower3Placed) ButtonHolder.disableShop(g, 8);
        if (ButtonHolder.Tower4InAir) g.drawImage(ImageHolder.tower4,mouseXadj,mouseYadj,64,64,null);
//        else if (ButtonHolder.Tower4Placed) ButtonHolder.disableShop(g, 8);
    }

    //updates the components of the game
    public void update(){
        level.update(1.0 / (double) FPS);
        //this will end the game once you've run out of lives
        if(lives == 0){
            endGame();
        }
    }

    //depending on the button clicked, a certain tower is created or "called" (indicated by towerCalled)
    //this takes in a Pair "placed" indicating where on the map the player has indicated to place their tower
    public static void createTowers(int towerCalled, Pair placed){
        switch (towerCalled){
            case 1:
                towers.add(new PawnTower(placed));
                break;
            case 2:
                towers.add(new BishopTower(placed));
                break;
            case 3:
                towers.add(new RookTower(placed));
                break;
            case 4:
                towers.add(new QueenTower(placed));
                break;
        }
    }

    //this will reset the game so that it can be played again
    static void resetGame(){
        titleScreen = false;
        instructionsScreen = false;
        chooseMapScreen = true;
        map1Screen = false;
        map2Screen = false;
        map3Screen = false;
        gameOverScreen = false;
        playAgain = false;
        //sets the level number back to zero
        TowerDefense.level = new Level(0);
        TowerDefense.lives = 25;
        TowerDefense.coins = 325;
        TowerDefense.popped = 0;
    }

    //loads the "Game Over" screen and gets rid of extra balloons
    static void endGame(){
            level.balloon = null;
            gameOverScreen = true;
            map1Screen = false;
            map2Screen = false;
            map3Screen = false;
            ButtonHolder.Tower1InAir = false;
            ButtonHolder.Tower2InAir = false;
            ButtonHolder.Tower3InAir = false;
            ButtonHolder.Tower4InAir = false;
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



}