<template>
  <div>
    <div v-if="membre != null && Params () && Params2() && voyages[myid]" class="card center">
        <div class="card center">
            <div class="card-body">
                <div class="row">
                    <div class="form-group col">
                        <h5 class="card-title">{{voyages[myid].date_depart}}  {{voyages[myid].heure_depart}}</h5>
                    </div>
                    <div class="form-group col">
                        <h5 class="card-text" align="right">{{voyages[myid].prix}} €</h5>
                    </div>
                </div>
                <h4 class="card-title">De : {{voyages[myid].ville_depart}}</h4>
                <p class="card-text">{{voyages[myid].lieu_depart}}</p>
                <h4 class="card-title">A : {{voyages[myid].ville_arrive}}</h4>
                <p class="card-text">{{voyages[myid].lieu_arrive}}</p>
                
                <div v-if="(voyages[myid].options).toString(2)[0] === '1' " class="group col">
                    <p class="card-text">Fumer</p>
                </div>
                
                <div v-if="(voyages[myid].options).toString(2)[1] === '1' " class="group col">
                    <p class="card-text"> Telephone</p>
                </div>
                
                <div v-if="(voyages[myid].options).toString(2)[2] === '1' " class="group col">
                    <p class="card-text" >Mail</p>
                </div>

                <div v-if="(voyages[myid].options).toString(2)[3] === '1' " class="group col">
                    <p class="card-text" >Baguage</p>
                </div>
                <div v-if="voyages[myid].place > 0" class="group col">
                    <p class="card-text" >{{voyages[myid].place}} place restante</p>
                </div>
            </div>
        </div>
    </div>
    <div v-else-if="membre != null && voyages[0] && Params()" class="card-columns">
        <div class="card" v-for="(voyage,index) in voyages" :key="index">
            <div class="card-header text-center ">
                {{voyage.date_depart}}  {{voyage.heure_depart}}
            </div>
            <div class="card-body text-center">
                <p class="card-text">{{voyage.ville_depart}}  >  {{voyage.ville_arrive}}</p>
                <p v-if="voyage.lieu_depart.length < 15">Depart : {{voyage.lieu_depart}}</p>
                <p v-else>Depart : {{voyage.lieu_depart.substr(0,15)}}... </p>
                <p v-if="voyage.lieu_arrive.length < 15">Arriver : {{voyage.lieu_arrive}}</p>
                <p v-else>Arriver : {{voyage.lieu_arrive.substr(0,15)}}...</p>
                <p>{{voyage.prix}}€</p>
            </div>
            <div class="card-footer text-center">
                <div class="row" v-if="voyage.place != 0">
                    <div class="group col">
                        <a :href="url(index)" class="btn btn-outline-info">Détails</a>
                    </div>
                </div>
                <p v-else style="color: green;">Complet</p>
            </div>
        </div>
    </div>
    <div v-else-if="membre != null && Params ()">
        <h5 style="text-align: center;"> 
            
            Désolé, aucun voyage n'a été trouvé
        </h5>
    </div>
    <div v-else-if="membre != null" class="card-columns mx-auto text-center">
        <div class="card bg-light">
            <div class="card-header">
                {{membre.prenom}} {{membre.nom}} n° {{membre.id}}
            </div>
            <div class="card-body">
                <p>{{membre.pseudo}}</p>
                <p class="card-text">{{membre.adresse}}, {{membre.cp}}</p>
                <p >Tel : {{membre.phone}} </p>
                <p >Mail : {{membre.mail}} </p>
            </div>
            <div class="card-footer">
              <div >
                <a href="/Account?val=1">Mes Propositions</a>
              </div>
              <div >
                <a href="/Account?val=2">Mes Billets</a>
              </div>
            </div>
        </div>
    </div>
    <div v-else>
      <p>Pas de Compte</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Account',
  data (){
    return {
      membre:null,
      val: this.$route.query.val,
      voyages: [],
      myid: this.$route.query.id,
    }
  },
  methods: {
    proposition (){
      this.$http.post('http://localhost:8081/Proposition',this.membre.id).then((response) => {
          if(response.data != null && response.data != ""){
              this.voyages= response.data
              console.log('Success, Reponse du Web Service', response.data)
          }else{
              console.log('NotFound', response)
          }
      }, (response) => {
          console.log('error', response)
          
      })
    },
    historique (){
      this.$http.post('http://localhost:8081/Historique',this.membre.id).then((response) => {
          if(response.data != null && response.data != ""){
              this.voyages = response.data
              console.log('Success, Reponse du Web Service', response.data)
          }else{
              console.log('NotFound', response)
              
          }
      }, (response) => {
          console.log('error', response)
          
      })
    },
    Params (){
        return this.$route.query.val ? true:false
    },
    Params2 (){
      return this.$route.query.id ? true:false
    },
    url (myid){
        return "/Account?val=" +this.val+ "&id=" + myid
    },
  },
  mounted (){
    this.membre = (JSON.parse(localStorage.getItem("user")))
    if(this.$route.query.val == '1'){
      this.proposition()
    }else if (this.$route.query.val == '2'){
      this.historique()
    }
    
  }
}
</script>