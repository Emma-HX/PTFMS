package gps.util;

import java.util.UUID;
/**
 * generate a trip id
 */
public class TripIdGenerator {
    public static String getNextTripId() {
        return UUID.randomUUID().toString();
    }
}
