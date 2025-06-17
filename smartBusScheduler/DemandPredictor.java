package smartBusScheduler;

import java.util.*;

/**
 * Predicts demand from collected passenger records by counting
 * the number of check-ins at each stop-time combination.
 */
public class DemandPredictor {

    /**
     * Count how many passengers appear at each (stop, timeSlot).
     * 
     * @param records list of simulated or collected passenger check-ins
     * @return map with key as "Stop_Time" and value as total demand
     */
    public static Map<String, Integer> predict(List<PassengerRecord> records) {
        Map<String, Integer> demandMap = new HashMap<>();

        for (PassengerRecord record : records) {
            String key = record.getStop() + "_" + record.getTime();
            demandMap.put(key, demandMap.getOrDefault(key, 0) + 1);
        }

        return demandMap;
    }
}

