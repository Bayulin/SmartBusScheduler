package smartBusScheduler;

import java.util.*;

/**
 * Generates simulated passenger check-in data for testing purposes.
 */
public class PassengerDatacollector {

    /**
     * Simulate 1000 passenger check-ins randomly across stops and time slots.
     * High demand is biased toward peak hours.
     * 
     * @return List of PassengerRecord containing stop and time
     */
    public static List<PassengerRecord> simulateInput() {
        List<PassengerRecord> records = new ArrayList<>();
        Random rand = new Random();

        String[] stops = {
            "Dormitory", "FBMK", "FSKTM", "FacultyScience",
            "Library", "FacultyFoodTech", "FacultyEducation"
        };

        String[] times = {
            "07:00", "08:00", "09:00", "10:00", "11:00", "12:00",
            "13:00", "14:00", "15:00", "16:00", "17:00"
        };

        for (int i = 0; i < 500; i++) {
            String stop = stops[rand.nextInt(stops.length)];

            // 60% chance to generate peak-hour record
            String time;
            int chance = rand.nextInt(10);
            if (chance < 6) {
                time = peakHourTime();
            } else {
                time = times[rand.nextInt(times.length)];
            }

            records.add(new PassengerRecord(stop, time));
        }

        return records;
    }

    // Return random time within peak hours
    private static String peakHourTime() {
        String[] peak = {"07:00", "08:00", "11:00", "12:00", "16:00", "17:00"};
        Random rand = new Random();
        return peak[rand.nextInt(peak.length)];
    }
}

