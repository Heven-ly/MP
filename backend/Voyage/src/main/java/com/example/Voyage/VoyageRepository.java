package com.example.Voyage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VoyageRepository extends CrudRepository<Voyage,Long> {

	List<Voyage> findByIdconducteur(long id);

	List<Voyage> findByIdvoyageur(long id);
	

}
