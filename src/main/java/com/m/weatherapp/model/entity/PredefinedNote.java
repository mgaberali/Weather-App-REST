package com.m.weatherapp.model.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "predefined_note", catalog = "weatherdb")
public class PredefinedNote implements java.io.Serializable {

	private Integer id;
	private Float minTemp;
	private Float maxTemp;
	private String predefinedNoteText;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false, length = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "min_temp", nullable = false)
	public Float getMinTemp() {
		return minTemp;
	}
	
	public void setMinTemp(Float minTemp) {
		this.minTemp = minTemp;
	}
	
	@Column(name = "max_temp", nullable = false)
	public Float getMaxTemp() {
		return maxTemp;
	}
	
	public void setMaxTemp(Float maxTemp) {
		this.maxTemp = maxTemp;
	}
	
	@Column(name = "note", length = 50)
	public String getPredefinedNoteText() {
		return predefinedNoteText;
	}
	
	public void setPredefinedNoteText(String note) {
		this.predefinedNoteText = note;
	}
	
//	@Override
//	public int hashCode() {
//		return (int) (minTemp + maxTemp);
//	}
//	
//	@Override
//	public boolean equals(Object obj) {
//		
//		if(obj instanceof PredefinedNote){
//			
//			PredefinedNote other = (PredefinedNote) obj;
//			
//			if(minTemp.equals(other.minTemp) && maxTemp.equals(other.maxTemp))
//				return true;
//			
//		}
//		
//		return false;
//	}
	
}
