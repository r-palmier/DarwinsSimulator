<script setup>
</script>

<template>

  <router-view></router-view>

</template>

<style>
@import './base.css';
@import url('https://fonts.googleapis.com/css2?family=Prompt:wght@300&family=Roboto:wght@300&display=swap');
*{
    font-family: 'Roboto', sans-serif;
}

body {
  background-color: #202225 ;

}

@media (hover: hover) {
  a:hover {
    background-color: hsla(160, 100%, 37%, 0.2);
  }
}

@media (min-width: 1024px) {
  body {
    margin: 0%;
    
  }

  #app {

  }
}

.admin{
	height: 100vh;
	width: 100%;

}
nav{
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding-top: 45px;
	padding-left: 8%;
	padding-right: 8%;
}
.home{
  background-color:#202225;
  display: flex;
  flex-direction: column;
  place-items: center;
  width: 100%;
  height: 100%;

}
.div{
    background-color: aquamarine;
    display: flex;
    width: 10%;
    height: 100%;
}
.cards{
    display: flex;
    background-color: #36393f ;
    width: 22em;
    height: 9em;
    margin: 0.5em;
    border-radius: 10px;


}
.wall{
    display: grid;
    grid-template-columns: 23em 23em;
    justify-items: center;
    position: absolute;
    flex-direction: row;
    flex-wrap: wrap;
    /* width: auto; */
    background-color: #292b2f ;
    justify-content: center;
    margin-top: 2%;
    border-radius: 15px;
}
nav{
    display: flex;
    flex-direction: row;
    background-color: cadetblue;
    padding: 0%;
    justify-content: center;
}
button{
    padding: 1em;
    margin: 1em;
}
p{
    color: white;
}
* {
  box-sizing: border-box;
  /*align-items: center;*/
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

header {
  background-color: #282A35;

}

.btnMenu {
  text-align: center;
  padding: 1em;
}

div#content div {
  display: inline-block;
  width: 48%;
  vertical-align: top;
  margin: 10px;
}

div#content {
  background-color: #E8E8E8;
}


.sBar {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 45%;
  vertical-align: top;

}


.searchBar {
  width: 40%;
  height: 10%;
  border: 1px black solid;
  border-radius: 25px;
  margin-top: 0.25rem;
  border-width: 2px;
  border-radius: 9999px;
  border-style: solid;

}

.btnSearch {
  padding-left: 5%;
}

#btnDeco {
position: absolute;
top: 1%;
left: 90%;
}


.topnav {
  overflow: hidden;
  background-color: cadetblue;
  padding-bottom: 0.5%;
  padding-top: 0.5%;
  padding-left: 1%;
  padding-right: 1%;
}

.topnav a {
  float: left;
  display: block;
  color: black;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #2196F3;
  color: white;
}

.topnav input[type=text] {
  float: right;
  padding: 6px;
  margin-top: 8px;
  margin-right: 16px;
  border: none;
  font-size: 17px;
}

footer {
  position: relative;
  bottom: 0%;
  background-color: hsla(50, 33%, 25%, .75);
  text-align: center;
  display: flex;
  justify-content: center;
  width: 100%;
  padding: 5px;

}
</style>