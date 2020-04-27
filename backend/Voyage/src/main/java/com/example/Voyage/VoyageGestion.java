package com.example.Voyage;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VoyageGestion {
	
	VoyageRepository voyageRepository;
	
	@Autowired
	public VoyageGestion(VoyageRepository voyageRepository) {
		super();
		this.voyageRepository = voyageRepository;
		Voyage first = new Voyage("Paris","Marseille","4e arrondissement","cathédrale",18,3,"12/02/1998","11:32","12/02/1998","11:32",4,1);
		Voyage second = new Voyage("Lyon", "Toulouse", "McDonald's sur la Grande place", "Gare près du centre ville", 14, 3, "12/02/2020", "11:40", "12/02/2020", "14:30", 0,1);
		Voyage third = new Voyage("Bordeaux", "Reims", "Starbuck à proximité de la gare", "Terminus b du tram 2", 20, 3, "12/02/2020", "11:40", "12/02/2020", "14:30", 15,2);
		this.voyageRepository.save(first);
		this.voyageRepository.save(second);
		this.voyageRepository.save(third);
		
	}
	
	@GetMapping("/Voyages")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Voyage> getVoyages(){
		return voyageRepository.findAll();
	}
	
	@GetMapping("/Voyages/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Optional<Voyage> getVoyagesById(@PathVariable("id") long id){
		return voyageRepository.findById(id);
	}
	
	@PostMapping("/Inscription/Voyage")
	public String form_inscription(@RequestBody Voyage voyage) {
		voyageRepository.save(voyage);
		return "Success";
	}
	
	@PostMapping("/Proposition")
	public List<Voyage> prop(@RequestBody long id) {
	
		return voyageRepository.findByIdconducteur(id);
	}
	
	@PostMapping("/Historique")
	public List<Voyage> hist(@RequestBody long id) {
		Iterable<Voyage> voyages = voyageRepository.findAll();
		List<Voyage> hist = new ArrayList<Voyage>();
		for (Voyage voyage : voyages) {
			if(voyage.getIdvoyageur().contains(id)) {
				hist.add(voyage);
			}
		}
		return hist;
	}
	
	
	@PutMapping("/Achat")
	public void achat(@RequestBody Panier panier) {
		List<Long> voyageid = panier.getVoyageid();
		List<Integer> places= panier.getPlaces();
		long idvoyageur = panier.getIdvoyageur();
		int index=0;
		Voyage voyage;
		int place;
		
		for(long id : voyageid) {
			voyage= voyageRepository.findById(id).get();
			place=voyage.getPlace();
			if(place - places.get(index) >= 0) {
				voyage.setPlace(place - places.get(index));
				voyage.getIdvoyageur().add(idvoyageur);
			}
			index++;
			voyageRepository.save(voyage);
		}
	}
}

/*@GetMapping("/getByMail/{mail}/{mdp}")
@ResponseStatus(HttpStatus.OK)
public Voyage getByMail(@PathVariable("mail") String mail,@PathVariable("mdp") String mdp){
	if(voyageRepository.count()==0) {
		voyageRepository.save(first);
	}
	Voyage retour = null;
	return retour;
}

@GetMapping("/getByPseudo/{pseudo}/{mdp}")
@ResponseStatus(HttpStatus.OK)
public Voyage getByPseudo(@PathVariable("pseudo") String pseudo,@PathVariable("mdp") String mdp){
	if(voyageRepository.count()==0) {
		voyageRepository.save(first);
	}
	Voyage retour = null;
	return retour;
}*/