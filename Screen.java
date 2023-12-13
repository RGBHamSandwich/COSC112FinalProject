import java.awt.*;



public class Screen {
    static int boxSize = 64;
    int TDWidth = Main.WIDTH;
    int TDHeight = Main.HEIGHT;

    //this draws the title screen
    public void drawTitleScreen(Graphics g){
        g.drawImage(ImageHolder.titleScreen,0,0, TDWidth, TDHeight,null);
        ButtonHolder.drawButton(0, g);
        ButtonHolder.drawButton(12, g);
    }

    public void drawInstructionsScreen(Graphics g) {
        //we'll use top, left, and inc (short for incrementation) to standardize the spacing and general shape of the instructions!
        int top = 50;
        int left = 100;
        int inc = 20;
        int smallBoxSize = boxSize - 10;
        g.setColor(Color.black);
        g.fillRect(0, 0, TDWidth, TDHeight);
        g.setColor(Color.white);
        g.drawString("Welcome to Amherst Tower Defense!", left, top);
        g.drawString("In our game, you'll be using towers—each representing a distinct chess piece—that unleash a barrage of", left, top + 1*inc);
        g.drawString("    bullets to defend against incoming waves of colorful balloons.\n", left, top + 2*inc);
        g.drawString("First, you'll choose a map, like the one to the right!", left, top + 4*inc);
        g.fillRect(left + 640, top - 10, smallBoxSize*3 + 20, smallBoxSize*3 + 20);
        //this is drawing the first map
        drawMap1Screen(g,left + 650,top,smallBoxSize*3,smallBoxSize*3);

        //these ints are to help with the balloon spacing
        int balloonLeft = left + 640;
        int balloonTop = top + smallBoxSize*3 + 40;
        g.drawString("Once you've chosen your map, it's time to think about your strategy!", left, top + 7*inc);
        g.drawString("On the right side of the screen, you'll see a shop. There, you can use your coins to buy towers to pop", left, top + 8*inc);
        g.drawString("oncoming balloons.  As you pop balloons, you'll get more coins! Check out our tower and balloon designs!", left, top + 9*inc);
        g.drawString("If you have enough coins, simply left-click on a tower to pick it up! Then you can place it on the map as\n", left, top + 10*inc);
        g.drawString("you please.", left, top + 11*inc);
        //this is drawing the balloons and the box they're on
        g.fillRect(balloonLeft + 10, balloonTop, smallBoxSize * 3, 65);
        g.drawImage(ImageHolder.pastelGreenBalloon, balloonLeft + 6*inc, balloonTop, null);
        g.drawImage(ImageHolder.pastelYellowBalloon, balloonLeft + 5*inc, balloonTop, null);
        g.drawImage(ImageHolder.pastelBlueBalloon, balloonLeft + 4*inc, balloonTop, null);
        g.drawImage(ImageHolder.yellowBalloon, balloonLeft + 3*inc, balloonTop, null);
        g.drawImage(ImageHolder.greenBalloon, balloonLeft + 2*inc, balloonTop, null);
        g.drawImage(ImageHolder.blueBalloon, balloonLeft + 1*inc, balloonTop, null);
        g.drawImage(ImageHolder.redBalloon, balloonLeft, balloonTop, null);

        g.drawString("While you can technically place your towers anywhere, keep a couple of things in mind:", left, top + 13*inc);
        g.drawString("Placing a tower on a path doesn't block balloons; they'll just float past you!", left, top + 14*inc);
        g.drawString("Every tower has it's own firing pattern—think about how a chess piece attacks!", left, top + 15*inc);

        g.drawString("If you change your mind about the tower, you can click on the empty bubble in the shop to put it back.", left, top + 17*inc);

        g.drawString("The descriptions of the towers' firing patterns are below, if you want to take a look.", left, top + 19*inc);
        g.drawString("Once the level has timed out (balloons have been shot or have passed by), press \"Start Level\"!", left, top + 20*inc);
        g.drawString("That should be about everything you need to know! Good luck!", left, top + 23*inc);

        //here are the graphics of the towers and their descriptions
        //these are more integers that are only used to help us keep
        int towerTop = balloonTop + 120;
        int towerInc = 20;
        int stringLeft = balloonLeft + 60;
        int rowInc = 60;
        g.fillRect(balloonLeft, towerTop - 26, 200, 330);
        ButtonHolder.drawButton(0, g);

        g.setColor(Color.black);
        g.drawImage(ImageHolder.tower1, balloonLeft, towerTop - 15, null);
        g.drawString("This is the Pawn Tower.", stringLeft, towerTop);
        g.drawString("Pawns fire one stream", stringLeft, towerTop + towerInc);
        g.drawString("     of bullets up.", stringLeft, towerTop + 2*towerInc);

        g.drawImage(ImageHolder.tower2, balloonLeft, towerTop + 65, null);
        g.drawString("This is the Bishop Tower.", stringLeft, towerTop + rowInc + towerInc);
        g.drawString("Bishops fire diagonally in", stringLeft, towerTop + rowInc + 2*towerInc);
        g.drawString("     two ways (randomly).", stringLeft, towerTop + rowInc + 3*towerInc);

        g.drawImage(ImageHolder.tower3, balloonLeft, towerTop + 145, null);      //EDIT THE Y
        g.drawString("This is the Rook Tower.", stringLeft, towerTop + 2*rowInc + 2*towerInc);
        g.drawString("Rooks fire up, down,", stringLeft, towerTop + 2*rowInc + 3*towerInc);
        g.drawString("     left, and right.", stringLeft, towerTop + 2*rowInc + 4*towerInc);

        g.drawImage(ImageHolder.tower4, balloonLeft, towerTop + 225, null);
        g.drawString("This is the Queen Tower.", stringLeft, towerTop + 3*rowInc + 3*towerInc);
        g.drawString("Queens fire in all eight", stringLeft, towerTop + 3*rowInc + 4*towerInc);
        g.drawString("     possible directions.", stringLeft, towerTop + 3*rowInc + 5*towerInc);

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
        int width = TDWidth - 256;
        //These two lines draw the life counter next to the shop label
        drawLifeToken(g,width+boxSize*17/8,1);
        g.drawString(Integer.toString(Main.lives),width+boxSize*21/8,20);
        //These two lines draw the coin counter under the life counter
        drawCoin(g,width+boxSize*17/8,boxSize/2);
        g.drawString(Integer.toString(Main.coins),width+boxSize*21/8,boxSize/2 + 20);
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
        g.drawString(Integer.toString(Main.popped), width + boxSize*2 + 15, TDHeight - boxSize *5/2 + 35);
        g.drawString(ButtonHolder.Tower1Price + "",boxSize/4 + width + spacing + 30,2 * (3*boxSize/2 + spacing) + 15);
        g.drawString(ButtonHolder.Tower2Price + "",width + spacing + 2*boxSize + 50,2 * (3*boxSize/2 + spacing) + 15);
        g.drawString(ButtonHolder.Tower3Price + "",boxSize/4 + width + spacing + 30,2 * (3*boxSize/2 + spacing) + 5 + 3*boxSize);
        g.drawString(ButtonHolder.Tower4Price + "",width + spacing + 2*boxSize + 50,2 * (3*boxSize/2 + spacing) + 5 + 3*boxSize);
        if(Main.level.balloon == null && Main.level.levelNum != 0){
            g.drawImage(ImageHolder.levelComplete,768, Main.HEIGHT - 128,256,64,null);
        }
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
        if(Main.playAgain) {
            ButtonHolder.drawButton(6, g);
        }
    }


}
