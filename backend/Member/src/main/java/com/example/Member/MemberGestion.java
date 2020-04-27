package com.example.Member;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MemberGestion {
	
	MemberRepository memberRepository;

	@Autowired
	public MemberGestion(MemberRepository memberRepository) {
		super();
		this.memberRepository = memberRepository;
		Member first = new Member("Doe","John","DJ","mymail@mydomain.com","myadresse","99887","0698453212","mdp");
		Member second = new Member("Doe","Jane","jane01","jane@gmail.com","myadresse2","12345","0698453212","jane8");
		Member third = new Member("Smith","Matt","SM","matt@smith.com","mon adresse avec une longue chaine de caractere afin de verifier l'affichage des longue chaine dans la rubrique mon compte","98765","0698453212","smith");
		
		memberRepository.save(first);
		memberRepository.save(second);
		memberRepository.save(third);
	}
	
	
	@GetMapping("/getByMail/{mail}/{mdp}")
	@ResponseStatus(HttpStatus.OK)
	public Member getByMail(@PathVariable("mail") String mail,@PathVariable("mdp") String mdp){
		return memberRepository.findByMailAndMdp(mail,mdp);
	}
	
	@GetMapping("/getByPseudo/{pseudo}/{mdp}")
	@ResponseStatus(HttpStatus.OK)
	public Member getByPseudo(@PathVariable("pseudo") String pseudo,@PathVariable("mdp") String mdp){
		return memberRepository.findByPseudoAndMdp(pseudo,mdp);
	}
	
	@GetMapping("/Members")
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Member> getMembers(){
		return memberRepository.findAll();
	}
	
	@PostMapping("/Inscription/Member")
	public String form_inscription(@RequestBody Member membre) {
		memberRepository.save(membre);
		return "Success";
	}
	
	@PostMapping("/getPhoneNumber")
	public String getPhoneNumber(@RequestBody long myid) {
		String def = "Il y a eu un problème merci de nous contacter pour plus d'information";
		String response = memberRepository.findById(myid).get().getPhone();
		return response != null? response:def;
	}
	
	@PostMapping("/getMail")
	public String getMail(@RequestBody long myid) {
		String def = "Il y a eu un problème merci de nous contacter pour plus d'information";
		String response = memberRepository.findById(myid).get().getMail();
		return response != null? response:def;
	}
	
	@PostMapping("/isMemberExist")
	public boolean isMemberExist(@RequestBody String mail) {
		return memberRepository.existsByMail(mail);
	}
	
}