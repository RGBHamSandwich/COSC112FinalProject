import java.awt.*;
import java.awt.Button;

import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;


public class Screen {
    static int boxSize = 64;
    int TDwidth = TowerDefense.WIDTH;
    int TDheight = TowerDefense.HEIGHT;

    //this draws the title screen
    public void drawTitleScreen(Graphics g){
        g.drawImage(ImageHolder.titleScreen,0,0,TDwidth,TDheight,null);
        ButtonHolder.drawButton(0, g);
        ButtonHolder.drawButton(12, g);
    }

    public void drawInstructionsScreen(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, TDwidth, TDheight);
        g.setColor(Color.white);
//        g.drawString();
        ButtonHolder.drawButton(0, g);

    }

    //This method draws the screen where the user can choose their map, including drawing smaller renditions of the maps
    public void drawChooseMapScreen(Graphics g,int width, int height){
        Color cream = new Color(255,255,235);
        g.setColor(cream);
        g.fillRect(0,0,width,height);
        drawMap1Screen(g,boxSize*3/2,boxSize*2,boxSize*3,boxSize*3);
        drawMap2Screen(g,boxSize*13/2,boxSize*2,boxSize*3,boxSize*3);
        drawMap3Screen(g,boxSize*23/2,boxSize*2,boxSize*3,boxSize*3);
        g.setColor(Color.BLACK);
        g.drawString("Choose Your Map:",width/2-70,50);
        ButtonHolder.drawButton(1, g);
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

    //draws the panel on the right side of the screen for the shop
    public void drawShopScreen(Graphics g){
        g.drawImage(ImageHolder.shop,768,0,256,768,null);
        int width = TDwidth - 256;
        //These two lines draw the life counter next to the shop label
        drawLifeToken(g,width+boxSize*17/8,1);
        g.drawString(Integer.toString(TowerDefense.lives),width+boxSize*21/8,20);
        //These two lines draw the coin counter under the life counter
        drawCoin(g,width+boxSize*17/8,boxSize/2);
        g.drawString(Integer.toString(TowerDefense.coins),width+boxSize*21/8,boxSize/2 + 20);
        //draws 6 coins in a grid.
        int spacing = 5;
        for(int i = 1; i < 3; i++) {
            for(int j = 0; j < 2;j++) {
                g.drawOval(width + (j)*(2*boxSize - spacing) + (j+1)*spacing, i * (boxSize + spacing) + (i-1)*(2*boxSize - 12), 2*(boxSize - spacing), 2*(boxSize - spacing));
                drawCoin(g,boxSize/4 + width +(j)*2*boxSize + (j+1)*spacing - 5, (i+1) * (3*boxSize/2 + spacing) + (i-1)*5*boxSize/4 - 5);
            }
        }

        //the chess icons only appear if they haven't been bought yet (i.e. they're not on the map yet)
        if (!(ButtonHolder.Tower1InAir || ButtonHolder.Tower1Placed)) g.drawImage(ImageHolder.tower1,width + spacing,
                boxSize + spacing, 2*(boxSize - spacing), 2*(boxSize - spacing),null);
        if (!(ButtonHolder.Tower2InAir || ButtonHolder.Tower2Placed))g.drawImage(ImageHolder.tower2,width + (2*boxSize - spacing) + 2*spacing,
                boxSize + spacing, 2*(boxSize - spacing), 2*(boxSize - spacing),null);
        if (!(ButtonHolder.Tower3InAir || ButtonHolder.Tower3Placed))g.drawImage(ImageHolder.tower3,width + spacing,
                2 * (boxSize + spacing) + (2*boxSize - 12),2*(boxSize - spacing), 2*(boxSize - spacing),null);
        if (!(ButtonHolder.Tower4InAir || ButtonHolder.Tower4Placed))g.drawImage(ImageHolder.tower4,width + (2*boxSize - spacing) + 2*spacing,
                2 * (boxSize + spacing) + (2*boxSize - 12), 2*(boxSize - spacing), 2*(boxSize - spacing),null);
        g.drawString(Integer.toString(TowerDefense.popped), width + boxSize*2 + 15,TDheight - boxSize *3/2 + 15);
        g.drawString(ButtonHolder.Tower1Price + "",boxSize/4 + width + spacing + 30,2 * (3*boxSize/2 + spacing) + 15);
        g.drawString(ButtonHolder.Tower2Price + "",width + spacing + 2*boxSize + 50,2 * (3*boxSize/2 + spacing) + 15);
        g.drawString(ButtonHolder.Tower3Price + "",boxSize/4 + width + spacing + 30,2 * (3*boxSize/2 + spacing) + 5 + 3*boxSize);
        g.drawString(ButtonHolder.Tower4Price + "",width + spacing + 2*boxSize + 50,2 * (3*boxSize/2 + spacing) + 5 + 3*boxSize);
    }

    //draws the coin icon at a given position
    static void drawCoin(Graphics g,int x,int y){
        g.drawImage(ImageHolder.coin,x,y,boxSize/2,boxSize/2,null);
    }

    //draws the life icon at the given location
    static void drawLifeToken(Graphics g,int x,int y){
        g.drawImage(ImageHolder.heart,x,y,boxSize/2,boxSize/2,null);
    }

    //draws the game over screen
    public void drawGameOverScreen(Graphics g,int width,int height){
        g.drawImage(ImageHolder.gameOver,0,0,width,height,null);
        if(TowerDefense.playAgain) {
            ButtonHolder.drawButton(6, g);
        }
    }


}
