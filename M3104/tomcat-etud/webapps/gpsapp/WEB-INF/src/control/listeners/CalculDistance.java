import java.util.ArrayList;

public class CalculDistance {
	private ArrayList<Point> coordonnees;
	private static final double REarth = 6378.137;
	
	public CalculDistance() {
		coordonnees = new ArrayList<>();
	}
	
	public void addPoint(double latitude, double longitude) {
		coordonnees.add(new Point(latitude, longitude));
	}
	
	public static double compute(Point p1, Point p2) {
		return REarth * Math.acos(Math.sin(convertToRad(p2.getLatitude())) * Math.sin(convertToRad(p1.getLatitude())) + Math.cos(convertToRad(p2.getLatitude())) * Math.cos(convertToRad(p1.getLatitude())) * Math.cos(convertToRad(p2.getLongitude()) - convertToRad(p1.getLongitude())));
	}
	
	private static double convertToRad(double degre) {
		return Math.PI * degre / 180;
	}
	
	private class Point	{
		private double latitude;
		private double longitude;
		
		public Point(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}
		
		public double getLatitude() { return latitude; }
		public double getLongitude() { return longitude; }
	}
	
}
