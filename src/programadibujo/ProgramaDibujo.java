/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programadibujo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Usuario
 */
public class ProgramaDibujo extends JPanel {

    /**
     * @param args the command line arguments
     */
    BufferedImage image;
    Graphics2D g2d;

    public ProgramaDibujo() {
        super();
        init();
    }

    private void init() {
        setBackground(Color.black);

        image = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);
        g2d = image.createGraphics();
        g2d.setColor(Color.ORANGE);
        g2d.setStroke(new BasicStroke(2));

        MouseAdapter mouseHandler = new MouseAdapter() {
            private Point curPoint = new Point();

            @Override
            public void mousePressed(MouseEvent e) {
                curPoint.setLocation(e.getPoint());
                super.mousePressed(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                g2d.drawLine(curPoint.x, curPoint.y, e.getX(), e.getY());
                curPoint.setLocation(e.getPoint());
                repaint();
                super.mouseDragged(e);

            }

        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        // TODO code application logic here

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ProgramaDibujo");
            frame.setMinimumSize(new Dimension(640, 480));
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(new ProgramaDibujo());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

}
