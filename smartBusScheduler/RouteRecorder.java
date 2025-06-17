package smartBusScheduler;

import java.util.*;

/**
 * Records actual traversed routes for each time slot,
 * for later visualization in route diagrams.
 */
public class RouteRecorder {

    // Map<Time, ActualVisitedRoute>
    private static final Map<String, List<String>> timeToRouteMap = new LinkedHashMap<>();

    /**
     * Records the actual route for a specific time.
     */
    public static void record(String timeSlot, List<String> actualRoute) {
        timeToRouteMap.put(timeSlot, new ArrayList<>(actualRoute));
    }

    /**
     * Retrieves all recorded routes by time.
     */
    public static Map<String, List<String>> getAllRoutes() {
        return timeToRouteMap;
    }
}
