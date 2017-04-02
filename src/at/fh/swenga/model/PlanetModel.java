package at.fh.swenga.model;

public class PlanetModel implements Comparable<PlanetModel> {

	private int ssn;
	
	private String name;
	private String surface;
	private Float size;
	
	public PlanetModel() {
		
	}
	
	public PlanetModel(int ssn, String name, String surface) {
		super();
		this.ssn = ssn;
		this.name = name;
		this.surface = surface;
	}
	
	public PlanetModel(int ssn, String name, String surface, float size) {
		super();
		this.ssn = ssn;
		this.name = name;
		this.surface = surface;
		this.size = size;
	}

	@Override
	public int compareTo(PlanetModel o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(size);
		result = prime * result + ssn;
		result = prime * result + ((surface == null) ? 0 : surface.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanetModel other = (PlanetModel) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(size) != Float.floatToIntBits(other.size))
			return false;
		if (ssn != other.ssn)
			return false;
		if (surface == null) {
			if (other.surface != null)
				return false;
		} else if (!surface.equals(other.surface))
			return false;
		return true;
	}
	
	
	
}
