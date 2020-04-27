<template>
  <div>
        <img v-if="success===null" src="../assets/Final_LogoMP.png" alt="Logo's Place">
        <div class="col-sm-6" >
            <form modelAttribute="member" id="sub" @submit="checkFormInscription" method="post" v-if="success===null">	
                <div v-if="error" class="row" >
                    <div class="alert alert-danger col" role="alert">
                        {{message}}
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input v-model="nom" type="text" class="form-control" placeholder='Nom' name='nom' maxlength= '350' />
                    </div>
                    <div class="form-group col">	
                        <input v-model="prenom" type="text" class="form-control" placeholder='Prenom' name='prenom' maxlength= '350' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input v-model="pseudo" type="text" class="form-control" placeholder='Pseudo' name='pseudo' maxlength= '350' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input v-model="mail" :class="['form-control', isEmailValid]" placeholder='E-mail' name='mail' maxlength= '350' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input v-model="adresse" type="text" class="form-control" placeholder='Adresse' name='adresse' maxlength= '350' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input v-model="cp" :class="['form-control', isCPValid]" placeholder='Code Postal' name='cp' maxlength= '5' />
                    </div>
                    <div class="form-group col">
                        <input v-model="phone" :class="['form-control', isPHONEValid]" placeholder='Telephone' name='phone' maxlength= '10' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input v-model="mdp" type="password" class="form-control" placeholder='Mot De Passe' name= 'mdp' />
                    </div>
                    <div class="form-group col">
                        <input v-model="cmdp" type="password" :class="['form-control', match]" placeholder='Confirmer Mot de Passe' name= 'cmdp' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input type="submit" class="btn btn-outline-success" value="Creer mon Compte" name="Valider">
                    </div>
                </div>
            </form>
            <b-alert show variant="success" v-if="success===true">Inscription Réussie !
                Pour vous connecter
                <router-link to= "/LogIn" >
                    Cliquez ici
                </router-link>
            </b-alert>
            <b-alert show variant="warning" v-if="success===false">Un problème sur nos server empêche l'inscription !
                Veuillez réessayer plus tard!
            </b-alert>
        </div>
  </div>
</template>

<script>
export default {
	name:"SubForm",
	data (){
		return{
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
            success: null,
            message:null,
            
			reg_mail: /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/,
			reg_cp: /^[0-9]{5}$/,
			reg_phone: /^[0-9]{10}$/
		}
	},
	methods:{
		checkFormInscription:function(e) { //Check Formulaire d'inscription
            this.error= null
            this.message=""
			if(this.nom && this.prenom && 
                this.mail && this.adresse && 
                this.cp && this.phone && this.mdp && this.cmdp && 
                this.isEmailValid == 'is-valid' && this.isCPValid == 'is-valid' &&
                this.isPHONEValid == 'is-valid' && this.match == 'is-valid'){
                
                this.$http.post('http://localhost:8081/isMemberExist',this.mail).then((response) => {
                    if(response.data===true){
                        this.message="Cette e-mail appartient déjà à quelqu'un";
                        this.error="yes";
                    }else{
                        this.$http.post('http://localhost:8081/Inscription/Member',{
                            nom: this.nom,
                            prenom: this.prenom,
                            pseudo: this.pseudo,
                            mail: this.mail,
                            adresse: this.adresse,
                            cp: this.cp,
                            phone: this.phone,
                            mdp: this.mdp,
                            cmdp: this.cmdp
                        }).then((response) => {
                            if(response.bodyText === 'Success'){
                                console.log('success, reponse du WS:', response.bodyText)
                                this.success=true
                            }else{
                                console.log('Probleme server, reponse par defaut du WS:', response.bodyText)
                                this.success=false
                            }
                            return true
                        }, (response) => {
                            console.log('error', response.data)
                        });
                    }
                }, (response) => {
                    console.log('error', response.data)
                    this.message="Cette e-mail appartient déjà à quelqu'un";
                    this.error="yes";
                });
            
            }else{
                this.error="yes";
                this.message="Veuillez remplire correctement le formulaire"
            }
            e.preventDefault();
        },
        exist: function(){
            
            
            
        }
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
        },
        
	}
}
</script>

<style scoped>
div img{
	float : right;
	margin-right: 15%;
}
form{
	margin-left: 10%;
	margin-top: 10%;
}
</style>
