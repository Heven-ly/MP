<template>
    <div>
        <div v-if="!connected" class="alert alert-warning col" role="alert">
          Vous devez avoir un compte pour proceder a un achat
        </div>
        <div v-if="Params() && voyages[myid]" class="card center">
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
                        <p class="card-text"> Telephone : {{phone}}</p>
                    </div>
                    
                    <div v-if="(voyages[myid].options).toString(2)[2] === '1' " class="group col">
                        <p class="card-text" >Mail: {{mail}}</p>
                    </div>

                    <div v-if="(voyages[myid].options).toString(2)[3] === '1' " class="group col">
                        <p class="card-text" >Baguage autorisé</p>
                    </div>
                    <div v-if="voyages[myid].place > 0" class="group col">
                        <p class="card-text" >{{voyages[myid].place}} place restante</p>
                    </div>
                     
                    <button v-if="voyages[myid].place != 0 && connected" @click="addproduct(myid)" class="btn btn-outline-success">Ajouter</button>
                </div>
            </div>
        </div>
        <div v-else-if="voyages[0]" class="card-columns">
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
                            <button v-if="connected" @click="addproduct(index)" class="btn btn-outline-success">Ajouter</button>
                        </div>
                        <div class="group col">
                            <a :href="url(index)" class="btn btn-outline-info">Détails</a>
                        </div>
                    </div>
                    <p v-else style="color: green;">Complet</p>
                </div>
            </div>
        </div>
        <div v-else>
            <h5 style="text-align: center;"> 
                <div v-if="message === 'Chargement des voyages...Veuillez patienter'" class="spinner-border text-primary" role="status">
                    <span class="sr-only">Loading...</span>
                </div>
                {{message}} 
            </h5>
        </div>
    </div>
</template>

<script>

import { EventBus } from '../main.js';

export default {
    data (){
		return{
            voyages: [],
            myid: this.$route.query.id,
            message: null,
            phone: "Indisponible pour le moment",
            mail:"Indisponible pour le moment",
            connected: null,
        }
    },
    methods: {
        url (myid){
            return "/Voyager?id=" + myid
        },
        Params (){
            return this.$route.query.id ? true:false
        },
        addproduct (idproduct){
            EventBus.$emit('addproduct', this.voyages[idproduct])
        },
        getPhoneNumber (){
            console.log('idconducteur', this.voyages[this.myid].idconducteur)
            this.$http.post('http://localhost:8081/getPhoneNumber/',this.voyages[this.myid].idconducteur).then((response) => {
                this.phone= response.bodyText
            }, (response) => {
                console.log('error', response)
                this.message="Un probleme est survenue. Veuillez nous contacter pour plus d'information"
            })
        },
        getMail (){
            this.$http.post('http://localhost:8081/getMail/',this.voyages[this.myid].idconducteur).then((response) => {
                this.mail= response.bodyText
            }, (response) => {
                console.log('error', response)
                this.message="Un probleme est survenue. Veuillez nous contacter pour plus d'information"
            })
        }
    },
    mounted() {
        this.message="Chargement des voyages...Veuillez patienter"
        this.connected= localStorage.getItem('user')? true:false
        this.$http.get('http://localhost:8081/Voyages').then((response) => {
            if(response.data != null && response.data != ""){
                this.voyages = response.data
                console.log('From Backend : ', response.data)
                if(this.voyages[this.myid]){
                    this.getPhoneNumber()
                    this.getMail()
                }
            }else{
                console.log('NotFound', response)
                this.message="Il n'y a pas de voyage disponible pour le moment"
            }
        }, (response) => {
            console.log('error', response)
            this.message="Un probleme est survenue. Il n'est pas possible de visionner les voyages pour l'instant. Veuillez réessayer plus tard."
        })
        
    }
}
</script>