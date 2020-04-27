<template>
  <div>
     <div v-if="error" class="row" >
          <div class="alert alert-danger col" role="alert">
              Remplissez correctement le formulaire!
          </div>
      </div>
      <form v-if="valid===false && panierexist && userexist" class="col-sm-5 mx-auto text-center" @submit="checkFormPaiement" method="put">
        <h1 class="display-4 py-2 text-white text-truncate">Paiement</h1>	
        <div class="row" >
          <div class="form-group col">
              <input v-model="numero" type="text" class="form-control" placeholder='Numero de Carte' name='numero' maxlength= '16' />
          </div>  
        </div>
        <div class="row" >
            <div class="form-group col">	
                <input v-model="date" type="text" class="form-control" placeholder='MMYY' name='expiration' maxlength= '4' />
            </div>
            <div class="form-group col">
                <input v-model="cvc" type="text" class="form-control" placeholder='CVC' name='cvc' maxlength= '3' />
            </div>
        </div>
        
        <div class="row" >
            <div class="form-group col">
                <input type="submit" class="btn btn-primary" value="Payer" name="Valider">
            </div>
        </div>
      </form>
      <div v-else-if="!panierexist && valid === true">
        <div class="alert alert-success col" role="alert">
              Félicitation pour votre achat! Pour revenir à la page d'acceuil <a href="/">Cliquer ici</a>
          </div>
      </div>
      <div v-else>
        <div class="alert alert-warning col" role="alert">
          Vous devez avoir un panier pour proceder au paiement
        </div>
      </div>
  </div>
</template>

<script>
import { EventBus } from '../main.js';

export default {
  name: 'Paiement',
  data (){
    return {
      numero: null,
      date: null,
      cvc: null,
      valid:false,
      voyages: [],
      voyplace: {},
      voyageid: [],
      places: [],
      user: [],
      panierexist: null,
      userexist: null,
      error: false,
      reg_num: /^[0-9]{16}$/,
      reg_exp: /^[0-9]{4}$/,
      reg_cvc: /^[0-9]{3}$/,
    }
  },
  methods: {
    checkFormPaiement (e){
      this.error=false
      e.preventDefault()
      if(this.reg_num.test(this.numero) && this.reg_exp.test(this.date) 
      && this.reg_cvc.test(this.cvc)){
        this.achat();
      }else{
        this.error = true
      }
    },
    achat (){
      this.voyageid=[]
      this.places=[]
      

      this.voyages = JSON.parse(localStorage.getItem("panier"))
      this.voyplace = JSON.parse(localStorage.getItem("panierplace"))
      this.voyages.forEach(voyage => {
      
        this.voyageid.push(voyage.id)
        this.places.push(this.voyplace[voyage.id])
        
      
      });
      
      this.$http.put('http://localhost:8081/Achat', {
        voyageid: this.voyageid,
        places: this.places,
        idvoyageur: this.user.id

      }).then((response) => {
          console.log('success', response.body)
          this.valid=true
          this.panierexist = false
          EventBus.$emit('clear')
      }, (response) => {
          console.log('error', response)
      });
    }
  },
  mounted (){
    this.panierexist = localStorage.getItem('panier')? true:false
    this.userexist = localStorage.getItem('user')? true:false
    if(this.userexist){
      this.user=JSON.parse(localStorage.getItem("user"))
    }
  }
}
</script>

<style scoped>

form:before {
    content: '';
    height: 100%;
    left: 0;
    position: absolute;
    top: 0;
    width: 100%;
    background-color: rgba(0,0,0,0.3);
    z-index: -1;
    border-radius: 10px;
}
</style>
