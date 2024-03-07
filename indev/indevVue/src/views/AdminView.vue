<script setup lang="js">

</script>

<template>
    <header>
    <nav class="topnav">
      <button class="btnMenu" @click="$router.push({ name: 'home' })">Home</button>
      <button class="btnMenu" @click="$router.push({ name: 'contact' })">Contact</button>
      <button class="btnMenu" @click="$router.push({ name: 'about' })">About</button>
      <button class="btnMenu" @click="$router.push({ name: 'admin' })">Admin</button>
      <button id="btnDeco" class="btnMenu">Déconnexion</button>

    </nav>
  </header>

<div class="Admin">
    <h1>This is an admin page</h1>
  </div>

    <!-- Pied de page -->
    <footer>
    <p>
      Copyright &copy; In'dev 2022-2023 - All right Reserved
    </p>
  </footer>
  
</template>
