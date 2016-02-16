package module6;

import demos.Airport;

public class BinarySearch {
	public static String findAirport (String toFind, Airport[] airports) {
		int low = 0;
		int high = airports.length - 1;
		int mid;
		
		while (low < high) {
			mid = (low + high) /2;
			int compare = toFind.compareTo(airports[mid].getCode3());
			
			if (compare < 0) {
				high = mid - 1;
			}
			
			if (compare > 0) {
				high = mid + 1;
			}
			
			else return airports[mid].getCode3();
		}
		return null;
	}

}
