package core.place;

import java.util.ArrayList;

import org.osmdroid.util.GeoPoint;

import core.models.Place;

public class PlaceUtil {
	public static ArrayList<Place> getDummyData(){
		ArrayList<Place> location = new ArrayList<Place>();
		
		Place locationA = new Place("De dam", "Dit is de dam. Hier is het heel interessant!", new GeoPoint(52.372823, 4.893626));
		location.add(locationA);
		Place locationB = new Place("De dam", "Dit is de dam. Hier is het heel interessant!", new GeoPoint(52.368118, 4.89339));
		location.add(locationB);
		Place locationC = new Place("De dam", "Dit is de dam. Hier is het heel interessant!", new GeoPoint(52.36861, 4.889444));
		location.add(locationC);
		
		return location;
	}
}
