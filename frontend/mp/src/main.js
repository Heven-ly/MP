import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import VueResource from 'vue-resource'
import './plugins/bootstrap-vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Acceuil from './views/Acceuil.vue'
import Login from './views/Login.vue'
import Sub from './views/Sub.vue'
import PropVoyage from './views/PropVoyage.vue'
import Voyager from './views/Voyager.vue'
import Panier from './views/Panier.vue'
import Paiement from './views/Paiement.vue'
import Account from './views/Account.vue'

Vue.config.productionTip = false

export const EventBus = new Vue()

Vue.use(VueResource)

Vue.use(VueRouter)

const routes= [
  {
    path: '/',
    name: 'Acceuil',
    component: Acceuil
  },
  {
    path: '/LogIn',
    name: 'Login',
    component: Login
  },
  {
    path: '/Inscription',
    name: 'Sub',
    component: Sub
  },
  {
    path: '/Proposer',
    name: 'PropVoyage',
    component: PropVoyage
  },
  {
    path: '/Voyager',
    name: 'Voyager',
    component: Voyager
  },
  {
    path: '/Panier',
    name: 'Panier',
    component: Panier,
  },
  {
    path: '/Paiement',
    name: 'Paiement',
    component: Paiement,
  },
  {
    path: '/Account',
    name: 'Account',
    component: Account,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')




