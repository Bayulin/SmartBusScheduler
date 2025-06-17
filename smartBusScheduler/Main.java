package smartBusScheduler;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Step 1: Generate simulated passenger demand data
        List<PassengerRecord> simulatedData = PassengerDatacollector.simulateInput();

        // Step 2: Predict demand at each stop and time
        Map<String, Integer> demandMap = DemandPredictor.predict(simulatedData);

        // Step 3: Define high-demand time slots
        List<String> peakHours = Arrays.asList("07:00", "08:00", "11:00", "12:00", "16:00", "17:00");

        for (String time : peakHours) {
            System.out.println("=== " + time + " ===");
            List<String> result = RoutePlanner.planRoute(demandMap, time);
            for (String line : result) {
                System.out.println(line);
            }
            System.out.println();
        }

        // Step 4: Visualize each actual route per peak hour
        RouteGraphVisualizer.showLabeledRoutes(RouteRecorder.getAllRoutes());
    }
}



