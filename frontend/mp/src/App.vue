<template>
  <div id="app">
    <div id="nav">
      <MyHeader/>
    </div>
    <router-view :paniervoyages="panier.voyages" :panierplace="panier.nbplace"/>
    <MyFooter/>
  </div>
</template>

<script>
import MyHeader from './components/MyHeader.vue'
import MyFooter from './components/MyFooter.vue'
import { EventBus } from './main.js';

class Panier{

  constructor () {
    this.voyages = [],
    this.nbplace = {}
  }

  addvoyage (p){
    if(!this.voyages.find(v => v.id === p.id)){
      this.voyages.push(p)
      this.nbplace[p.id]= 1
      localStorage.setItem("panier",JSON.stringify(this.voyages))
      localStorage.setItem("panierplace",JSON.stringify(this.nbplace))
    }else{
      if(this.nbplace[p.id] < p.place){
        this.nbplace[p.id]+= 1
        localStorage.setItem("panierplace",JSON.stringify(this.nbplace))
      }
    }
  }

  deletevoyage (index){
    this.nbplace[this.voyages[index].id]=0
    this.voyages.splice(index,1)
    localStorage.setItem("panierplace",JSON.stringify(this.nbplace))
    localStorage.setItem("panier",JSON.stringify(this.voyages))

  }

  decvoyage (pid){
    if(this.nbplace[pid] > 1){
        this.nbplace[pid]-= 1
        localStorage.setItem("panierplace",JSON.stringify(this.nbplace))
    }
  }

  clearpanier (){
    this.voyages = []
    this.nbplace = {}
    localStorage.removeItem("panier")
    localStorage.removeItem("panierplace")
  }
}

export default {
  name: 'App',
  data (){
    return{
      panier: new Panier
    }
  },
  components: {
   MyHeader,
   MyFooter
  },
  mounted (){
    if(localStorage.getItem("panier") != null && localStorage.getItem("panier") != []){
      this.panier.voyages= JSON.parse(localStorage.getItem("panier"))
      this.panier.nbplace= JSON.parse(localStorage.getItem("panierplace"))
    }
    EventBus.$on('addproduct', product => {
      this.panier.addvoyage(product)
    })
    EventBus.$on('deleteproduct', index => {
      this.panier.deletevoyage(index)
    })
    EventBus.$on('decnbproduct', vindex => {
      this.panier.decvoyage(vindex)
    })
    EventBus.$on('clear', () => {
      this.panier.clearpanier()
    })
    
  }
}
</script>

<style>
html{
  min-height: 100%;
}
body{
  
  min-height: 100%;
  height: 100%;
  font-family: TimeNewRoman,helvetica,sans-serif;
  background: #2980B9;  /* fallback for old browsers */
  background: -webkit-linear-gradient(to right, #FFFFFF, #6DD5FA, #2980B9);  /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(to right, #FFFFFF, #6DD5FA, #2980B9); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */

}
</style>
