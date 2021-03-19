import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class rita extends Canvas implements Runnable{
    private static boolean körs;
    private int houseX, houseY;
    private int houseVX, houseVY;

    private int width = 600;
    private int height = 400;

    private int treeX,treeY;

    private Thread tråd;
    int fps = 30;

    private BufferStrategy bs;
    //private BufferedImage bild;

    public rita(){
        JFrame rityta = new JFrame("EN SUPERDUPER COOL BILD");
        this.setSize(width,height);
        rityta.add(this);
        rityta.pack();
        rityta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rityta.setVisible(true);

        körs = false;

        houseX = 150;
        houseY = 200;
        houseVX = 1;
        houseVY = 0;
    }
    public void draw () {         // vilka former som ska ritas
        bs = getBufferStrategy();
        if (bs == null){
            createBufferStrategy(4);
            return;
        }
        Graphics p = bs.getDrawGraphics();

        update();
        p.setColor(Color.white);
        p.fillRect(0,0,width,height);
        drawHouse(p, houseX,houseY);

        drawTree(p, 100, 100);
        drawTree(p, 100, 111);
        drawTree(p, 100, 122);
        drawTree(p, 100, 133);
        drawTree(p, 100, 144);
        drawTree(p, 100, 155);
        p.dispose();
        bs.show();
    }
    public void update() {
        houseX += houseVX;
        if (houseX > width) {
            houseVX = -1;
        }
        if (houseX < 0){
            houseVX = 1;
        }
    }

    private void drawTree(Graphics p, int y, int x) {
        p.setColor(new Color(0,128,0));
        int[] xs = {0+x, 10+x, 20+x};
        int[] ys = {30+y,0+y,30+y};
        p.fillPolygon(xs,ys,3);
        p.setColor(new Color(200,128,30));
        p.fillRect(7+x,30+y,6,10);

    }

    private void drawHouse(Graphics p, int x, int y) {
        p.setColor(new Color(255, 226, 0, 255));
        p.fillRect(40+x,40+y, 400,200);

        int[] xx = {40+x, 240+x, 440+x};
        int[] yy = {40+y, y, 40+y};
        p.setColor(new Color(0, 0, 0, 255));
        p.fillPolygon(xx, yy,3);
    }

    public static void main(String[] args) {
        rita bild = new rita();
        körs = true;
        bild.start();
    }
    public synchronized void start(){
        tråd = new Thread(this);
        tråd.start();
    }

    public synchronized void stop(){
        körs = false;
        try {
            tråd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double deltaT = 1.0/fps;
        long lastTime = System.currentTimeMillis();

        while(körs){
            long now = System.currentTimeMillis();
            if (now-lastTime > deltaT){
                update();
                draw();
                lastTime=now;
            }
        }
    }
     private  class KL implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            treeX-= 5;
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
