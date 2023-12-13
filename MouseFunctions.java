
import java.awt.event.*;


public class MouseFunctions implements MouseListener, MouseMotionListener {
    //these doubles will keep track of the current x and y position of the mouse)
    static double mouseX = 0;
    static double mouseY = 0;


    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        ButtonHolder.handleClick(x, y);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //if we use click/drag mechanics, this is important so the program doesn't crash when the mouse exits the screen
        double x = TowerDefense.WIDTH/2;
        double y = TowerDefense.HEIGHT/2;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //we need to use this in tandem with mouseExited to get the drag and drop working again
        //on the other hand, we could just cancel the drag and drop when the mouse exits the screen
        double x = e.getX();
        double y = e.getY();

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //this will update the current x and y of the mouse as it moves
        double x = e.getX();
        double y = e.getY();
        mouseX = x;
        mouseY = y;
    }

}


