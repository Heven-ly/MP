new Vue({
	el: '#sub',
	data: {
		error: null,
		nom:null,
	    prenom:null,
	    pseudo:null,
		mail:null,
		adresse:null,
	    cp:null,
	    phone:null,
	    mdp:null,
	    cmdp:null,
	    reg_cp: /^[0-9]{5}$/,
	    reg_phone: /^[0-9]{10}$/,
	    reg_mail: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/
	},
	methods:{
		checkFormInscription:function(e) { //Check Formulaire d'inscription
			console.log(e);
			this.error= null;
			
			if(this.nom && this.prenom && 
					this.mail && this.adresse && 
					this.cp && this.phone && this.mdp && this.cmdp && 
					this.isEmailValid == 'is-valid' && this.isCPValid == 'is-valid' &&
					this.isPHONEValid == 'is-valid' && this.match == 'is-valid') return true;
			this.error="yes";
			e.preventDefault();
		},
	},
	computed: {
		isEmailValid: function() { //Verifie la syntaxe d'une @ mail
	    	return (this.mail == "" || this.mail == null)? "" : (this.reg_mail.test(this.mail)) ? 'is-valid' : 'is-invalid';
	    },
	    
	    isCPValid: function() { //Verifie la syntaxe d'un code postal
	    	return (this.cp == "" || this.cp == null)? "" : (this.reg_cp.test(this.cp)) ? 'is-valid' : 'is-invalid';
	    },
	    isPHONEValid: function() {
	    	return (this.phone == "" || this.phone == null)? "" : (this.reg_phone.test(this.phone)) ? 'is-valid' : 'is-invalid';
	    },
	    match: function() { //Vérifie si le mot de passe à bien été rentré
	    	return (this.cmdp == "" || this.cmdp == null || this.mdp == "" || this.mdp == null)? "" : (this.cmdp == this.mdp)? 'is-valid' : 'is-invalid';
		}
	}
})
