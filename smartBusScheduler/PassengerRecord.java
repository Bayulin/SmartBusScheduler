package smartBusScheduler;

/**
 * Represents one passenger's check-in record at a stop and time.
 */
public class PassengerRecord {
    private final String stop;
    private final String time;

    public PassengerRecord(String stop, String time) {
        this.stop = stop;
        this.time = time;
    }

    public String getStop() {
        return stop;
    }

    public String getTime() {
        return time;
    }
}



