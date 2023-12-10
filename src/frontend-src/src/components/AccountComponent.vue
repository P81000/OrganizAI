<script setup>
import { ref } from 'vue';

const username = ref("Sample Name Jr");
const email = ref("sample@email.com")
</script>

<template>
  <div class="account">
    <header class="header"></header>
    <main class="container">
      <img class="logout" src="../assets/logout.png" alt="profile" title="Logout"/>
      <div class="picture">
        <img class="profile" src="../assets/profile.png" alt="profile" title="Change Profile picture"/>
      </div>
      <div class="username">
        <h1 class="text">{{ username }}</h1>
        <img class="pencil1" src="../assets/pencil.png" alt="pencil" title="Change Username"/>
      </div>
      <div class="useremail">
        <h1 class="text">{{ email }}</h1>
        <img class="pencil1" src="../assets/pencil.png" alt="pencil" title="Change User email"/>
      </div>
      <div class="userpassword" style="margin-top: 5vh;">
        <button class="btn">Change my Password</button>
      </div>
      <div class="delete">
        <a class="btn-delete">Delete my account</a>
      </div>
    </main>
    <img class="circles" src="../assets/circles.png" alt="Circles" />
    <img class="dots" src="../assets/dots.png" alt="Dots" />
  </div>
</template>

<style scoped>
.account {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 100vw;
  height: 200vh;
  overflow-x: hidden;
  position: relative;
}
.header {
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 7vh;
  background: linear-gradient(to left, #3d5a80, #293241);
  margin-bottom: 0;
}
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  position: absolute;
  top: 3vh;
  left: 50%;
  transform: translateX(-50%);
  width: 50vw;
  height: 100vh;
  border-radius: 10px;
  overflow: auto;
  background: linear-gradient(to bottom right, #E0FBFC 35%, #B8E3E8);
}
.picture, .username, .useremail, .userpassword, .delete {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-start;
  margin-bottom: 3vh;
}
.profile {
  margin-top: 5vh;
  max-width: 8vw;
  max-height: 16vh;
  cursor: pointer;
}
.pencil1 {
  cursor: pointer;
  transition: 0.5s;
}
.pencil1:hover {
  filter: drop-shadow(1px 1px 1px #ee6c4d);
}
.text {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 250%;
  color: #293241;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
  margin-left: 2vw;
}
button{
  width: 10vw;
  height: 4vh;
  background-color: #3D5A80;
  border-radius: 10rem;
  border: 1px solid;
  cursor: pointer;
}
button:hover {
  background: transparent;
  border-color: #ee6c4d;
  color: #ee6c4d;
  transition: 0.7s;
}
.pencil1:active, button:active {
  transform: scale(0.9);
}
.btn {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 100%;
  color: #E0FBFC;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
  margin-left: 1vw;
}
.btn-delete {
  cursor: pointer;
  font-family: Roboto, sans-serif;
  font-weight: 100;
  font-size: 100%;
  color: #ee6c4d;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
  margin-left: 1vw;
  border-bottom: 1px solid #ee6c4d;
  transition: 0.7s;
}
.btn-delete:hover {
  font-weight: 500;
  transform: scale(1.1);
  border: none;
}
.circles {
  position: absolute;
  top: 10vh;
  left: 0;
  opacity: 0.6;
  filter: blur(1px);
}
.dots {
  position: absolute;
  top: 20vh;
  right: -10vw;
  opacity: 0.6;
  filter: blur(1px);
}
.logout {
  position: fixed;
  top: 2vh;
  left: 1vw;
  max-width: 2vw;
  max-height: 3vh;
  cursor: pointer;
}
.logout:hover {
  filter: grayscale(200%);
  transition: 0.7s;
}
</style>
