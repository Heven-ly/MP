<template>
    <div>
        <MyPanier :voyages="paniervoyages" :voyplace="panierplace"/>
    </div>
</template>

<script>
import MyPanier from '../components/MyPanier.vue'

export default {
    name: 'Panier',
    props:['paniervoyages', 'panierplace'],
    components: {
        MyPanier
    }
}
</script>