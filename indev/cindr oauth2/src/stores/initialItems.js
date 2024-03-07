import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useInitialItems = defineStore('initialItems', () => {
  const items = ref([])
  async function getInitialItems() {
    const url = (
      'http://localhost:8080/getItemsPage?page=0&size=10&sort=id'
    );
    const result = await fetch(url)
      .then(response => response.json())
      .catch(err => console.log(err));

    console.log('Fetched from: ' + url);
    items.value = result.content;
    console.log('result',result);
    console.log('ITEMS', items.value);
  }

  return { items, getInitialItems }
})
