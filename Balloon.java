import java.awt.*;

class Balloon{
    static int radius = 24;
    Pair position;
    Pair velocity;
    Color color;
    Balloon nextBalloon;
    boolean pathCleared = false;
    boolean shot = false;
    int rank;


    public Balloon(double x,double y,Color color,double xVelocity,double yVelocity, int rank){
        this.position = new Pair(x,y);
        this.color = color;
        this.velocity = new Pair(xVelocity,yVelocity);
        this.rank = rank;
    }


    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval((int) (position.x-radius), (int) (position.y-radius),radius*2,radius*2);
        if(nextBalloon != null){
            nextBalloon.draw(g);
            //!this.shot &&
        }
    }
    public boolean collides(Bullet bullet){
        if ((bullet.getPosition().x > this.position.x-radius-2) && (bullet.getPosition().x < position.x+radius-2)){
            if ((bullet.getPosition().y > this.position.y-radius-2) && (bullet.getPosition().y < position.y+radius-2)){
                return true;
            }
        }
        return false;
    }
    //this is basically the ballon version of zerovelocity and me just testing to see if it works which it doesnt
    public void shot(Bullet bullet){
        if (this.collides(bullet)){
            System.out.println("shot");
            this.shot = true;
            Bullet temp = new Bullet(new Pair(300, 80),5, new Pair(-200, 0));
            bullet = temp;
        }
        if (this.nextBalloon != null){
            System.out.println(1);
            nextBalloon.shot(bullet);
        }
    }
    public void updateMap1(double time){
        position = position.add(velocity.times(time));
//Put here a method that determines which level we're on and how many balloons to generate for each level
//Start with three levels for one map
        if(nextBalloon == null && position.y > 100 && position.x < 100){
            double velocity = Math.sqrt(Math.pow(this.velocity.x,2) + Math.pow(this.velocity.y,2));
            if(rank < 30) {
//                bH.append(new Balloon(96, 0, this.color, 0,velocity,this.rank+1));
                this.nextBalloon = new Balloon(96, 0, this.color, 0,velocity,this.rank+1);
            }
            else if(rank == 30){
//                bH.append(new Balloon(96, 0, Color.yellow, 0,200,this.rank+1));
                this.nextBalloon = new Balloon(96, 0, Color.yellow, 0,200,this.rank+1);
            }
            else if(rank > 30 && rank < 40){
//                bH.append(new Balloon(96, 0, this.color, 0,velocity,this.rank+1));
                this.nextBalloon = new Balloon(96, 0, this.color, 0,velocity,this.rank+1);
            }
        }
        if(nextBalloon != null){
            nextBalloon.updateMap1(time);
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
        if(position.x == 608 && position.y > 672){
            position.y = 672;
            velocity.x = velocity.y;
            velocity.y = 0;
        }
        if(position.y == 672 && position.x > 864){
            position.x = 864;
            velocity.y = -velocity.x;
            velocity.x = 0;
        }
        if(position.x == 864 && position.y < 96){
            position.y = 96;
            velocity.x = -velocity.y;
            velocity.y = 0;
        }
        if(position.x >= 1024){
            pathCleared = true;
        }
    }
    public void updateMap2(double time){


    }
    public Rectangle bounds(){
        return new Rectangle((int)this.position.x, (int)this.position.y, 30, 30);
    }
}

class Node{
    Balloon b;
    Node prev;

    public Node(Balloon b){
        this.b = b;
    }
}
class BalloonHolder{
    Node end;
    public BalloonHolder(){
        end = null;
    }
    public void append (Balloon toAppend){
        Node toAdd = new Node(toAppend);
        toAdd.prev = end;
        end = toAdd;
    }
    public Balloon pop(){
        Balloon toReturn = end.b;
        end = end.prev;
        return toReturn;
    }
    public void update(){

    }
}