package golj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;

/**
 * @author Michael Hunger
 * @since 25.03.2010
 */
public class GameOfLifeImage {
    public static void main(String[] args) {

        new GolFrame(Integer.valueOf(args[0]),Integer.valueOf(args[1]),args.length>2).setVisible(true);
    }

    private static class GolFrame extends JFrame {
        private BufferedImage image;
        private int scale = 20;
        private final Timer timer;
        private long generation;

        public GolFrame(int size, int time, boolean fillRandomly) {
            scale = Math.max(800/size,1);
            this.image = createBufferedImage(size,size);//createImage(PATTERN, SCALE);
            if (fillRandomly) fillRandomly(this.image);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(image.getWidth() * scale, image.getHeight() * scale);
            final MouseAdapter mouseAdapter = new DrawMouseAdapter();
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
            timer = new Timer(time,new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    nextGeneration();
                    repaint();
                }
            });
        }

        private void drawLine(Point from, Point to) {
            final Graphics2D g = image.createGraphics();
            g.setColor(new Color(COLOR));
            g.drawLine(from.x/scale, from.y/scale, to.x/scale, to.y/scale);
            g.dispose();
            repaint();
        }

        private BufferedImage createBufferedImage(int width, int height) {
            return new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        }

        private void fillRandomly(BufferedImage image) {
            final Graphics2D g = image.createGraphics();
            g.setColor(new Color(COLOR));
            final int w = image.getWidth();
            final int h = image.getHeight();
            for (int x=0;x< w;x++) {
                for (int y=0;y<h;y++) {
                    if ((x+y) % 10 == 0 && Math.random()>0.8) {
                        g.drawRect(x, y, 3,3);
                    }
                }            
            }
            g.dispose();
            repaint();
        }


        private static final int COLOR = 0x484848;
        private final ConvolveOp convolve = new ConvolveOp(new Kernel(3, 3, new float[]{
                1, 1, 1,
                1, 0.5f, 1,
                1, 1, 1}));

        private final LookupOp lookup = createLookupOp();

        private LookupOp createLookupOp() {
            final short[] lookupTable = new short[256];
            lookupTable[180] = 0x48;
            lookupTable[216] = 0x48;
            lookupTable[252] = 0x48;
            return new LookupOp(new ShortLookupTable(0, lookupTable), null);
        }

        private void nextGeneration() {
            image=convolve.filter(image, null);
            lookup.filter(image, image);
            generation++;
        }

        @Override
        public void paint(Graphics graphics) {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }




        

        private class DrawMouseAdapter extends MouseAdapter {
            Point lastPos;

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                switch (mouseEvent.getClickCount()) {
                    case 1: {
                        drawLine(mouseEvent.getPoint(),mouseEvent.getPoint());
                        break;
                    }
                    case 2: {
                        if (timer.isRunning()) timer.stop();
                        else timer.start();
                        System.out.println("generation = " + generation);
                        break;
                    }
                    case 3: {
                        image = createBufferedImage(image.getWidth(), image.getHeight());
                        break;
                    }
                }
            }

            public void mouseDragged(MouseEvent mouseEvent) {
                if (lastPos != null) {
                    final Point pos = mouseEvent.getPoint();
                    drawLine(lastPos, pos);
                    lastPos = pos;
                }
            }

            public void mousePressed(MouseEvent mouseEvent) {
                lastPos = mouseEvent.getPoint();
            }

            public void mouseReleased(MouseEvent mouseEvent) {
                lastPos = null;
            }
        }
        private final static String PATTERN =
                        "     X              " +
                        "      X             " +
                        "    XXX             " +
                        "                    " +
                        "                    " +
                        "                    " +
                        "                    " +
                        "                    " +
                        "                    ";

        private BufferedImage createImage(String pattern, int width) {
            final int size = pattern.length();
            int height = size / width;
            int[] pixels = new int[size];
            for (int i = 0; i < size; i++) {
                if (pattern.charAt(i) != ' ') pixels[i] = COLOR;
            }
            final BufferedImage image = createBufferedImage(width, height);
            image.setRGB(0, 0, width, height, pixels, 0, width);
            return image;
        }
    }
}
