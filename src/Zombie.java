
import java.awt.Graphics;
import java.net.URL;

import javax.imageio.ImageIO;

public class Zombie extends MapObject {

    private int direction = 0;

    public Zombie(int x, int y, Player p) {
        super(x, y);
        // find way to reference a player
        initImages();
    }

    private void initImages() {

        try {
            URL url = getClass().getResource("res/skeleBack.png");
            WalkBack = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("Image could not be opened:res/skeleBack.png");
            e.printStackTrace();
        }

        try {
            URL url = getClass().getResource("res/skeleFront.png");
            WalkFront = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("Image could not be opened:res/skeleFront.png");
            e.printStackTrace();
        }

        try {
            URL url = getClass().getResource("res/skeleLeft.png");
            WalkLeft = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("Image could not be opened:res/skeleLeft.png");
            e.printStackTrace();
        }

        try {
            URL url = getClass().getResource("res/skeleRight.png");
            WalkRight = ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("Image could not be opened:res/skeleRight.png");
            e.printStackTrace();
        }
    }

//    public int locX(Player p) {
//        //return p.getX();
//    }

//    public int locY(Player p) {
//        //return p.getY();
//    }

    public void draw(Graphics g, int x, int y) {
//        if (Math.abs(y - this.getY()) > Math.abs(x - this.getX())) {
//            if (y > this.getY()) {
//                g.drawImage(WalkBack, this.x, this.y, null);
//            } else {
//                g.drawImage(WalkFront, this.x, this.y, null);
//            }
//        }
//
//        if (Math.abs(y - this.getY()) < Math.abs(x - this.getX())) {
//            if (x > this.getX()) {
//                g.drawImage(WalkRight, this.x, this.y, null);
//            } else {
//                g.drawImage(WalkLeft, this.x, this.y, null);
//            }
//        }

    }

    public void moveLeft(){
        if(x > 0){
            x -= 5;

        }
    }

    public void moveRight(){
        if(x < 1150){
            x += 5;

        }
    }

    public void moveUp(){
        if(y > 5){
            y -= 5;


        }
    }

    public void moveDown(){
        if(y < 750){
            y += 5;
       }
    }

}
