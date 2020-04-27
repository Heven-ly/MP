<template>
	<div>
		<div class="col-sm-6" >
			<b-alert show variant="success" v-if="connected==='found'">
                Connexion Réussie ! Bienvenue {{member.prenom}} {{member.nom}}
            </b-alert>
			<b-alert show variant="danger" v-if="connected==='notfound'">
				Impossible de se connecter avec cet identifiant et ce mot de passe!
            </b-alert>

			<form v-if="connected!='found'" id="login" @submit="checkFormConnexion" method="post">
			
				<div class="row">
				
					<div class="form-group col">

						<input v-if="loginType === true " v-model="mail" :class="['form-control', isEmailValid]" placeholder='E-mail' name='mail' maxlength= '350' key="mail_in" />
						<input v-else v-model="pseudo" class="form-control" placeholder='Pseudo' name='pseudo' maxlength= '350' key="pseudo_in" />	
						<div class="invalid-feedback">
							e-mail invalide.
						</div>			
						<p v-if="mail === null && error && loginType === true">
							Il faut un E-mail
						</p>
						<p v-if="pseudo === null && error && loginType === false">
							Il faut un Pseudo
						</p>

					</div>
					
					<div class="form-group col">
						<button type="button" class="btn btn-outline-primary" @click="changelogtype">Switch</button>
					</div>
					
				</div>

				<div class="row">
					<div class="form-group col">
						<input type="password" v-model="mdp" class="form-control" placeholder='Mot De Passe' name= 'mdp' />
						<p v-if="mdp === null && error">
							Il faut un mot de passe
						</p>
					</div>
				</div>
				<div class="row">
					<div class="form-group col">
						<input type="submit" class="btn btn-outline-success" value="Connexion" name="Valider" />
					</div>
					<div class="form-group col">
						<a href="Inscription"> Pas encore inscrit?</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</template>

<script>
import { EventBus } from '../main';
export default {
	name:"LoginForm",
	data (){
		return{
			member:{
				id:Number,
				nom:String,
				prenom:String,
				pseudo:String,
				mail:String,
				adresse:String,
				cp:String,
				phone:String,
				mdp:String,
			},
			connected:null,
			error: null,
			mail:"",
			mdp:"",
			pseudo:"",
			loginType: true,
			reg: /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,24}))$/
		}
	},
	methods:{
		checkFormConnexion:function(e) { //Check Formulaire de connexion
			this.error = null;
			this.connected = null;
			e.preventDefault();
			if(this.mail == '') this.mail=null;
			if(this.pseudo == '') this.pseudo=null;
			if(this.mdp == '') this.mdp=null;
			if(((this.mail && this.loginType == true && this.isEmailValid == 'is-valid' ) || (this.pseudo && this.loginType == false)) && this.mdp){
				this.$http.post('http://localhost:8081/Connexion',{
                        pseudo: this.pseudo,
                        mail: this.mail,
                        mdp: this.mdp,
                    }).then((response) => {
						if(response.data != null && response.data != ""){
							localStorage.setItem("user", JSON.stringify(response.data))
							this.member = JSON.parse(localStorage.getItem("user"))
							localStorage.setItem("userID",this.member.id)

							console.log('success Reponse du WS: ', response.data)
							this.connected = 'found'
							EventBus.$emit('logevent')
						}else{
							console.log('NotFound Reponse du WS:', response.data)
							this.connected = 'notfound'
						}
                    }, (response) => {
                        console.log('error', response)
                });
			}else{
				this.error= 'yes';
			}
			
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
}
</script>

<style scoped>
form{
	margin-left: 10%;
	margin-top: 10%;
}
</style>
