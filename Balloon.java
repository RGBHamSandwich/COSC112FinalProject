import java.awt.*;
import java.util.Random;

public class Balloon implements Drawable {
    int radius;     //this is NOT static because we need to tamper with it in fireBullet method
    Pair position;
    Pair velocity;
    Color color;
    //used to keep track of the next balloon in the linked list of balloons
    Balloon nextBalloon;
    //keeps track of if the balloon has finished traveling the path
    boolean pathCleared = false;
    //determines whether the balloon starts with x or y velocity
    boolean verticalStart;
    //used to track how many balloons have been created in a given level
    int rank;
    //used to randomly position a balloon within a set x distance from its center
    int xMargin;
    //used to randomly position a balloon within a set y distance from its center
    int yMargin;


    //Constructor for a balloon given its color, position, and initial direction
    public Balloon(Pair position, Color color, int rank, boolean verticalStart){
        this.position = new Pair(position.x,position.y);
        this.color = color;
        this.rank = rank;
        this.verticalStart = verticalStart;
        this.radius = determineSize(this.color);
        determineVelocity();
        determineMargins();
    }

    //determines the velocity of a balloon given its color and assigns either x or y velocity based on
    //initial direction
    public void determineVelocity(){
        double velocity = determineVelocity(this.color);
        if(verticalStart) {
            this.velocity = new Pair(0,velocity);
        }
        else{
            this.velocity = new Pair(velocity,0);
        }
    }

    //Determines the total velocity based on the balloon's color
    static double determineVelocity(Color color){
        if(color == Color.red) return 200;
        else if(color == Color.green) return 300;
        else if(color == Color.blue) return 250;
        else if(color == Color.yellow) return 350;
        else return 0;
    }

    //determines the size of the balloon based on its color
    static int determineSize(Color color){
        if(color == Color.red) return 20;
        else if(color == Color.green) return 27;
        else if(color == Color.blue) return 23;
        else if(color == Color.yellow) return 30;
        else return 0;
    }

    //determines the random placement of a balloon within a set distance of its centering point
    public void determineMargins(){
        int margin = 10;
        Random rand = new Random();
        int r1 = rand.nextInt();
        if(r1 % 3 == 1){
            this.xMargin = -margin;
        }
        else if(r1 % 3 == 2){
            this.xMargin = 0;
        }
        int r2 = rand.nextInt();
        if(r2 % 3 == 1){
            this.yMargin = -margin;
        }
        else if(r2 % 3 == 2){
            this.yMargin = 0;
        }
    }

    //controls balloon movement on the first map, recursively updates the subsequent balloons in the linked list
    public void updateMap1(double time,int levelNum){
        position = position.add(velocity.times(time));
        Random rand = new Random();
        int r = rand.nextInt(25,175);
        if(nextBalloon == null && position.y > r && position.x < 100){
            nextBalloon = determineLevel(levelNum);
        }
        if(nextBalloon != null){
            nextBalloon.updateMap1(time,levelNum);
        }
        if(position.y > 672 && position.x == 96){
            position.y = 672;
            velocity.x = velocity.y;
            velocity.y = 0;
        }
        if(position.y == 672 && position.x > 352 && position.x < 608){
            position.x = 352;
            velocity.y = -velocity.x;
            velocity.x = 0;
        }
        if(position.x == 352 && position.y < 96){
            position.y = 96;
            velocity.x = -velocity.y;
            velocity.y = 0;
        }
        if(position.y == 96 && position.x > 608 && position.x < 864){
            position.x = 608;
            velocity.y = velocity.x;
            velocity.x = 0;
        }
        //End Condition
        if(position.y > Main.HEIGHT + 15){
            pathCleared = true;
        }
    }

