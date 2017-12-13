import umontreal.ssj.functionfit.BSpline;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Main extends JPanel {
    static double[] x = {10, -30, -30, -40, -40};
    static double[] y = {-25, -25, -40, -40, -20};
    static List<Double> xCoords = new ArrayList<>();
    static List<Double> yCoords = new ArrayList<>();

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double w = getWidth();
        double h = getHeight();
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Draw ordinate.
        g2.setColor(Color.green);
        for(int i = 0; i < x.length-1; i++) {
            g2.draw(new Line2D.Double(x[i] + w/2, y[i] + h/2, x[i+1] + w/2, y[i+1] + h/2));
        }
        g2.setColor(Color.BLUE);
        for(int i = 0; i < xCoords.size()-1; i++) {
            g2.draw(new Line2D.Double(xCoords.get(i) + w/2, yCoords.get(i) + h/2, xCoords.get(i+1) + w/2, yCoords.get(i+1) + h/2));
        }
        g2.setColor(Color.black);
        for(int i = 0; i < x.length; i++){
            g2.draw(new Ellipse2D.Double(x[i] + w/2 - 5/2.0, y[i] + h/2 - 5/2.0, 5,5));
        }
    }

    public static void main(final String[] args) {
        for(int i = 0; i < x.length; i++){
            x[i] *= 2;
            y[i] *= 2;
        }
        BSpline bSpline = new BSpline(x,y,3);
        for(double i = 0; i < 1; i += 0.0001) {
            System.out.printf("%f\t%f\n", bSpline.evalX(i), bSpline.evalY(i));
            xCoords.add(bSpline.evalX(i));
            yCoords.add(bSpline.evalY(i));
        }
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Main());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
