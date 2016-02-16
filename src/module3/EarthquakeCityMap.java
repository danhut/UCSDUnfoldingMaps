package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author danhut
 * Date: July 17, 2015
 * */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this.  It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;
	
	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	// The map
	private UnfoldingMap map;
	
	//feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	
	public void setup() {
		size(1200, 800, OPENGL);

		if (offline) {
		    map = new UnfoldingMap(this, 200, 50, 1000, 800, new MBTilesMapProvider(mbTilesString));
		    earthquakesURL = "2.5_week.atom"; 	// Same feed, saved Aug 7, 2015, for working offline
		}
		else {
			map = new UnfoldingMap(this, 200, 50, 1000, 800, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			//earthquakesURL = "2.5_week.atom";
		}
		
	    map.zoomToLevel(2);
	    MapUtils.createDefaultEventDispatcher(this, map);	
			
	    // The List you will populate with new SimplePointMarkers
	    List<Marker> markers = new ArrayList<Marker>();

	    //Use provided parser to collect properties for each earthquake
	    //PointFeatures have a getLocation method
	    List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
	    
	    	    	  
	    // For each quake initialise the marker
	    for (PointFeature quake : earthquakes) {
	    	markers.add(0,createMarker(quake)); 
	    }
	    
	    // Add markers to the map
		map.addMarkers(markers);
	}
		
	// Helper method that takes in an earthquake feature and 
	// returns a SimplePointMarker for that earthquake
	private SimplePointMarker createMarker(PointFeature feature)
	{

	    int yellow = color(255, 255, 0);
	    int red = color(255,0,0);
	    int blue = color(0,0,255);
	    SimplePointMarker newMarker = new SimplePointMarker();
	    newMarker.setLocation(feature.getLocation());
		
    	Object magObj = feature.getProperty("magnitude");
    	float mag = Float.parseFloat(magObj.toString());
    	
    	if (mag < 4) {
    		newMarker.setColor(blue);
    		newMarker.setRadius(5);
    	}
    	else if (4 <= mag && mag <= 5.4) {
    		newMarker.setColor(yellow);
    		newMarker.setRadius(9);
    	}
    	else {
    		newMarker.setColor(red);
    		newMarker.setRadius(15);
    	}		
		
		return newMarker;
	}
	
	
	public void draw() {
	    background(10);
	    map.draw();
	    addKey();
	}


	// helper method to draw key in GUI
	private void addKey() 
	{   
		// key container
		fill(0, 102, 153);
		rect(10, 20, 175, 400);
		fill(0, 0, 0);
		textSize(20);
		text("Map Key", 40, 50); 

		// small
		fill(0, 0, 255);
		ellipse(20, 70, 5, 5);
		textSize(10);
		text("Magnitude < 4.0", 40, 80); 		
		
		// medium
		fill(255, 255, 0);
		ellipse(20, 90, 9, 9);
		text("Magnitude < 4.0 & <= 5.4", 40, 100); 
		
		// large
		fill(255, 0, 0);
		ellipse(20, 110, 15, 15);
		text("Magnitude > 5.4", 40, 120); 		
	
	}
}
