package org.kilon.android.trainride.model.ride;

import java.util.Set;


public interface RideGroup extends Ride {
	void addRide(Ride ride);
	Ride getRide(int location);
	int size();
	Set<Integer> getColors();
}
