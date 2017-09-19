package db;


public class Coordinates
{
    private int journey_id;
    private int journey_pos;
    private double latitude;
	private double longitude;

    public Coordinates(int journey_id, double latitude, double longitude) {
        this.journey_id = journey_id;
        this.journey_pos = journey_pos;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getJourney_id() {
        return journey_id;
    }

    public void setJourney_id(int journey_id) {
        this.journey_id = journey_id;
    }

    public int getJourney_pos() {
        return journey_pos;
    }

    public void setJourney_pos(int journey_pos) {
        this.journey_pos = journey_pos;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        String ret = "--- Coordinates ---";
        ret += "\nJOURNEY_ID = " + journey_id;
        ret += "\nJOURNEY_POS = " + journey_pos;
        ret += "\nLATITUDE = " + latitude;
        ret += "\nLONGITUDE" + longitude;
        ret += "\n---------------\n";
        return ret;
    }
}
