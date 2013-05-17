package com.example.nastani;

public class NastaniData {
	private String title;
	private String headliner;
	private String imageUrl;
	private String city;
	private String venueName;
	private String opis;
	
	public NastaniData(String title, String headliner, String imageUrl, String city, String venueName, String opis) {
		super();
		this.title = title;
		this.headliner = headliner;
		this.imageUrl = imageUrl;
		this.city = city;
		this.venueName = venueName;
		this.opis = opis;
	}
	public String getheadliner() {
		return headliner;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public String getCity() {
		return city;
	}
	public String getvenueName() {
		return venueName;
	}
	public String getOpis() {
		return opis;
	}
	public String getTitle() {
		return title;
	}
}
