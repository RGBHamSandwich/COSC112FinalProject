import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


class Button {
    //these are the parameters used to "draw" each button
    double x;
    double y;
    double width;
    double height;
    //these four doubles give coordinates for the top (y), bottom (y), left (x), and right (x) of the button
    double top;
    double bottom;
    double left;
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
                    TowerDefense.resetGame();
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

}

class ButtonHolder {
    static Button[] buttonArray;
    int WIDTH = TowerDefense.WIDTH;
    int HEIGHT = TowerDefense.HEIGHT;
    int boxSize = Screen.boxSize;

    public ButtonHolder() {
        buttonArray = new Button[10]; // here we can edit the number of buttons

        //0 is the Start button
        buttonArray[0] = new Button(WIDTH/2-20,HEIGHT*5/8-20,50,30);
        //1, 2, 3 are the buttons to choose between map 1, 2, and 3
        buttonArray[1] = new Button(boxSize,boxSize*2,boxSize*4,boxSize*3);
        buttonArray[2] = new Button(boxSize*6,boxSize*2,boxSize*4,boxSize*3);
        buttonArray[3] = new Button(boxSize*11,boxSize*2,boxSize*4,boxSize*3);
        //4 is the button to start the level
        buttonArray[4] = new Button(WIDTH - 256,HEIGHT - boxSize, boxSize*4, boxSize);
        //5 registers a click anywhere on the "game over" screen to materialize the "play again" button
        buttonArray[5] = new Button(0,0,WIDTH,HEIGHT);
        //button 6 is the play again button
        buttonArray[6] = new Button(WIDTH/2-30,HEIGHT*5/8 - 20,85,30);
        //7 is the top-left shop button
        buttonArray[7] = new Button(784, 87, 63, 63);
    }

    public static void handleButtonClick(double x, double y) {
        //this will help us figure out if clicks are on the button or somewhere else
        Button button = buttonArray[0];

        //Perhaps Hena could help me figure out a better way to write all these if/else things.
        //I've overcomplicated this section to ensure that buttons are only clicked when the appropriate screen is showing

        //this detects a start button click
        if (TowerDefense.titleScreen && checkClick(x, y, button)) button.ifClicked(0);
        //these detect a map selection click
        else if (TowerDefense.chooseMapScreen) {
            if (checkClick(x, y, buttonArray[1])) button.ifClicked(1);
            else if (checkClick(x, y, buttonArray[2])) button.ifClicked(2);
            else if (checkClick(x, y, buttonArray[3])) button.ifClicked(3);
        }
        //this detects a "game over" click
        else if(TowerDefense.lives == 0 && !TowerDefense.playAgain) button.ifClicked(5);
        //this detects a click to play again
        else if(TowerDefense.gameOverScreen) button.ifClicked(6);
        //all of the next clicks happen on the map screens, so they're grouped together
        else if (TowerDefense.map1Screen || TowerDefense.map2Screen || TowerDefense.map3Screen) {
            //this detects a "start level" click
            if(checkClick(x, y, buttonArray[4])) button.ifClicked(4);
            //these detect clicks on the shop buttons
            for (int i = 7; i < buttonArray.length; i++) {
                button = buttonArray[i];
                if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
                    button.ifClicked(i);
                    System.out.println("Button Clicked: " + i);
                    // Only one button can be clicked at a time, so this keeps us from thumbing through every button for every click
                    break;
                }
            }
        }

    }

    public static boolean checkClick(double x, double y, Button button) {
        //this method checks if the click is within the bounds of the button
        if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
            return true;
        }
        else return false;
    }

}