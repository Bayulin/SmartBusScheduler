package smartBusScheduler;

import java.util.*;

/**
 * RoutePlanner handles shortest path logic with early return
 * if a bus is full.
 */
public class RoutePlanner {

    private static final int BUS_CAPACITY = 30;

    // Define fixed route for all peak hours
    private static final List<String> ROUTE = List.of(
        "Dormitory", "FBMK", "FSKTM", "FacultyScience",
        "Library", "FacultyFoodTech", "FacultyEducation", "Dormitory"
    );

    /**
     * Plans the route at a given time slot based on demand.
     * Returns output log and stores the actual traversed route.
     */
    public static List<String> planRoute(Map<String, Integer> demandMap, String timeSlot) {
        List<String> output = new ArrayList<>();
        List<String> actualRoute = new ArrayList<>();
        output.add("Scheduling for time: " + timeSlot);

        int remainingCapacity = BUS_CAPACITY;
        boolean full = false;

        for (String stop : ROUTE) {
            actualRoute.add(stop);  // Add this stop to the actual visited route
            String key = stop + "_" + timeSlot;
            int demand = demandMap.getOrDefault(key, 0);

            if (demand == 0) {
                output.add("Stop " + stop + ": no passengers");
                continue;
            }

            if (remainingCapacity >= demand) {
                remainingCapacity -= demand;
                output.add("Stop " + stop + ": picked up " + demand + " passengers, remaining capacity: " + remainingCapacity);
            } else {
                output.add("Stop " + stop + ": only picked up " + remainingCapacity + " passengers (bus full)");
                full = true;
                break;
            }
        }

        if (full) {
            output.add("Bus is full, returning directly to Dormitory.");
            actualRoute.add("Dormitory"); // Return to Dormitory
        } else {
            output.add("Route completed, returning to Dormitory.");
            actualRoute.add("Dormitory"); // End at Dormitory as usual
        }

        // Store actualRoute into the map for visualization (key = timeSlot)
        RouteRecorder.record(timeSlot, actualRoute);

        return output;
    }
}