    //controls balloon movement on the second map, recursively updates the subsequent balloons in the linked list
    public void updateMap2(double time,int levelNum){
        position = position.add(velocity.times(time));
        Random rand = new Random();
        int r = rand.nextInt(25,175);
        if(nextBalloon == null && position.y > r && position.x < 100){
            nextBalloon = determineLevel(levelNum);
        }
        if(nextBalloon != null){
            nextBalloon.updateMap2(time,levelNum);
        }
        //First Curve
        if(position.y > 234 && position.y < 330 && position.x > 64 && position.x < 256){
            Pair center = new Pair(160,234);
            centripetalMotion(center,position);
        }
        if(position.y > 160 && position.y < 224 && position.x > 192 && position.x < 256){
            velocity.x = 0;
            velocity.y = - determineVelocity(this.color);
        }
        //Second Curve
        if(position.y > 56 && position.y < 120 && position.x > 192 && position.x < 384){
            Pair center = new Pair(288,120);
            centripetalMotion(center,position);
        }
        if(position.y > 160 && position.y < 224 && position.x > 320 && position.x < 384){
            velocity.x = 0;
            velocity.y = determineVelocity(this.color);
        }
        //Third Curve
        if(position.y > 234 && position.y < 330 && position.x > 320 && position.x < 512){
            Pair center = new Pair(416,234);
            centripetalMotion(center,position);
        }
        if(position.y > 160 && position.y < 224 && position.x > 448 && position.x < 512){
            velocity.x = 0;
            velocity.y = - determineVelocity(this.color);
        }
        //Fourth Curve
        if(position.y > 56 && position.y < 120 && position.x > 448 && position.x < 640){
            Pair center = new Pair(544,120);
            centripetalMotion(center,position);
        }
        if(position.y > 160 && position.y < 224 && position.x > 576 && position.x < 640){
            velocity.x = 0;
            velocity.y = determineVelocity(this.color);
        }
        //Fifth Curve
        if(position.y > 648 && position.y < 712 && position.x > 448 && position.x < 640){
            Pair center = new Pair(544,648);
            centripetalMotion(center,position);
        }
        if(position.y > 544 && position.y < 608 && position.x > 448 && position.x < 512){
            velocity.x = 0;
            velocity.y = - determineVelocity(this.color);
        }
        //Sixth Curve
        if(position.y > 438 && position.y < 534 && position.x > 320 && position.x < 512){
            Pair center = new Pair(416,534);
            centripetalMotion(center,position);
        }
        if(position.y > 544 && position.y < 608 && position.x > 320 && position.x < 384){
            velocity.x = 0;
            velocity.y = determineVelocity(this.color);
        }
        //Seventh Curve
        if(position.y > 648 && position.y < 712 && position.x > 192 && position.x < 384){
            Pair center = new Pair(288,648);
            centripetalMotion(center,position);
        }
        if(position.y > 544 && position.y < 608 && position.x > 192 && position.x < 256){
            velocity.x = 0;
            velocity.y = - determineVelocity(this.color);
        }
        //Eighth Curve
        if(position.y > 438 && position.y < 534 && position.x > 64 && position.x < 256){
            Pair center = new Pair(160,534);
            centripetalMotion(center,position);
        }
        if(position.y > 544 && position.y < 608 && position.x > 64 && position.x < 128){
            velocity.x = 0;
            velocity.y = determineVelocity(this.color);
        }
        //End Condition
        if(position.y > Main.HEIGHT + 15){
            pathCleared = true;
        }
    }

    //Directs the balloon on a curved path given its position and a center of rotation, applicable only on Map 2
    public void centripetalMotion(Pair center,Pair position){
        velocity.x = velocity.x + (center.x - position.x)/2;
        velocity.y = velocity.y + (center.y - position.y)/2;
    }

    //controls balloon movement on the third map, recursively updates the subsequent balloons in the linked list
    public void updateMap3(double time,int levelNum){
        position = position.add(velocity.times(time));
        Random rand = new Random();
        int r = rand.nextInt(25,175);
        if(nextBalloon == null && position.x > r && (position.y < 450 && position.y > 350)){
            nextBalloon = determineLevel(levelNum);
        }
        if(nextBalloon != null){
            nextBalloon.updateMap3(time,levelNum);
        }
        if(position.y == 416 && position.x > 544 && position.x < 600){
            position.x = 544;
            velocity.y = -velocity.x;
            velocity.x = 0;
        }
        if(position.x == 544 && position.y < 224 && position.y > 200){
            position.y = 224;
            velocity.x = velocity.y;
            velocity.y = 0;
        }
        if(position.y == 224 && position.x < 96){
            position.x = 96;
            velocity.y = velocity.x;
            velocity.x = 0;
        }
        if(position.x == 96 && position.y < 96){
            position.y = 96;
            velocity.x = -velocity.y;
            velocity.y = 0;
        }
        if(position.y == 96 && position.x > 672){
            position.x = 672;
            velocity.y = velocity.x;
            velocity.x = 0;
        }
        if(position.x == 672 && position.y > 544){
            position.y = 544;
            velocity.x = -velocity.y;
            velocity.y = 0;
        }
        if(position.y == 544 && position.x < 96){
            position.x = 96;
            velocity.y = -velocity.x;
            velocity.x = 0;
        }
        if(position.x == 96 && position.y > 672){
            position.y = 672;
            velocity.x = velocity.y;
            velocity.y = 0;
        }
        //End Conditions
        if(position.x > Screen.boxSize*12 + 15){
            pathCleared = true;
        }
    }

