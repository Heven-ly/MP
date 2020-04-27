<template>
  <div>
    <img v-if="idconducteur != null && success!=true" src="../assets/Final_LogoMP.png" alt="Logo's Place">
        <b-alert show variant="success" v-if="success===true">Proposition Réussie !
            Pour faire un achat
            <router-link to= "/Voyager" >
                Cliquez ici
            </router-link>
        </b-alert>
        <b-alert show variant="danger" v-if="success===false">
            Une erreur coté server est survenue. Veuillez réessayer plus tard!
        </b-alert>
        <div v-if="idconducteur != null && success!=true" class="col-sm-6" >
            <form modelAttribute="member" id="sub" @submit="checkFormVoyage" method="post">	
                <div v-if="error" class="row" >
                    <div class="alert alert-danger col" role="alert">
                        Remplissez correctement le formulaire!
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <select v-model="ville_depart" class="custom-select col">
                            <option selected disabled>Ville de départ...</option>
                            <option v-for="ville in villes" :key="ville">
                                {{ ville }}
                            </option>
                        </select>
                    </div>
                    <div class="form-group col">
                        <select v-model="ville_arrive" class="custom-select col" >
                            <option selected disabled>Ville de d'arrivé...</option>
                            <option v-for="ville in villes" :key="ville">
                                {{ ville }}
                            </option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <textarea v-model="lieu_arrive" class="form-control" placeholder='Lieu de départ'></textarea>
                    </div>
                    <div class="form-group col">
                        <textarea v-model="lieu_depart" class="form-control" placeholder='Lieu de de dépot'></textarea>
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <div class="input-group">
                            <input v-model.number="prix" type="text" class="form-control" placeholder='Prix'>
                                <div class="input-group-append ">
                                    <span class="input-group-text">€</span>
                                </div>
                        </div>
                    </div>
                    <div class="form-group col">
                        <select v-model="place" class="custom-select col" >
                            <option selected disabled>Place Disponible</option>
                            <option v-for="i in [1,2,3,4,5]" :key="i">
                                {{ i }}
                            </option>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <label for="dep"> Départ le : </label>
                    </div>
                    <div class="form-group col">
                        <label for="arr"> Arriver le : </label>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <input v-model="date_depart" type='text' id="dep" class="form-control" placeholder='mm/jj/aaaa' />
                    </div>
                    <div class="form-group col">
                        <input v-model="date_arrive" type='text' id="arr" class="form-control" placeholder='mm/jj/aaaa' />
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col">
                        <input v-model="heure_depart" type='text' class="form-control" placeholder='hh:mm' />
                    </div>
                    <div class="form-group col">
                        <input v-model="heure_arrive" type='text' class="form-control" placeholder='hh:mm' />
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <label>Fumer</label>
                        <input v-model="fumer" type="checkbox" name="" id="smoke">
                    </div>
                    <div class="form-group col">
                        <label>Téléphone</label>
                        <input v-model="phone" type="checkbox" name="" id="smoke">
                    </div>
                    <div class="form-group col">
                        <label>Mail</label>
                        <input v-model="mail" type="checkbox" name="" id="smoke">
                    </div>
                    <div class="form-group col">
                        <label>Baguage</label>
                        <input v-model="bagage" type="checkbox" name="" id="smoke">
                    </div>
                </div>
                <div class="row" >
                    <div class="form-group col">
                        <input type="submit" class="btn btn-outline-success" value="Proposer mon Voyage" name="Valider">
                    </div>
                </div>
            </form>
        </div>
        <div v-else-if="idconducteur === null" class="col-sm-6" >
            <div class="alert alert-danger col" role="alert">
                Vous devez être inscrit pour proposer un voyage.
            </div>
        </div>
  </div>
</template>

<script>
export default {
	name:"LoginForm",
	data (){
		return{
            ville_depart: null,
            ville_arrive: null,
            lieu_depart: null,
            lieu_arrive: null,
            prix: null,
            place: null,
            date_depart: null,
            heure_depart: null,
            date_arrive: null,
            heure_arrive: null,
            options: 0,
            success:null,
            idconducteur: localStorage.getItem("userID"),
            fumer: false, //symbolise dans la représentation binaire pour les options : 8
            phone: false, //symbolise : 4
            mail:false, //symbolise : 2
            bagage:false, // symbolise : 1
            error: null,
            villes: ["Paris","Berlin","Lyon","Bruxelle","Geneve","Madrid","Bretagne","Toulouse"],

        }
	},
	methods:{
		checkFormVoyage:function(e) { //Check Formulaire de connexion
            this.error = null;
            this.success = null;
            if(this.EveryThingIsOk()){
                this.options=0
                if(this.fumer == true) this.options+=8
                if(this.phone == true) this.options+=4
                if(this.mail == true) this.options+=2
                if(this.bagage == true) this.options+=1
                this.$http.post('http://localhost:8081/Inscription/Voyage',{
                        ville_depart: this.ville_depart,
                        ville_arrive: this.ville_arrive,
                        lieu_depart: this.lieu_depart,
                        lieu_arrive: this.lieu_arrive,
                        prix: Number(this.prix),
                        place: Number(this.place),
                        date_depart: this.date_depart,
                        heure_depart: this.heure_depart,
                        date_arrive: this.date_arrive,
                        heure_arrive: this.heure_arrive,
                        options: this.options,
                        idconducteur: this.idconducteur
                    }).then((response) => {
                        if(response.bodyText != null && response.bodyText != ''){
                            console.log('success, reponse du WS:', response.bodyText)
                            this.success=true
                        }else{
                            console.log('Probleme server, reponse par defaut du WS:', response.bodyText)
                            this.success=false
                        }
                    }, (response) => {
                        console.log('error', response)
                    });
            }else{
                this.error= 'yes';
            } 
			
			e.preventDefault();
        },
        EveryThingIsOk: function(){
            if(this.idconducteur != localStorage.getItem("userID")){
                this.idconducteur = localStorage.getItem("userID")
                return false
            }
            if(!this.ville_depart || !this.place || !this.ville_arrive || !this.lieu_depart || !this.lieu_arrive) return false
            if(!this.prix || this.prix == '' || isNaN(Number(this.prix))) return false
            if(new Date(this.date_depart + " " + this.heure_depart) == 'Invalid Date' || !this.date_depart || !this.heure_depart) return false
            if(new Date(this.date_arrive + " " + this.heure_arrive) == 'Invalid Date' || !this.date_arrive || !this.heure_arrive) return false
            return true
        },
		isNotEmpty: function(fvar) {
			if(fvar == null || fvar == '') return 'is-invalid';
			return '';
        },
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
div img{
	float : right;
	margin-right: 15%;
}
</style>
