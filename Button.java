import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


class Button {
    //these are the parameters used to "draw" each button
    Pair position = new Pair(0, 0);
    Pair dimension = new Pair(0, 0);

    //these four doubles give coordinates for the top (y), bottom (y), left (x), and right (x) of the button
    double top;
    double bottom;
    double left;
    double right;

    public Button(double x, double y, double width, double height) {
        //here we set the values for each side of the button as well as filling out position (x, y) and dimension (width, height)
        position.x = x;
        position.y = y;
        dimension.x = width;
        dimension.y = height;
        right = x + width;
        left = x;
        bottom = y + height;
        top = y;

    }

}

class ButtonHolder {
    static Button[] buttonArray;
    static int WIDTH = TowerDefense.WIDTH;
    static int HEIGHT = TowerDefense.HEIGHT;
    static int boxSize = Screen.boxSize;

    //here is where all of the buttons are initialized
    //here's the info for button 0, the start button
    static Button start0 = new Button(WIDTH/2-20,HEIGHT*5/8-20, 50, 30);
    //1, 2, 3 are the buttons to choose between map 1, 2, and 3
    static Button map1 = new Button(boxSize, boxSize*2, boxSize*4,boxSize*3);
    static Button map2 = new Button(boxSize*6,boxSize*2, boxSize*4,boxSize*3);
    static Button map3 = new Button(boxSize*11,boxSize*2, boxSize*4,boxSize*3);
    //4 is the button to start the level
    static Button startLevel4 = new Button(WIDTH - 256,HEIGHT - boxSize, boxSize*4, boxSize);
    //5 registers a click anywhere on the "game over" screen to materialize the "play again" button
    static Button gameOver5 = new Button(0, 0, WIDTH, HEIGHT);
    //button 6 is the play again button
    static Button playAgain6 = new Button(WIDTH /2-30,HEIGHT*5/8 - 20, 85, 30);
    //7 is the top-left shop button
    static Button shop7 = new Button(784, 87, 63, 63);
    static Button shop8;
    static Button shop9;
    //here is where we'll add more buttons



    public ButtonHolder() {
        buttonArray = new Button[10]; // here we can edit the number of buttons

        //here's all of the the buttons being slotted into the array so we can reference them easier by cases
        buttonArray[0] = start0;
        buttonArray[1] = map1;
        buttonArray[2] = map2;
        buttonArray[3] = map3;
        buttonArray[4] = startLevel4;
        buttonArray[5] = gameOver5;
        buttonArray[6] = playAgain6;
        buttonArray[7] = shop7;
        buttonArray[8] = shop8;
        buttonArray[9] = shop9;
        //we'll add more buttons here

    }

    //this will help us determine if clicks are on the button or somewhere else

