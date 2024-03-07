<script setup>

const url = import.meta.env.VITE_URL_DISCORD;
console.log(url);

</script>

<template>

    <div class="flex items-center justify-center h-screen bg-discord-gray text-white">
        <a id="login"
            :href="url"
            class="bg-discord-blue  text-xl px-5 py-3 rounded-md font-bold flex items-center space-x-4 hover:bg-gray-600 transition duration-75">
            <i class="fa-brands fa-discord text-2xl"></i>
            <span>Login with Discord</span> 
        </a>
    </div>

    

</template>

<style>
    html{
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20%;
}


.btn-grad {
            background-image: linear-gradient(to right, #4776E6 0%, #8E54E9  51%, #4776E6  100%);
            margin: 10px;
            padding: 15px 45px;
            text-align: center;
            text-transform: uppercase;
            transition: 0.5s;
            background-size: 200% auto;
            color: white;
            box-shadow: 0 0 20px #eee;
            border-radius: 10px;
            display: block;
          }

          .btn-grad:hover {
            background-position: right center; /* change the direction of the change here */
            color: #fff;
            text-decoration: none;
          }
</style>