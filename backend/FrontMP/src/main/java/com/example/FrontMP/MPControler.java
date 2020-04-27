package com.example.FrontMP;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*")
@RestController
public class MPControler {
	
	@Autowired
	DiscoveryClient discoveryClient;
	
	@HystrixCommand(fallbackMethod = "defaultMessage3")
	@PostMapping("/Connexion")
	public Member Connexion(@RequestBody LoginForm Login) {
		List<ServiceInstance> instances = discoveryClient.getInstances("MemberWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address;
		if(Login.getMail() != "") {
			microservice1Address = "http://" + hostname + ":" + port + "/getByMail/" + Login.getMail() + "/" + Login.getMdp();
		}else {
			microservice1Address = "http://" + hostname + ":" + port + "/getByPseudo/" + Login.getPseudo() + "/" + Login.getMdp();;
		}
		Member response =
				restTemplate.getForObject(microservice1Address, Member.class);
		return response;
	}
	
	public Member defaultMessage3(@RequestBody LoginForm Login) {
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "defaultMessage2")
	@GetMapping("/members")
	public List<Member> getMember() {
		List<ServiceInstance> instances = discoveryClient.getInstances("MemberWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/mo";
		List<Member> response =
				restTemplate.getForObject(microservice1Address, List.class);
		return response;
	}
	
	public List<Member> defaultMessage2() {
		return null;
	}


	@HystrixCommand(fallbackMethod = "defaultMessage")
	@PostMapping("/Inscription/Member")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public String addmember(@RequestBody Member membre) {
		
		if(membre.getNom() != null) {
			List<ServiceInstance> instances = discoveryClient.getInstances("MemberWebService");
			ServiceInstance test = instances.get(0);
			String hostname = test.getHost();
			int port = test.getPort();
			RestTemplate restTemplate = new RestTemplate();
			String microservice1Address = "http://" + hostname + ":" + port + "/Inscription/Member";
			String response =
					restTemplate.postForObject(microservice1Address, membre, String.class);
			return response;
		}
		return "no name or member not found";
	}
	
	@ResponseBody
	public String defaultMessage(@RequestBody Member membre) {
		return "Member Web Service not found";
	}
	
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@PostMapping("/Inscription/Voyage")
	@ResponseStatus(HttpStatus.OK)
	public String voyage(@RequestBody Voyage voyage) {
		System.out.println(voyage.toString());
		if(voyage.getVille_arrive() != null) {
			List<ServiceInstance> instances = discoveryClient.getInstances("VoyageWebService");
			ServiceInstance test = instances.get(0);
			String hostname = test.getHost();
			int port = test.getPort();
			RestTemplate restTemplate = new RestTemplate();
			String microservice1Address = "http://" + hostname + ":" + port + "/Inscription/Voyage";
			String response =
					restTemplate.postForObject(microservice1Address, voyage, String.class);
			return response;
		}
		return "voyage not initialized";
	}
	
	public String defaultMessage(@RequestBody Voyage voyage) {
		
		return "Voyages Web Service not found";
	}
	
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@GetMapping("/Voyages")
	@ResponseStatus(HttpStatus.OK)
	public List<Voyage> voyages() {
																List<ServiceInstance> instances = discoveryClient.getInstances("VoyageWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/Voyages";
		List<Voyage> response =
				restTemplate.getForObject(microservice1Address, List.class);
		return response;
	}
	
	public List<Voyage> defaultMessage() {
		
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@PutMapping("/Achat")
	@ResponseStatus(HttpStatus.OK)
	public String achat(@RequestBody Panier panier) {
		
		List<ServiceInstance> instances = discoveryClient.getInstances("VoyageWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/Achat";
		restTemplate.put(microservice1Address, panier);
		return "Success";
	}
	
	public String defaultMessage(@RequestBody Panier panier) {
		
		return "Sorry we can't";
	}
	
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@PostMapping("/Proposition")
	@ResponseStatus(HttpStatus.OK)
	public List<Voyage> prop(@RequestBody long id) {
		List<ServiceInstance> instances = discoveryClient.getInstances("VoyageWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/Proposition";
		List<Voyage> response =
				restTemplate.postForObject(microservice1Address, id, List.class);
		return response;
	}
	@HystrixCommand(fallbackMethod = "defaultMessage")
	@PostMapping("/Historique")
	@ResponseStatus(HttpStatus.OK)
	public List<Voyage> hist(@RequestBody long id) {
		List<ServiceInstance> instances = discoveryClient.getInstances("VoyageWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/Historique";
		List<Voyage> response =
				restTemplate.postForObject(microservice1Address, id, List.class);
		return response;
	}
	public List<Voyage> defaultMessage(@RequestBody long id) {
		
		return null;
	}
	
	@HystrixCommand(fallbackMethod = "defaultgetInformation")
	@PostMapping("/getPhoneNumber")
	public String getPhoneNumber(@RequestBody long myid) {
		List<ServiceInstance> instances = discoveryClient.getInstances("MemberWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/getPhoneNumber";
		String response =
				restTemplate.postForObject(microservice1Address,myid, String.class);
		return response;
	}
	
	public String defaultgetInformation(@RequestBody long myid) {
		return "An error expected please contact us for more informations";
	}
	
	@HystrixCommand(fallbackMethod = "defaultgetInformation")
	@PostMapping("/getMail")
	public String getMail(@RequestBody long myid) {
		List<ServiceInstance> instances = discoveryClient.getInstances("MemberWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/getMail";
		String response =
				restTemplate.postForObject(microservice1Address,myid, String.class);
		return response;
	}
	
	@HystrixCommand(fallbackMethod = "defaultExistMember")
	@PostMapping("/isMemberExist")
	public boolean isMemberExist(@RequestBody String mail) {
		List<ServiceInstance> instances = discoveryClient.getInstances("MemberWebService");
		ServiceInstance test = instances.get(0);
		String hostname = test.getHost();
		int port = test.getPort();
		RestTemplate restTemplate = new RestTemplate();
		String microservice1Address = "http://" + hostname + ":" + port + "/isMemberExist";
		boolean response =
				restTemplate.postForObject(microservice1Address, mail, boolean.class);
		return response;
	}
	
	public boolean defaultExistMember(@PathVariable("idconducteur") String idconducteur) {
		return true;
	}
	
}
