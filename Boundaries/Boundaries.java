public class Boundaries {
    public static void main(String[] args) {
        String locationName = "1600 Pennsylvania Ave NW, Washington DC";
        GeographicLocation geographicLocation = new GeographicLocation();
        System.out.println("LATITUDE : " + geographicLocation.getLatitudeOfSpecifiedLocation(locationName));
        System.out.println("LONGITUDE : " + geographicLocation.getLongitudeOfSpecifiedLocation(locationName));
    }
}
