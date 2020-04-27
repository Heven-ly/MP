<template>
    <div>
        <table v-if="voyages[0]" class="table table-striped table-sm-8 " >
            <thead class="thead-dark"><tr>
                <th>Date</th>
                <th>Depart</th>
                <th>Arriver</th>
                <th>Prix</th>
                <th>Place Acheter</th>
                <th></th>
                
            </tr></thead>
            <tbody>
                <tr v-for="(voyage,index) in voyages" :key="index">
                    <th>
                        {{voyage.date_depart}}  {{voyage.heure_depart}}
                    </th>
                    <th>
                        {{voyage.ville_depart}}
                    </th>
                    <th>
                        {{voyage.ville_arrive}}
                    </th>
                    <th>
                        {{voyage.prix}}€
                    </th>
                    <th class="row" >
                        <div class="row">
                            <div class="form-group col">
                                <button @click="incrementer(voyage)" type="button" class="btn btn-outline-dark">
                                    +
                                </button>
                            </div>
                            <div class="form-group col">
                                <p>
                                    {{voyplace[voyage.id]}}
                                </p>
                            </div>
                            <div class="form-group col">
                                <button @click="decrementer(voyage)" type="button" class="btn btn-outline-dark">
                                    -
                                </button>
                            </div>    
                        </div>
                    </th>
                    <th>
                        <div class="row">
                            <div class="form-group col">
                                <a :href="url(voyage.id -1 )" class="btn btn-info">Détails</a>
                            </div>
                            <div class="form-group col">
                                <button @click="destroy(index)" class="btn btn-danger">Supprimer</button>
                            </div>
                        </div>
                    </th>
                </tr>
                <tr>
                    <th>
                        Total : {{total}}€
                    </th>
                </tr>
            </tbody>
            <a href="Paiement" type="button" class="btn btn-outline-primary">Valider mon panier</a>
        </table>
        <p v-else>Vous n'avez pas encore choisi de voyages</p>
    </div>
</template>

<script>
import { EventBus } from '../main.js';

export default {
    props:['voyages','voyplace'],
    data (){
        return {
            total: 0,
        }
    },
    methods: {
        url (myid){
            return "/Voyager?id=" + myid
        },
        incrementer (v){
            if(this.voyplace[v.id] < v.place){
                EventBus.$emit('addproduct', v)
                this.total += v.prix
            }
        },
        decrementer (v){
            if(this.voyplace[v.id] > 1){
                EventBus.$emit('decnbproduct', v.id)
                this.total -= v.prix
            }
        },
        destroy(index){
            this.total -= this.voyages[index].prix
            EventBus.$emit('deleteproduct', index)
        },
        calctoto(){
            if(this.voyages[0]){ 
                this.total = 0
                var tempvoyages= JSON.parse(localStorage.getItem("panier"))
                tempvoyages.forEach(voyage => {
                    this.total += voyage.prix * this.voyplace[voyage.id]
                });
            }
        }
    },
    mounted (){
        this.calctoto()
    }
}
</script>