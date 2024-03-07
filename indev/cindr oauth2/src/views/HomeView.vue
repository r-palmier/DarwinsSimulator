<script setup>
import card from '../components/card.vue';
import { ref, onMounted, watch } from 'vue';
import { useInitialItems } from "../stores/initialItems";
const siteTitle = ref('Titre');
const initialItems = useInitialItems();
let items = ref([]);

onMounted(async () => {
  await initialItems.getInitialItems();
  items = initialItems.items
  console.log('SSSS',items)
  console.log("tags : " + items.tags);

})


</script>

<template>
      <header>
    <nav class="topnav">
      <button class="btnMenu" @click="$router.push({ name: 'home' })">Home</button>
      <button class="btnMenu" @click="$router.push({ name: 'contact' })">Contact</button>
      <button class="btnMenu" @click="$router.push({ name: 'about' })">About</button>
      <button class="btnMenu" @click="$router.push({ name: 'admin' })">Admin</button>
      <button @click="fetchTest()">fetchTest</button>
      <button id="btnDeco" class="btnMenu">Déconnexion</button>

    </nav>
  </header>
<div class="home">
  <div class="wall">
    <!-- QQQQ {{ initialItems.items[0] }} -->
    <button @click="initialItems.getInitialItems">getItems</button>
    <div v-for="item in initialItems.items">
      <card :siteTitle= item.name :tags=[item.tags] :description=item.description></card>
      
    </div>
    <card :siteTitle= siteTitle />
    <card></card>
    <card></card>
    <card></card>
    <card></card>
    <card></card>
    <card></card>
    <card></card>
    <card></card>
    <card></card>
    
  </div>
</div>
 
</template>
