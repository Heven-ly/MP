<template>
  <div>

    <h2 align="center" >🅜🅐🅡🅢🅤🅟🅘🅐🅛 🅟🅞🅒🅚🅔🅣</h2>
      
    <div class="col-11 mx-auto">
      <ul class="nav nav-tabs">
        <li class="nav-items">
          <router-link class="nav-link" to= "/" >
              Accueil
          </router-link>
        </li>
        <li class="nav-items">
          <router-link class="nav-link" to= "Voyager" >
              Voyager
          </router-link>
        </li>
        <li class="nav-items">
          <router-link class="nav-link" to= "Proposer" >
              Proposer
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" to= "Panier">
              Panier 
          </router-link>
        </li>
        <li  class="nav-item ml-auto">
          <router-link v-if="sessionstate === 'Se Connecter'" class="nav-link" to="LogIn">
            {{sessionstate}}
          </router-link>
          <router-link v-else class="nav-link" to="Account">
              {{sessionstate}}
          </router-link>
          
        </li>
        <li v-if="sessionstate === 'Mon Compte'" class="nav-item">
          <button  align="right" @click="deconnected()" class="btn btn-outline-dark">
            Deconnexion
          </button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { EventBus } from '../main.js';
export default {
  
  name:"MyHeader",
  data (){
    return {
      sessionstate: this.Sessionset()
    }
  },
  methods: {
    Sessionset (){
      return localStorage.getItem("user") != null? "Mon Compte":"Se Connecter"
    },
    deconnected (){
      this.sessionstate="Se Connecter"
      localStorage.removeItem("user")
      localStorage.removeItem("userID")
    }
  },
  mounted (){
    EventBus.$on('logevent', () => {
      this.sessionstate = "Mon Compte"
    })
  }
}
</script>

<style scoped>
    ul li a{
        color: #555555;
        font-weight: bold;
    }   
</style>
