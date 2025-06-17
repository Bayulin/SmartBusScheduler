package smartBusScheduler;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * RouteMapDrawer is a visual component that displays a simplified route map
 * of bus stops using Java Swing. Each stop is shown as a blue circle, and the
 * connections between stops are drawn as lines.
 */
public class RouteMapDrawer extends JPanel {

    private final List<String> route;

    /**
     * Constructor that takes the route (list of stop names) to be visualized.
     * @param route A list of stop names representing the bus route.
     */
    public RouteMapDrawer(List<String> route) {
        this.route = route;
        setPreferredSize(new Dimension(800, 400)); // Set canvas size
    }

    /**
     * Custom painting method that draws the route as a series of stops and connections.
     * @param g The Graphics object used to draw the UI components.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int startX = 100;
        int y = 200;
        int nodeSpacing = 90;

        for (int i = 0; i < route.size(); i++) {
            int x = startX + i * nodeSpacing;

            // Draw a blue circle for the stop
            g.setColor(Color.BLUE);
            g.fillOval(x - 10, y - 10, 20, 20);

            // Draw the stop name above the circle
            g.setColor(Color.BLACK);
            g.drawString(route.get(i), x - 30, y - 20);

            // Draw a line to the next stop
            if (i < route.size() - 1) {
                int nextX = startX + (i + 1) * nodeSpacing;
                g.drawLine(x, y, nextX, y);
            }
        }

        // Draw a simple red rectangle to represent the bus
        g.setColor(Color.RED);
        int busX = startX + (route.size() / 2) * nodeSpacing - 10;
        g.fillRect(busX, y + 30, 40, 20);
        g.setColor(Color.WHITE);
        g.drawString("Bus", busX + 5, y + 45);
    }

    /**
     * Static method to launch the route drawing window.
     * @param route A list of stop names representing the route to be drawn.
     */
    public static void drawRoute(List<String> route) {
        JFrame frame = new JFrame("Bus Route Map");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new RouteMapDrawer(route));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