    public static void handleClick(double x, double y) {
        //this section is dense, but it ensures that buttons are only clicked when the appropriate screen is showing

        //this detects a start button click
        if (TowerDefense.titleScreen && checkClick(x, y, buttonArray[0])) ButtonHolder.ifClicked(0);

        //these detect a map selection click
        else if (TowerDefense.chooseMapScreen) {
            if (checkClick(x, y, buttonArray[1])) ButtonHolder.ifClicked(1);
            else if (checkClick(x, y, buttonArray[2])) ButtonHolder.ifClicked(2);
            else if (checkClick(x, y, buttonArray[3])) ButtonHolder.ifClicked(3);
        }

        //this detects a "game over" click
        else if(TowerDefense.lives == 0 && !TowerDefense.playAgain) ButtonHolder.ifClicked(5);

        //this detects a click to play again
        else if(TowerDefense.gameOverScreen) ButtonHolder.ifClicked(6);

        //all of the next clicks happen on the map screens, so they're grouped together
        else if (TowerDefense.map1Screen || TowerDefense.map2Screen || TowerDefense.map3Screen) {
            //this detects a "start level" click
            if(checkClick(x, y, buttonArray[4])) ButtonHolder.ifClicked(4);
            //these detect clicks on the shop buttons; Since this is a for loop, we can keep adding shop buttons without editing this part
            for (int i = 7; i < buttonArray.length; i++) {
                Button button = buttonArray[i];
                if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
                    ButtonHolder.ifClicked(i);
                    System.out.println("Button Clicked: " + i);
                    // Only one button can be clicked at a time, so this keeps us from thumbing through every button for every click
                    break;
                }
            }
        }

    }

    //this method checks if the click is within the bounds of the button
    public static boolean checkClick(double x, double y, Button button) {
        if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
            return true;
        }
        else return false;
    }

    public static void ifClicked(int num) {

        if (TowerDefense.map1Screen || TowerDefense.map2Screen || TowerDefense.map3Screen) {
            //based on the current screen and probably a "placingTower" boolean, this would change a mid-air tower's x and y
            //i.e. this would indicate where a tower (presumably attached to the mouse) would sit based on where you click
        }

        //the following cases detail what happens when a specific button is clicked
        //recall that each position [?] in the array correlates to a particular button function
        //these numbers are consistent with those, so case 0 and buttonArray[0] pertain to the same button
        switch(num) {
            case 0:
                //here's the start button, it starts the game
                if(TowerDefense.titleScreen||(TowerDefense.gameOverScreen && TowerDefense.playAgain)) {
                    TowerDefense.resetGame();       //this sends you to the chooseMapScreen
                }
                break;

            //the next three buttons are to choose between map 1, 2, and 3
            case 1:
                TowerDefense.chooseMapScreen = false;
                TowerDefense.map1Screen = true;
                System.out.println("Moving to map 1");
                System.out.println(TowerDefense.lives);
                break;

            case 2:
                TowerDefense.chooseMapScreen = false;
                TowerDefense.map2Screen = true;
                System.out.println("Moving to map 2");
                System.out.println(TowerDefense.lives);
                break;

            case 3:
                TowerDefense.chooseMapScreen = false;
                TowerDefense.map3Screen = true;
                System.out.println("Moving to map 3");
                System.out.println(TowerDefense.lives);
                break;

            case 4:
                //this is the "start level" button
                if (TowerDefense.level.balloon == null) {
                    System.out.println(TowerDefense.level.levelNum);
                    TowerDefense.level.levelNum++;
                    TowerDefense.level = new Level(TowerDefense.level.levelNum);
                    System.out.println(TowerDefense.level.levelNum);
                }
                break;

            //the next two buttons are for restarting the game after you lose
            case 5:
                TowerDefense.playAgain = true;
                break;

            case 6:
                TowerDefense.resetGame();
                break;

            case 7:
//                TowerDefense.basicTowerPresent = true;            //I don't think this is what I need
//              TowerDefense.drawTower(1);                          // I don't know how to pass g into this :(
                //I'm not actually using this right now, but this is where tower creation will happen!
//                BasicTower basicTower = new BasicTower(10, 100);
                break;

        }

    }

    public static void drawButton(int num, Graphics g){
        Button b = ButtonHolder.buttonArray[num];
        Pair position = b.position;
        Pair dimension = b.dimension;

        //similar to ifClicked, the following cases detail how to draw a button
        //it's important to note that we don't have to draw ALL of the buttons
        switch(num) {
            case 0: //the start button
                g.drawRect((int) position.x,(int) position.y,(int) dimension.x,(int) dimension.y);
                g.drawString("Start",(int) position.x + 10,(int) position.y + 20);
                break;

            case 1: //the map buttons (1, 2, and 3 are encompassed here)
                g.drawRect((int) position.x,(int) position.y,(int) dimension.x,(int) dimension.y);
                b = ButtonHolder.buttonArray[2];
                g.drawRect((int) b.position.x,(int) b.position.y,(int) b.dimension.x,(int) b.dimension.y);
                b = ButtonHolder.buttonArray[3];
                g.drawRect((int) b.position.x,(int) b.position.y,(int) b.dimension.x,(int) b.dimension.y);
                break;

            case 6:
                g.setColor(Color.BLACK);
                g.drawRect((int) position.x,(int) position.y,(int) dimension.x,(int) dimension.y);
                g.drawString("Play again?", (int) position.x + 10, (int) position.y + 20);
                break;

//            case 5:
//                break;

        }

    }


}