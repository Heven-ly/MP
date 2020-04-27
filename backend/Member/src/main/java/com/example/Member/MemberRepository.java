package com.example.Member;



import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
	
	Member findByPseudoAndMdp(String pseudo, String mdp);
	Member findByMailAndMdp(String mail, String mdp);
	boolean existsByMail(String mail);
}
