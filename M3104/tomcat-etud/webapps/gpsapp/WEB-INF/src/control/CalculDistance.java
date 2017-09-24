package control;

import java.util.ArrayList;

public class CalculDistance {
	private ArrayList<Point> coordonnees;
	private static final double REarth = 6378.137;
	
	public CalculDistance() {
		coordonnees = new ArrayList<>();
	}
	
	public static double compute(double lat1, double long1, double lat2, double long2) {
		return REarth * Math.acos(Math.sin(convertToRad(lat2))
                * Math.sin(convertToRad(lat1))
                + Math.cos(convertToRad(lat2))
                * Math.cos(convertToRad(lat1))
                * Math.cos(convertToRad(long2)
                - convertToRad(long1)));
	}
	
	private static double convertToRad(double degre) {
		return Math.PI * degre / 180;
	}
	
}
