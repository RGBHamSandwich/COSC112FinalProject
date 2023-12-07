import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


class Button {
    double x;
    double y;
    double width;
    double height;
    //The (y) coordinate for the upper side corner of the button
    double top;
    //The (y) coordinate for the lower side of the button
    double bottom;
    //The (x) coordinate for the left side corner of the button
    double left;
    //The (x) coordinate for the right side of the button
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
//      if a button is clicked, these are the functions

        if (TowerDefense.map1Screen || TowerDefense.map2Screen || TowerDefense.map3Screen) {
            //this would indicate where a tower (presumably attached to the mouse) would sit
        }

        switch(num) {
            case 0:
                if(TowerDefense.titleScreen||(TowerDefense.gameOverScreen && TowerDefense.playAgain)) {
                    TowerDefense.resetGame();
                }
                break;

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
                if (TowerDefense.map1Screen || TowerDefense.map2Screen || TowerDefense.map3Screen){
                    if (TowerDefense.level.balloon == null) {
                        System.out.println(TowerDefense.level.levelNum);
                        TowerDefense.level.levelNum++;
                        TowerDefense.level = new Level(TowerDefense.level.levelNum);
                        System.out.println(TowerDefense.level.levelNum);
                    }
                }
                break;

            case 5:
                TowerDefense.resetGame();
                break;

            case 6:
                if(TowerDefense.gameOverScreen && !TowerDefense.playAgain){
                    TowerDefense.playAgain = true;
                }
                break;

            case 7:
                TowerDefense.basicTowerPresent = true;
//              TowerDefense.drawTower(1);
                // I don't know how to pass g into this :(
                break;

        }

    }

}

class ButtonHolder {
    Button[] buttonArray;
    int WIDTH = TowerDefense.WIDTH;
    int HEIGHT = TowerDefense.HEIGHT;
    int boxSize = Screen.boxSize;

    public ButtonHolder() {
        buttonArray = new Button[10]; // here we can edit the number of buttons

        //0 is the Start button
        buttonArray[0] = new Button(WIDTH/2-20,HEIGHT*5/8-20,50,30);
        //1, 2, 3 are the buttons to choose between map 1, 2, and 3
        buttonArray[1] = new Button(boxSize*3/2,boxSize*2, boxSize*4,boxSize*3);
        buttonArray[2] = new Button(boxSize*13/2,boxSize*2, boxSize*4,boxSize*3);
        buttonArray[3] = new Button(boxSize*23/2,boxSize*2, boxSize*4, boxSize*3);
        //4 is the button to start the level
        buttonArray[4] = new Button(WIDTH - 256,HEIGHT - boxSize, boxSize*4, boxSize);
        //5, 6 implement a "play again" function
        buttonArray[5] = new Button(WIDTH/2-30,HEIGHT*5/8 - 20,85,30);
        buttonArray[6] = new Button(0,0,WIDTH,HEIGHT);
        //7 is the top-left shop button
        buttonArray[7] = new Button(784, 87, 63, 63);
    }

    public void handleButtonClick(double x, double y) {
        //this will help us figure out if clicks are on the button or somewhere else
        for (int i = 0; i < buttonArray.length; i++) {
            Button button = buttonArray[i];
            if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
                button.ifClicked(i);
                System.out.println("Button Clicked: "+i);
                // Only one button can be clicked at a time, so this keeps us from thumbing through every button for every click
                break;
            }
        }
    }
}