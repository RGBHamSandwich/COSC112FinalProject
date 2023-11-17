import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Screen {
   static int boxSize = 64;
   Balloons balloon;
    static boolean titleScreen = true;
    static boolean chooseMapScreen = false;
    static boolean map1Screen = false;
    static boolean map2Screen = false;
    static boolean map3Screen = false;

   //This method draws the Title Screen.
   public void drawTitleScreen(Graphics g, int width, int height){
       Button DrawStart = TowerDefense.arrayOfButtons.Start;
       g.setColor(Color.BLACK);
       g.fillRect(0,0,width,height);
       g.setColor(Color.white);
       g.drawString("Amherst Tower Defense",width/2-70, height/2);
       g.drawRect((int) DrawStart.x,(int)DrawStart.y,(int)DrawStart.width,(int)DrawStart.height);
       g.drawString("Start",width/2-10,height*5/8);
       g.setColor(Color.pink);

   }


   //This method draws the screen where the user can choose their map.
   public void drawChooseMapScreen(Graphics g,int width, int height){
       g.setColor(Color.BLACK);
       g.fillRect(0,0,width,height);
       g.setColor(Color.green);
       drawMap1Screen(g,boxSize*3/2,boxSize*2,boxSize*4,boxSize*3,16);
       g.setColor(Color.cyan);
       drawMap2Screen(g,boxSize*13/2,boxSize*2,boxSize*4,boxSize*3,16);
       g.setColor(Color.pink);
       drawMap3Screen(g,boxSize*23/2,boxSize*2,boxSize*4,boxSize*3,16);
       g.setColor(Color.WHITE);
       g.drawString("Choose Your Map:",width/2-70,50);
       g.drawRect(boxSize,boxSize*2,boxSize*4,boxSize*3);
       g.drawRect(boxSize*6,boxSize*2,boxSize*4,boxSize*3);
       g.drawRect(boxSize*11,boxSize*2,boxSize*4,boxSize*3);
   }


   //This method draws the screen for the first map.
   public void drawMap1Screen(Graphics g,int x, int y,int width, int height,int boxSize){
       g.setColor(Color.green);
       g.fillRect(x,y,width-boxSize*4,height);
       g.setColor(Color.lightGray);
       g.fillRect(x+boxSize,y,boxSize,height-boxSize);
       g.fillRect(x+boxSize,y+height-(boxSize*2),boxSize*5,boxSize);
       g.fillRect(x+boxSize*5,y+boxSize,boxSize,height-boxSize*2);
       g.fillRect(x+boxSize*5,y+boxSize,boxSize*5,boxSize);
       g.fillRect(x+boxSize*9,y+boxSize,boxSize,height-boxSize);
   }


   //This method draws the screen for the second map.
   public void drawMap2Screen(Graphics g,int x, int y,int width, int height,int boxSize){
       g.setColor(Color.cyan);
       g.fillRect(x,y,width-boxSize*4,height);
   }


   //This method draws the screen for the third map.
   public void drawMap3Screen(Graphics g,int x, int y,int width, int height,int boxSize){
       g.setColor(Color.pink);
       g.fillRect(x,y,width-boxSize*4,height);
   }


   public void drawShopScreen(Graphics g,int width, int height){
       width = width-256;
       g.setColor(Color.lightGray);
       g.fillRect(width,0,boxSize*4,height);
       g.setColor(Color.WHITE);
       g.fillRect(width,0,boxSize*4,boxSize);
       g.setColor(Color.black);
       g.drawRect(width,0,boxSize*4,boxSize);
       g.drawString("Shop",width + boxSize*2 -15,boxSize/2);
       g.setColor(Color.green);
       g.fillRect(width,height-boxSize,boxSize*4,boxSize);
       g.setColor(Color.black);
       g.drawRect(width,height-boxSize,boxSize*4,boxSize);
       g.drawString("Start Level",width + boxSize*2 -30,height-boxSize/2);
       //draws 9 circles in a grid. The number of rows is the upper bound of i, and there are three per row.
       //the number of rows can be any int between 2 and 9 for circles to appear in the proper area.
       int spacing = 15;
       for(int i = 1; i < 3; i++) {
           for(int j = 0; j < 3;j++) {
               g.drawOval(width + (j)*boxSize + (j+1)*spacing, i * (boxSize + spacing), boxSize, boxSize);
           }
       }
   }


}