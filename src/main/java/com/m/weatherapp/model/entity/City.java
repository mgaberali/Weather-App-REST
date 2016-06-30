package com.m.weatherapp.model.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "city", catalog = "weatherdb", uniqueConstraints = @UniqueConstraint(columnNames = "city_name") )
public class City implements java.io.Serializable {

	private Integer cityId;
	private String cityName;
	private Set<Note> notes;

	public City() {
	}

	public City(String cityName) {
		this.cityName = cityName;
	}
	

	public City(Integer cityId, String cityName) {
		super();
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public City(String cityName, Set notes) {
		this.cityName = cityName;
		this.notes = notes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "city_id", unique = true, nullable = false)
	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	@Column(name = "city_name", unique = true, nullable = false, length = 30)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city", targetEntity = Note.class)
	public Set getNotes() {
		return this.notes;
	}

	public void setNotes(Set notes) {
		this.notes = notes;
	}
	
	@Override
	public int hashCode() {
		return cityName.length();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if(other instanceof City){
			
			City otherCity = (City) other;
			
			if(cityName.equals(otherCity.cityName))
				return true;
		}
		
		return false;
	}

}
