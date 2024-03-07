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
      Membres: 
    </h1>

    <h1 class="about">
      Paul DEVRED,
    </h1>

    <h1 class="about">
     Alexy Vanot,
    </h1>

    <h1 class="about">
    Robin PALMIER,
    </h1>

    <h1 class="about">
    Pierre GERVAIS.
    </h1>
  </div>

    <!-- Pied de page -->
    <footer>
    <p>
      Copyright &copy; In'dev 2022-2023 - All right Reserved
    </p>
  </footer>
  
</template>
