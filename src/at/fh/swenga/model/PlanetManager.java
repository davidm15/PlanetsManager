package at.fh.swenga.model;

import java.util.ArrayList;
import java.util.List;

public class PlanetManager {

	private List<PlanetModel> planets = new ArrayList<PlanetModel>();
	
	public void addPlanet(PlanetModel planet) {
		planets.add(planet);
	}
	
	public boolean isEmpty() {
		return planets.isEmpty();
	}
	
	public PlanetModel getPlanetbySSN(int ssn) {
		for (PlanetModel planetModel : planets) {
			if (planetModel.getSsn() == ssn) {
				return planetModel;
			}
		}
		return null;
	}
	
	public List<PlanetModel> getAllPlanets() {
		return planets;
	}
	
	public List<PlanetModel> getFilteredPlanets(String searchString) {
			
		if (searchString == null || searchString.equals("")) {
			return planets;
		}

		// List for results
		List<PlanetModel> filteredList = new ArrayList<PlanetModel>();

		// check every employee
		for (PlanetModel planetModel : planets) {
			
			if ((planetModel.getName() != null && planetModel.getName().contains(searchString))
					|| (planetModel.getSurface() != null && planetModel.getSurface().contains(searchString))) {
				filteredList.add(planetModel);
			}
		}
		return filteredList;
	}
	
	public boolean remove(int ssn) {
		return planets.remove(new PlanetModel(ssn,null,null));
	}
}
