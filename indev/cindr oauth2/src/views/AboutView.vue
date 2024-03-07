<script setup lang="js">

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

  <div class="fullscreen">
    <h1 class="text">
    In'Dev est un Projet IT S4 visant à classer et mettre à l'accès de tous les élèves du ESIEA GROUPE, les ressources partager par les éléves sur le Discord de L'ecole.
    </h1>
  </div>

    <!-- Pied de page -->
    <footer>
    <p>
      Copyright &copy; In'dev 2022-2023 - All right Reserved
    </p>
  </footer>

</template>
