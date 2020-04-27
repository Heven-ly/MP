package com.example.FrontMP;

public class LoginForm {
	private String pseudo;
	private String mail;
	private String mdp;
	
	
	public LoginForm() {
		super();
	}

	public LoginForm(String pseudo, String mail, String mdp) {
		super();
		this.pseudo = pseudo;
		this.mail = mail;
		this.mdp = mdp;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	@Override
	public String toString() {
		return "LoginForm [pseudo=" + pseudo + ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	
	
	
	
	
	
}

