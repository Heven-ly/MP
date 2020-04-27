new Vue({
	el: '#login',
	data: {
		error: null,
		mail:null,
	    mdp:null,
	    pseudo:null,
	    loginType: true,
	    reg: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/
	},
	methods:{
		checkFormConnexion:function(e) { //Check Formulaire de connexion
			console.log('checkFormConnexion used');
			this.error = null;
	    	if(((this.mail && this.loginType == true && this.isEmailValid == 'is-valid' ) || (this.pseudo && this.loginType == false)) && this.mdp) return true;
	    	if(this.mail == '') this.mail=null;
	    	if(this.pseudo == '') this.pseudo=null;
	    	if(this.mdp == '') this.mdp=null;
	    	this.error= 'yes';
	    	e.preventDefault();
		},
	    changelogtype:function() { //gère l'etat du bouton switcher dans la page de connexion
	    	console.log('changelogType used');
	    	if (this.loginType == true){
	    		this.loginType = false;
	    	}else{
	    		this.loginType = true;
	    	}
	    },
	    isNotEmpty: function(fvar) {
	    	if(fvar == null || fvar == '') return 'is-invalid';
	    	return '';
	    }
	},
	computed: {
		isEmailValid: function() { //Verifie la syntaxe d'une @ mail
	    	return (this.mail == "" || this.mail == null)? "" : (this.reg.test(this.mail)) ? 'is-valid' : 'is-invalid';
	    }
	}
})