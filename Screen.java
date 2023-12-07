import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Screen {
    static int boxSize = 64;

    //This method draws the Title Screen.
    public void drawTitleScreen(Graphics g, int width, int height){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        g.setColor(Color.BLACK);
        g.drawString("Amherst Tower Defense",width/2-70, height/2);
        g.drawRect(width/2-20,height*5/8-20,50,30);
        g.drawString("Start",width/2-10,height*5/8);
    }

    //This method draws the screen where the user can choose their map, including drawing smaller renditions of the maps
    public void drawChooseMapScreen(Graphics g,int width, int height){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        g.setColor(Color.green);
        drawMap1Screen(g,boxSize*3/2,boxSize*2,boxSize*3,boxSize*3);
        g.setColor(Color.cyan);
        drawMap2Screen(g,boxSize*13/2,boxSize*2,boxSize*3,boxSize*3);
        g.setColor(Color.blue);
        drawMap3Screen(g,boxSize*23/2,boxSize*2,boxSize*3,boxSize*3);
        g.setColor(Color.BLACK);
        g.drawString("Choose Your Map:",width/2-70,50);
        g.drawRect(boxSize,boxSize*2,boxSize*4,boxSize*3);
        g.drawRect(boxSize*6,boxSize*2,boxSize*4,boxSize*3);
        g.drawRect(boxSize*11,boxSize*2,boxSize*4,boxSize*3);
    }

    //This method draws the screen for the first map.
    public void drawMap1Screen(Graphics g,int x, int y,int width, int height){
        g.drawImage(ImageHolder.map1,x,y,width,height,null);
    }

    //This method draws the screen for the second map.
    public void drawMap2Screen(Graphics g,int x, int y,int width, int height){
        g.drawImage(ImageHolder.map2,x,y,width,height,null);
    }
    //This method draws the screen for the third map.
    public void drawMap3Screen(Graphics g,int x, int y,int width, int height){
        g.drawImage(ImageHolder.map3,x,y,width,height,null);
    }

    public void drawShopScreen(Graphics g){
        g.drawImage(ImageHolder.shop,768,0,256,768,null);
        int width = TowerDefense.WIDTH - 256;
        //These two lines draw the life counter next to the shop label
        drawLifeToken(g,width+boxSize*17/8,1);
        g.drawString(Integer.toString(TowerDefense.lives),width+boxSize*21/8,20);
        //These two lines draw the coin counter under the life counter
        drawCoin(g,width+boxSize*17/8,boxSize/2);
        g.drawString(Integer.toString(TowerDefense.coins),width+boxSize*21/8,boxSize/2 + 20);
        //draws 6 coins in a grid.
        int spacing = 15;
        for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 3;j++) {
                drawCoin(g,width +(j)*boxSize + (j+1)*spacing - 5,(i+1) * (boxSize + spacing) + (i-1)*boxSize - 5);
            }
        }
        //this oval outlines the top left shop bubble
        g.setColor(Color.blue);
        g.drawOval(784, 87, 63, 63);
    }

    static void drawCoin(Graphics g,int x,int y){
        g.drawImage(ImageHolder.coin,x,y,boxSize/2,boxSize/2,null);
    }

    static void drawLifeToken(Graphics g,int x,int y){
        g.drawImage(ImageHolder.heart,x,y,boxSize/2,boxSize/2,null);
    }

    public void drawGameOverScreen(Graphics g,int width,int height){
        g.drawImage(ImageHolder.gameOver,0,0,1024,768,null);
        if(TowerDefense.playAgain) {
            drawPlayAgainButton(g, width, height);
        }
    }

    static void drawPlayAgainButton(Graphics g,int width, int height){
        g.setColor(Color.BLACK);
        g.drawRect(width/2-30,height*5/8 - 20,85,30);
        g.drawString("Play again?",width/2-20,height*5/8);
    }

}
