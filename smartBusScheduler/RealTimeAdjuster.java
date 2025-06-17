package smartBusScheduler;

import java.util.*;

public class RealTimeAdjuster {

    private static final int THRESHOLD = 25;

    public static List<String> adjustSchedule(Map<String, Integer> demandMap) {
        List<String> warnings = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : demandMap.entrySet()) {
            String key = entry.getKey(); // e.g., Dormitory_08:00
            int demand = entry.getValue();

            if (demand > THRESHOLD) {
                warnings.add("⚠ High demand at " + key + ": " + demand + " passengers. Consider dispatching extra bus.");
            } else if (demand == 0) {
                warnings.add("ℹ No demand at " + key + ". Bus may skip this stop.");
            }
        }

        return warnings;
    }
}



