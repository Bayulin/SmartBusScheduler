package smartBusScheduler;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Visualizes multiple bus routes with labeled time slots in one window.
 */
public class RouteGraphVisualizer {

    /**
     * Displays multiple routes in a grid layout with corresponding time slot labels.
     *
     * @param routes Map from time (e.g., "08:00") to list of stops for that route.
     */
    public static void showLabeledRoutes(Map<String, List<String>> routes) {
        int rows = (int) Math.ceil(routes.size() / 2.0);
        JFrame frame = new JFrame("Bus Routes Visualization");
        frame.setLayout(new GridLayout(rows, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (Map.Entry<String, List<String>> entry : routes.entrySet()) {
            frame.add(new RoutePanel(entry.getValue(), entry.getKey()));
        }

        frame.setSize(1000, 800);
        frame.setVisible(true);
    }

    /**
     * JPanel for visualizing a single route with labeled stops.
     */
    static class RoutePanel extends JPanel {
        private final List<String> route;
        private final String title;

        public RoutePanel(List<String> route, String title) {
            this.route = route;
            this.title = title;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            int n = route.size();
            int radius = Math.min(width, height) / 3;
            int centerX = width / 2;
            int centerY = height / 2;
            int nodeRadius = 20;

            Point[] points = new Point[n];
            for (int i = 0; i < n; i++) {
                double angle = 2 * Math.PI * i / n;
                int x = centerX + (int) (radius * Math.cos(angle));
                int y = centerY + (int) (radius * Math.sin(angle));
                points[i] = new Point(x, y);
            }

            // Draw edges between stops
            g2.setColor(Color.BLUE);
            for (int i = 0; i < n - 1; i++) {
                g2.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
            }

            // Optional: close the loop
            g2.drawLine(points[n - 1].x, points[n - 1].y, points[0].x, points[0].y);

            // Draw nodes and labels
            g2.setColor(Color.BLACK);
            for (int i = 0; i < n; i++) {
                int x = points[i].x - nodeRadius / 2;
                int y = points[i].y - nodeRadius / 2;
                g2.fillOval(x, y, nodeRadius, nodeRadius);

                // Adjust label to avoid overlapping with the node
                g2.drawString(route.get(i), points[i].x + 10, points[i].y - 10);
            }

            // Draw title centered at top
            g2.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm = g2.getFontMetrics();
            int titleWidth = fm.stringWidth("Time: " + title);
            g2.drawString("Time: " + title, (width - titleWidth) / 2, 25);
        }
    }
}
