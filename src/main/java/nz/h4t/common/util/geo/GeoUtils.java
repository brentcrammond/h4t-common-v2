package nz.h4t.common.util.geo;

/**
 * The GeoUtils class provides utility methods for
 * calculations related to geographical distances.
 * <p>
 * This class contains a single method, distance(), which
 * calculates the distance between two sets of latitude
 * and longitude coordinates on Earth using the Haversine
 * formula.
 * <p>
 * The distance calculation is provided in kilometers by
 * default, but can be easily converted to other units of
 * measurement if necessary.
 */
public class GeoUtils {
    /**
     * Calculates the great-circle distance between two points on the Earth's surface
     * specified by their latitude and longitude using the Haversine formula.
     *
     * @param lat1 the latitude of the first point in decimal degrees
     * @param lon1 the longitude of the first point in decimal degrees
     * @param lat2 the latitude of the second point in decimal degrees
     * @param lon2 the longitude of the second point in decimal degrees
     * @return the distance between the two points in kilometers
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }
}
