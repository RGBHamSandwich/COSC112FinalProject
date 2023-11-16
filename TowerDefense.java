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
   public static int moolah = 0;

   Screen screen;

   boolean titleScreen;
    static Button Start = new Button(WIDTH/2-20,HEIGHT*5/8-20,50,30);
    boolean chooseMapScreen;
   boolean map1Screen;
   Button map1ScreenButton = new Button(Screen.boxSize*3/2,Screen.boxSize*2,Screen.boxSize*4,Screen.boxSize*3);
   boolean map2Screen;
   Button map2ScreenButton = new Button(Screen.boxSize*13/2,Screen.boxSize*2,Screen.boxSize*4,Screen.boxSize*3);
   boolean map3Screen;
   Button map3ScreenButton = new Button(Screen.boxSize*23/2,Screen.boxSize*2,Screen.boxSize*4, Screen.boxSize*3);


   public TowerDefense(){
       addKeyListener(this);
       addMouseListener(this);

       titleScreen = true;
       chooseMapScreen = false;
       map1Screen = false;
       map2Screen = false;
       map3Screen = false;
       screen = new Screen();
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
       }
       else if(chooseMapScreen){
           screen.drawChooseMapScreen(g,getWidth(),getHeight());
       }
       else if(map1Screen){
           screen.drawMap1Screen(g,0,0,getWidth(),getHeight(),Screen.boxSize);
           screen.drawShopScreen(g,getWidth(),getHeight());
       }
       else if(map2Screen){
           screen.drawMap2Screen(g,0,0,getWidth(),getHeight(),Screen.boxSize);
           screen.drawShopScreen(g,getWidth(),getHeight());
       }
       else if(map3Screen){
           screen.drawMap3Screen(g,0,0,getWidth(),getHeight(),Screen.boxSize);
           screen.drawShopScreen(g,getWidth(),getHeight());
       }
   }


   class Runner implements Runnable{
       public void run() {
           while(true){
               //sc
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

       if (titleScreen) {
           if (y > Start.top && y < Start.bottom && x > Start.left && x < Start.right) {
               titleScreen = false;
               chooseMapScreen = true;
           }
       }

       if (chooseMapScreen) {
           if ((y > map1ScreenButton.top) && (y < map1ScreenButton.bottom) && (x > map1ScreenButton.left) && (x < map1ScreenButton.right)) {
               chooseMapScreen = false;
               map1Screen = true;
           }
           if ((y > map2ScreenButton.top) && (y < map2ScreenButton.bottom) && (x > map2ScreenButton.left) && (x < map2ScreenButton.right)) {
               chooseMapScreen = false;
               map2Screen = true;
           }
           if ((y > map3ScreenButton.top) && (y < map3ScreenButton.bottom) && (x > map3ScreenButton.left) && (x < map3ScreenButton.right)) {
               chooseMapScreen = false;
               map3Screen = true;
           }
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
   //determine perspective, and use these if the base of the tower is different than where the bullets will be coming from.
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


class Balloons{
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
    //The coordinate for the upper side corner of the button
    double top;
    //The coordinate for the lower side of the button
    double bottom;
    //The coordinate for the left side corner of the button
    double left;
    //The coordinate for the right side of the button
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

    public void ifClicked() {
        //a button could do something interesting!
    }

}