    //controls the number of balloons in each level
    public Balloon determineLevel(int levelNum){
        Pair startPosition = Level.startPosition;
        switch (levelNum){
            case 1://Level 1
                if (rank < 11) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                break;
            case 2://Level 2
                if (rank < 10) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 10) {
                    return new Balloon(startPosition, Color.blue, this.rank + 1,this.verticalStart);
                }
                else if (rank < 15) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                break;
            case 3: //Level 3
                if (rank < 10) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 10) {
                    return new Balloon(startPosition, Color.blue, this.rank + 1,this.verticalStart);
                }
                else if (rank < 15) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 15){
                    return new Balloon(startPosition, Color.green, this.rank + 1,this.verticalStart);
                }
                else if (rank < 20) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                break;
            case 4://Level 4
                if (rank < 10) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 10) {
                    return new Balloon(startPosition, Color.blue, this.rank + 1,this.verticalStart);
                }
                else if (rank < 15) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 15){
                    return new Balloon(startPosition, Color.green, this.rank + 1,this.verticalStart);
                }
                else if (rank < 20) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 20){
                    return new Balloon(startPosition, Color.yellow, this.rank + 1,this.verticalStart);
                }
                else if (rank < 25) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                break;
            case 5: //Level 5
                if (rank < 10) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 10) {
                    return new Balloon(startPosition, Color.blue, this.rank + 1,this.verticalStart);
                }
                else if (rank < 15) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 15){
                    return new Balloon(startPosition, Color.green, this.rank + 1,this.verticalStart);
                }
                else if (rank < 20) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 20){
                    return new Balloon(startPosition, Color.yellow, this.rank + 1,this.verticalStart);
                }
                else if (rank < 35) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
            default: //for any Level Number greater than 5
                if (rank < 10) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 10) {
                    return new Balloon(startPosition, Color.blue, this.rank + 1,this.verticalStart);
                }
                else if (rank < 25) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 25){
                    return new Balloon(startPosition, Color.green, this.rank + 1,this.verticalStart);
                }
                else if (rank < 30) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
                else if (rank == 30){
                    return new Balloon(startPosition, Color.yellow, this.rank + 1,this.verticalStart);
                }
                else if (rank < 45) {
                    return new Balloon(startPosition, this.color, this.rank + 1,this.verticalStart);
                }
        }
        return null;
    }

    //draws the balloon. Graphics differ based on map, recursively draws the subsequent balloons in the linked list
    @Override
    public void drawComponent(Graphics g){
        if(color == Color.red){
            g.drawImage(ImageHolder.redBalloon,(int) (position.x - radius + xMargin),(int) (position.y - radius + yMargin),
                    radius * 2, radius * 2,null);
        }
        else if(color == Color.yellow){
            if(Main.map2Screen){
                g.drawImage(ImageHolder.pastelYellowBalloon,(int) (position.x - radius + xMargin),(int) (position.y - radius + yMargin),
                        radius * 2, radius * 2,null);
            }
            else {
                g.drawImage(ImageHolder.yellowBalloon, (int) (position.x - radius + xMargin), (int) (position.y - radius + yMargin),
                        radius * 2, radius * 2, null);
            }
        }
        else if(color == Color.blue){
            if(Main.map2Screen){
                g.drawImage(ImageHolder.pastelBlueBalloon,(int) (position.x - radius + xMargin),(int) (position.y - radius + yMargin),
                        radius * 2, radius * 2,null);
            }
            else {
                g.drawImage(ImageHolder.blueBalloon,(int) (position.x - radius + xMargin),(int) (position.y - radius + yMargin),
                        radius * 2, radius * 2,null);
            }
        }
        else if(color == Color.green){
            if(Main.map2Screen){
                g.drawImage(ImageHolder.pastelGreenBalloon,(int) (position.x - radius + xMargin),(int) (position.y - radius + yMargin),
                        radius * 2, radius * 2,null);
            }
            else {
                g.drawImage(ImageHolder.greenBalloon,(int) (position.x - radius + xMargin),(int) (position.y - radius + yMargin),
                        radius * 2, radius * 2,null);
            }
        }
        if (nextBalloon != null) {
            nextBalloon.drawComponent(g);
        }
    }

    //This method checks if a balloon has been popped by a bullet, returns whether it has been
    public boolean isShot(Bullet bullet){
        if ((bullet.getPosition().x > this.position.x-radius-2) && (bullet.getPosition().x < position.x+radius-2)){
            if ((bullet.getPosition().y > this.position.y-radius-2) && (bullet.getPosition().y < position.y+radius-2)){
                return true;
            }
        }
        return false;
    }
}
