package it.unisalento.se.saw.util;

public class DistanceGPSUtil {
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double distance(double lat_start, double lat_end, double lon_start, double lon_end) {

	    final int R = 6371; // Radius of the earth
	    double latDistance, lonDistance, a, c;
	    double dis, distance;
	    latDistance = Math.toRadians(lat_end - lat_start);
	    lonDistance = Math.toRadians(lon_end - lon_start);
	    a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat_start)) * Math.cos(Math.toRadians(lat_end))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    
	    
	    c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    dis = R * c; // convert to meters
	    dis = Math.pow(dis, 2);
	    distance = Math.sqrt(dis);
	    return distance;
	    
	}

}
