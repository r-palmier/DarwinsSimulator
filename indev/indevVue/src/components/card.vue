<script setup>

    let beginUurl = "https://t3.gstatic.com/faviconV2?client=SOCIAL&type=FAVICON&fallback_opts=TYPE,SIZE,URL&url=";
    let endUrl = "&size=256";
    

    const props = defineProps({
        siteTitle: String,
        link: String,
        description: String,
        tags: Array
    })

</script>

<template>
    <div class="card">
        <div class="imgContainer">
            <img src="../../ressources/Notion-logo.svg.png" alt="">
        </div>
        <div class="infoContainer">
            <p class="siteTitle">{{ siteTitle }}</p>
            <a v-bind:href="props.link" class="underline">{{ link }}</a>
            <p class="description">{{ description }}</p>
                <span v-for="tag in tags">
                    <span v-for="object in tag">
                        <p class="tag">
                            {{ object.name }}
                        </p>
                    </span>

                </span>
        </div>
      
    </div>

</template>

<style scoped>

    @import url('https://fonts.googleapis.com/css2?family=Berkshire+Swash&family=League+Gothic&family=Lobster&family=Prompt:wght@300&family=Roboto:wght@100&display=swap');
    /* @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap'); */

    *{
    /* font-family: 'Poppins', sans-serif; */
    }


    body{
    margin: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    }

    a{
    text-decoration: none;
    color: #0074D9;
    }

    .underline{
    position: relative;
    }

    .underline::before{
    content: '';
    position: absolute;
    bottom: 0;
    right: 0;
    width: 0;
    height: 2px;
    background-color: #0074D9;
    transition: width 0.6s cubic-bezier(0.25, 1, 0.5, 1);
    }

    @media (hover: hover) and (pointer: fine) {
        .underline:hover::before{
            left: 0;
            right: auto;
            width: 100%;
        }
    }
   .card{
    display: flex;
    flex-direction: row;
    align-items: center;
    text-indent: 0.1em;
    background-color: #36393f ;
    width: 22em;
    height: 9em;
    margin: 0.5em;
    border-radius: 10px;
    overflow:hidden

    }
    .card .siteTitle {
        font-weight: 800;
        /* margin: 1em; */
    }
    .card p {
        margin: 0.1em;
    }
    .card .description {
        font-weight: 100;
    }
    .card img {
        width: 5em;
        height: 5em;
    }
    .imgContainer {
        display: flex;
        align-items: center;
        margin: 1em;
    }
    .tag {
        background-color: #292b2f;
        border-radius: 5px;
        padding: 0.2em;
        margin: 0.1em;
        display: inline;
    }

</style>