package model;

import javax.persistence.*;

@Entity
@Table(name = "galleries")
public class Gallery {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long gallery_id;
	
	@Column(nullable = false)
	private String name;
	
	@Column
	private String city;
	
	@Column(nullable = false)
	private String country;
	
	public String getName() {
		return name;
	}
	
	
	public Long getId() {
		return gallery_id;
	}
	
	public void setId(Long id) {
		this.gallery_id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

}
