import java.awt.*;


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
    static Button placeTower7 = new Button(0,0,768,768);
    //8 is the top-left shop button, and the tower number increases from left to right
    static Button shop8 = new Button(782, 87, 65, 100);
    static boolean Tower1InAir;
    static boolean Tower1Placed;
    static int Tower1Price = 100;
    static Button shop9 = new Button(860, 87, 65, 100);
    static boolean Tower2InAir;
    static boolean Tower2Placed;
    static int Tower2Price = 200;
    static Button shop10 = new Button(938, 87, 65, 100);
    static boolean Tower3InAir;
    static boolean Tower3Placed;
    static int Tower3Price = 325;
    static Button shop11;



    public ButtonHolder() {
        buttonArray = new Button[13]; // here we can edit the number of buttons

        //here's all of the the buttons being slotted into the array so we can reference them easier by cases
        buttonArray[0] = start0;
        buttonArray[1] = map1;
        buttonArray[2] = map2;
        buttonArray[3] = map3;
        buttonArray[4] = startLevel4;
        buttonArray[5] = gameOver5;
        buttonArray[6] = playAgain6;
        buttonArray[7] = placeTower7;
        buttonArray[8] = shop8;
        buttonArray[9] = shop9;
        buttonArray[10] = shop10;
        buttonArray[11] = shop11;
//        buttonArray[12] = instructions12;
        //we'll add more buttons here

    }

    //this is the first step in deciding if a button has been clicked and what to do about it
    public static void handleClick(double x, double y) {
        boolean anyTowerInAir = false;
//        if (Tower1InAir || Tower2InAir || Tower3InAir || Tower4InAir) anyTowerInAir = true;
        //this section is dense, but it ensures that buttons are only clicked when the appropriate screen is showing
        //it also keeps most of the conditionals confined to one space so "ifClicked" is more straightforward for all of us

        //this detects a start button click
        if ((TowerDefense.titleScreen || (TowerDefense.gameOverScreen && TowerDefense.playAgain)) && checkClick(x, y, buttonArray[0])) ButtonHolder.ifClicked(0);

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
            //if a tower is in-air, this detects if it will be placed
            if(anyTowerInAir && checkClick(x, y, buttonArray[7])) ButtonHolder.ifClicked(7);
            //these detect clicks on the shop buttons; Since this is a for loop, we can keep adding shop buttons without editing this part
            for (int i = 8; i < buttonArray.length; i++) {
                Button button = buttonArray[i];
                if (x > button.left && x < button.right && y > button.top && y < button.bottom) {
                    ButtonHolder.ifClicked(i);
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

    //if a button has been clicked, it's passed into here
    public static void ifClicked(int num) {
        //we'll use this for the shop buttons
        int price;
        int mouseXadj = (int) MouseFunctions.mouseX - 30;
        int mouseYadj = (int) MouseFunctions.mouseY - 15;
        System.out.println("Button " + num + " clicked.");

        //the following cases detail what happens when a specific button is clicked
        //recall that each position [?] in the array correlates to a particular button function
        //these numbers are consistent with those, so case 0 and buttonArray[0] pertain to the same button
        switch(num) {
            //here's the start and play again button to send you to the chooseMapScreen
            case 0:
            case 6:
                    TowerDefense.resetGame();
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

            //this is the "start level" button
            case 4:
                if (TowerDefense.level.balloon == null) {
                    System.out.println(TowerDefense.level.levelNum);
                    TowerDefense.level.levelNum++;
                    TowerDefense.level = new Level(TowerDefense.level.levelNum);
                    System.out.println(TowerDefense.level.levelNum);
                }
                break;

            //this prompts the "play again" button to pop up
            case 5:
                TowerDefense.playAgain = true;
                break;

            //this places a tower if you're holding one
            case 7:
                System.out.println("Ideally, the tower would be placed where you just clicked.");
                if (Tower1InAir) {
                   TowerDefense.level.createTowers(1, new Pair(mouseXadj, mouseYadj));
                    Tower1InAir = false;
                }
                else if (Tower2InAir){
                    TowerDefense.level.createTowers(2, new Pair(mouseXadj, mouseYadj));
                    Tower2InAir = false;
                }
                else if (Tower3InAir){
                    TowerDefense.level.createTowers(3, new Pair(mouseXadj, mouseYadj));
                    Tower3InAir = false;
                }
                break;

            case 8:
                price = Tower1Price;
                if (!(Tower1Placed || Tower1InAir) && price <= TowerDefense.coins) {
                    putTowersDown();
                    Tower1InAir = true;
                    TowerDefense.coins -= price;
                }
                else if (Tower1InAir) {
                    Tower1InAir = false;
                    TowerDefense.coins += price;
                }
                break;

            case 9:
                price = Tower2Price;
                if (!(Tower2Placed || Tower2InAir) && price <= TowerDefense.coins) {
                    putTowersDown();
                    Tower2InAir = true;
                    TowerDefense.coins -= price;
                }
                else if (Tower2InAir) {
                    Tower2InAir = false;
                    TowerDefense.coins += price;
                }
                break;

            case 10:
                price = Tower3Price;
                if (!(Tower3Placed || Tower3InAir) && price <= TowerDefense.coins) {
                    putTowersDown();
                    Tower3InAir = true;
                    TowerDefense.coins -= price;
                }
                else if (Tower3InAir) {
                    Tower3InAir = false;
                    TowerDefense.coins += price;
                }
                break;

        }

    }

    public static void putTowersDown() {
        if (Tower1InAir) {
            Tower1InAir = false;
            TowerDefense.coins += Tower1Price;
        }
        if (Tower2InAir) {
            ButtonHolder.Tower2InAir = false;
            TowerDefense.coins += Tower2Price;
        }
        if (Tower3InAir) {
            ButtonHolder.Tower3InAir = false;
            TowerDefense.coins += Tower3Price;
        }
//        if (Tower4InAir) {
//        ButtonHolder.Tower4InAir = false;
//        TowerDefence.coins += Tower4Price;
//        }
    }

    //for the buttons that need to be drawn, their physical details are here
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

    //when a tower is placed, this will place a graphic over it to show that it cannot be bought again
    public static void disableShop(Graphics g, int num) {
        Pair position = buttonArray[num].position;
        Pair dimension = buttonArray[num].dimension;
        g.setColor(Color.red);
        g.fillRect((int) position.x, (int) position.y, (int) dimension.x, (int) dimension.y);
    }


}