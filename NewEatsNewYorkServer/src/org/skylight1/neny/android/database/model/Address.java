package org.skylight1.neny.android.database.model;

import static java.lang.String.format;

public class Address {
	private String building;

	private String street;

	private String zipCode;

	public Address(String aBuilding, String aStreet, String aZipCode) {
		super();
		building = aBuilding;
		street = aStreet;
		zipCode = aZipCode;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String aBuilding) {
		building = aBuilding;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String aStreet) {
		street = aStreet;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String aZipCode) {
		zipCode = aZipCode;
	}

	@Override
	public String toString() {
		return format("%s[building=%s,streetAs=%s,zip=%s]", getClass().getSimpleName(), building, street, zipCode);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((building == null) ? 0 : building.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Address other = (Address) obj;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
}
