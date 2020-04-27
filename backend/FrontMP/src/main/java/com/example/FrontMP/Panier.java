package com.example.FrontMP;

import java.util.List;

public class Panier {
	private List<Long> voyageid;
	private List<Integer> places;
	private long idvoyageur;
	
	public Panier() {
		super();
	}

	public Panier(List<Long> voyageid, List<Integer> places, long idvoyageur) {
		super();
		this.voyageid = voyageid;
		this.places = places;
		this.idvoyageur = idvoyageur;
	}

	public List<Long> getVoyageid() {
		return voyageid;
	}

	public void setVoyageid(List<Long> voyageid) {
		this.voyageid = voyageid;
	}

	public List<Integer> getPlaces() {
		return places;
	}

	public void setPlaces(List<Integer> places) {
		this.places = places;
	}

	public long getIdvoyageur() {
		return idvoyageur;
	}

	public void setIdvoyageur(long idvoyageur) {
		this.idvoyageur = idvoyageur;
	}

	@Override
	public String toString() {
		return "Panier [voyageid=" + voyageid + ", places=" + places + ", idvoyageur=" + idvoyageur + "]";
	}
	
	
	
}
