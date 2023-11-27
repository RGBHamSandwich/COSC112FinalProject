//penis
import java.awt.*;

public class Screen {
    static int boxSize = 64;
    Balloon balloon;

    //This method draws the Title Screen.
    public void drawTitleScreen(Graphics g, int width, int height){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(Color.white);
        g.drawString("Amherst Tower Defense",width/2-70, height/2);
        g.drawRect(width/2-20,height*5/8-20,50,30);
        g.drawString("Start",width/2-10,height*5/8);
    }

    //This method draws the screen where the user can choose their map.
    public void drawChooseMapScreen(Graphics g,int width, int height){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(Color.green);
        drawMap1Screen(g,boxSize*3/2,boxSize*2,boxSize*4,boxSize*3,16);
        g.setColor(Color.cyan);
        drawMap2Screen(g,boxSize*13/2,boxSize*2,boxSize*4,boxSize*3,16);
        g.setColor(Color.blue);
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
        g.setColor(Color.lightGray);
        g.fillRect(x+boxSize,y,boxSize,boxSize*7/2);
        g.fillOval(x+boxSize,y+boxSize*2,boxSize*3,boxSize*3);

        g.fillRect(x+boxSize,y+boxSize*17/2,boxSize,boxSize*7/2);
        g.fillOval(x+boxSize,y+boxSize*7,boxSize*3,boxSize*3);

        g.fillOval(x+boxSize*3,y+boxSize/2,boxSize*3,boxSize*3);
        g.fillOval(x+boxSize*3,y+boxSize*17/2,boxSize*3,boxSize*3);

        g.fillOval(x+boxSize*5,y+boxSize*2,boxSize*3,boxSize*3);
        g.fillOval(x+boxSize*5,y+boxSize*7,boxSize*3,boxSize*3);

        g.fillOval(x+boxSize*7,y+boxSize/2,boxSize*3,boxSize*3);
        g.fillOval(x+boxSize*7,y+boxSize*17/2,boxSize*3,boxSize*3);

        g.fillRect(x+boxSize*9,y+boxSize*2,boxSize,boxSize*8);

        g.setColor(Color.cyan);

        g.fillOval(x + boxSize*2,y + boxSize*3,boxSize,boxSize);
        g.fillRect(x + boxSize*2,y,boxSize,boxSize*7/2);
        g.fillOval(x + boxSize*2,y + boxSize*8,boxSize,boxSize);
        g.fillRect(x + boxSize*2,y + boxSize*17/2,boxSize,boxSize*7/2);

        g.fillOval(x + boxSize*4,y + boxSize*3/2,boxSize,boxSize);
        g.fillRect(x + boxSize*4,y + boxSize*2,boxSize,boxSize*7/2);
        g.fillOval(x + boxSize*4,y + boxSize*19/2,boxSize,boxSize);
        g.fillRect(x + boxSize*4,y + boxSize*8,boxSize,boxSize*2);

        g.fillOval(x + boxSize*6,y + boxSize*3,boxSize,boxSize);
        g.fillRect(x + boxSize*6,y,boxSize,boxSize*7/2);
        g.fillOval(x + boxSize*6,y + boxSize*8,boxSize,boxSize);
        g.fillRect(x + boxSize*6,y + boxSize*17/2,boxSize,boxSize*7/2);

        g.fillOval(x + boxSize*8,y + boxSize*3/2,boxSize,boxSize);
        g.fillRect(x + boxSize*8,y + boxSize*2,boxSize,boxSize*7/2);
        g.fillOval(x + boxSize*8,y + boxSize*19/2,boxSize,boxSize);
        g.fillRect(x + boxSize*8,y + boxSize*8,boxSize,boxSize*2);
    }

    //This method draws the screen for the third map.
    public void drawMap3Screen(Graphics g,int x, int y,int width, int height,int boxSize){
        g.setColor(Color.blue);
        g.fillRect(x,y,width-boxSize*4,height);
        g.setColor(Color.lightGray);
        g.fillRect(x,y+boxSize*6,boxSize*9,boxSize);
        g.fillRect(x+boxSize,y+boxSize*3,boxSize*8,boxSize);
        g.fillRect(x+boxSize,y+boxSize,boxSize*10,boxSize);
        g.drawRect(x+boxSize,y+boxSize,boxSize,boxSize*3);
        g.drawRect(x+boxSize*8,y+boxSize*3,boxSize,boxSize*3);
        g.drawRect(x+boxSize*10,y+boxSize,boxSize,boxSize*8);
        g.fillRect(x+boxSize,y+boxSize*8,boxSize*10,boxSize);
        g.drawRect(x+boxSize,y+boxSize*8,boxSize,boxSize*3);
        g.fillRect(x+boxSize,y+boxSize*10,boxSize*11,boxSize);
    }

    public void drawShopScreen(Graphics g,int width, int height){
        width = width-256;
        g.setColor(Color.lightGray);
        g.fillRect(width,0,boxSize*4,height);
        g.setColor(Color.WHITE);
        g.fillRect(width,0,boxSize*4,boxSize);
        g.setColor(Color.black);
        g.drawRect(width,0,boxSize*4,boxSize);
        g.drawRect(width+boxSize*2,0,boxSize*2,boxSize);
        g.drawString("Shop",width + boxSize -15,boxSize/2);
        //These two lines draw the life counter next to the shop label
        drawLifeToken(g,width+boxSize*17/8,1);
        g.drawString(Integer.toString(TowerDefense.lives),width+boxSize*21/8,20);
        //These two lines draw the coin counter under the life counter
        drawCoin(g,width+boxSize*17/8,boxSize/2);
        g.drawString(Integer.toString(TowerDefense.coins),width+boxSize*21/8,boxSize/2 + 20);
        //These next lines draw the start level button
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
                g.drawOval(width + (j)*boxSize + (j+1)*spacing, i * (boxSize + spacing) + (i-1)*boxSize, boxSize,
                        boxSize);
                g.drawRect(width +(j)*boxSize + (j+1)*spacing,(i+1) * (boxSize + spacing) + (i-1)*boxSize,boxSize,
                        boxSize/2);
                drawCoin(g,width +(j)*boxSize + (j+1)*spacing + 1,(i+1) * (boxSize + spacing) + (i-1)*boxSize + 1);
            }
        }
    }

    static void drawCoin(Graphics g,int x,int y){
        g.setColor(Color.orange);
        g.fillOval(x, y, boxSize/2 - 3,boxSize/2 - 3);
        g.setColor(Color.yellow);
        g.fillOval(x+2, y+2, boxSize/2 - 7,boxSize/2 - 7);
        g.setColor(Color.black);
    }

    static void drawLifeToken(Graphics g,int x,int y){
        g.setColor(Color.red);
        g.fillOval(x, y, boxSize/2 - 3,boxSize/2 - 3);
        g.setColor(Color.pink);
        g.fillOval(x+2, y+2, boxSize/2 - 7,boxSize/2 - 7);
        g.setColor(Color.black);
    }

    public void drawGameOverScreen(Graphics g,int width,int height){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(Color.red);
        g.drawString("Game Over",width/2-20, height/2);
        if(TowerDefense.playAgain) {
            drawPlayAgainButton(g, width, height);
        }
    }

    static void drawPlayAgainButton(Graphics g,int width, int height){
        g.setColor(Color.white);
        g.drawRect(width/2-30,height*5/8 - 20,85,30);
        g.drawString("Play again?",width/2-20,height*5/8);
    }

}
