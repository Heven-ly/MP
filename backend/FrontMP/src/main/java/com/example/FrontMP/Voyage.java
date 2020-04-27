package com.example.FrontMP;

public class Voyage {
	
	private long id;
	private String ville_depart;
	private String ville_arrive;
	private String lieu_depart;
	private String lieu_arrive;
	private float prix;
	private int place;
	private String date_depart;
	private String heure_depart;
	private String date_arrive;
	private String heure_arrive;
	private int options;
	private long idconducteur;
	
	
	public Voyage() {
		super();
	}

	public Voyage(String ville_depart, String ville_arrive, String lieu_depart, String lieu_arrive, float prix,
			int place, String date_depart, String heure_depart, String date_arrive, String heure_arrive, int options) {
		super();
		this.ville_depart = ville_depart;
		this.ville_arrive = ville_arrive;
		this.lieu_depart = lieu_depart;
		this.lieu_arrive = lieu_arrive;
		this.prix = prix;
		this.place = place;
		this.date_depart = date_depart;
		this.heure_depart = heure_depart;
		this.date_arrive = date_arrive;
		this.heure_arrive = heure_arrive;
		this.options = options;
	}

	public String getVille_depart() {
		return ville_depart;
	}


	public void setVille_depart(String ville_depart) {
		this.ville_depart = ville_depart;
	}


	public String getVille_arrive() {
		return ville_arrive;
	}


	public void setVille_arrive(String ville_arrive) {
		this.ville_arrive = ville_arrive;
	}


	public String getLieu_depart() {
		return lieu_depart;
	}


	public void setLieu_depart(String lieu_depart) {
		this.lieu_depart = lieu_depart;
	}


	public String getLieu_arrive() {
		return lieu_arrive;
	}


	public void setLieu_arrive(String lieu_arrive) {
		this.lieu_arrive = lieu_arrive;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public int getPlace() {
		return place;
	}


	public void setPlace(int place) {
		this.place = place;
	}

	public String getDate_depart() {
		return date_depart;
	}

	public void setDate_depart(String date_depart) {
		this.date_depart = date_depart;
	}

	public String getHeure_depart() {
		return heure_depart;
	}

	public void setHeure_depart(String heure_depart) {
		this.heure_depart = heure_depart;
	}

	public String getDate_arrive() {
		return date_arrive;
	}

	public void setDate_arrive(String date_arrive) {
		this.date_arrive = date_arrive;
	}

	public String getHeure_arrive() {
		return heure_arrive;
	}

	public void setHeure_arrive(String heure_arrive) {
		this.heure_arrive = heure_arrive;
	}

	public int getOptions() {
		return options;
	}


	public void setOptions(int options) {
		this.options = options;
	}

	public long getIdconducteur() {
		return idconducteur;
	}

	public void setIdconducteur(long idconducteur) {
		this.idconducteur = idconducteur;
	}

	@Override
	public String toString() {
		return "Voyage [id=" + id + ", ville_depart=" + ville_depart + ", ville_arrive=" + ville_arrive
				+ ", lieu_depart=" + lieu_depart + ", lieu_arrive=" + lieu_arrive + ", prix=" + prix + ", place="
				+ place + ", date_depart=" + date_depart + ", heure_depart=" + heure_depart + ", date_arrive="
				+ date_arrive + ", heure_arrive=" + heure_arrive + ", options=" + options + ", idconducteur="
				+ idconducteur + "]";
	}
	
}
