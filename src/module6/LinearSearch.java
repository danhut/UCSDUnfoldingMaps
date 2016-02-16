package module6;



public class LinearSearch {
	public static String findAirportCode (String toFind, Airport[] airports) {
		// toFind is a city name
		for (String airport : airports)
			if (toFind.equals(getCode3())) {
			    return toFind.equals(getCode3());
			}
		}
	}
}

public class LinearSearch {
	public static String findAirportCode (String toFind, Airport[] airports) {
		// toFind is a city name
		int idx = 0;
		while (idx < airports.length) {
			Airport a = airports[idx];
			if (toFind.equals(a.getCity())) {
			    return a.getCode3();
			}
		    idx ++;
		}
		return null;
	}
}