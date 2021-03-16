import javax.swing.*;
import java.awt.*;

public class rita extends Canvas {
    public rita(){
        JFrame rityta = new JFrame("EN SUPERDUPER COOL BILD");
        this.setSize(750,750);
        rityta.add(this);
        rityta.pack();
        rityta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rityta.setVisible(true);
    }
    public void paint(Graphics p) {         // vilka former som ska ritas
        drawHouse(p, 200,300);
        drawTree(p, 100, 100);


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
    }
}
