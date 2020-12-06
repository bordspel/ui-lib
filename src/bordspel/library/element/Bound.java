package bordspel.library.element;

import java.util.ArrayList;

public class Bound {
	
	private ArrayList<Location> locations = new ArrayList<>();
	
	public Bound() {
		
	}
	
	public void addLocation(int x, int y) {
		this.locations.add(new Location(x, y));
	}
	
	public void removeLocation(Location location) {
		this.locations.remove(location);
	}
	
	public ArrayList<Location> getLocations() {
		return this.locations;
	}
	
	public boolean isInBound(int x, int y) {
		Location point = new Location(x, y);
		
		// We cannot check less then 3 locations, otherwise it will result in an error.
		if (this.locations.size() < 3)
			return false;
		
		// We need to check if the point is within at least 1 triangle of points. If that is the case the point lies within the bounds.
		for (int i=0; i < this.locations.size() - 2; i++) {
			Location loc1 = this.locations.get(i);
			Location loc2 = this.locations.get(i + 1);
			Location loc3 = this.locations.get(i + 2);
			
			boolean isInTriangle = isPointInTriangle(point, loc1, loc2, loc3);
			
			if (isInTriangle)
				return true;
		}
		
		return false;
	}
	
	private float sign(Location p1, Location p2, Location p3) {
		return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
	}
	
	private boolean isPointInTriangle(Location pt, Location v1, Location v2, Location v3) {
	    float d1, d2, d3;
	    boolean has_neg, has_pos;
	    
	    d1 = sign(pt, v1, v2);
	    d2 = sign(pt, v2, v3);
	    d3 = sign(pt, v3, v1);

	    has_neg = (d1 < 0) || (d2 < 0) || (d3 < 0);
	    has_pos = (d1 > 0) || (d2 > 0) || (d3 > 0);

	    return !(has_neg && has_pos);
	}
	
}

class Location {
	
	public int x;
	public int y;
	
	Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
