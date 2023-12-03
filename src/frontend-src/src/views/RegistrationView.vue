<script setup>
import router from "@/router";
import { ref } from "vue";
const username = ref("");
const email = ref("");
const password = ref("");
const showSignUp = ref(false);
const submitForm = () => {
  if (username.value && email.value && password.value) {
    redirectMainPage();
  }
};
const redirectMainPage = () => {
  router.push("/main-page");
};
</script>

<template>
  <div class="home">
    <header class="header">
      <div class="rectangle">
        <router-link to="/">
          <img class="arrow" src="../assets/arrow.png" alt="Arrow" />
        </router-link>
      </div>
    </header>
    <main class="main">
      <article>
        <div class="container" :class="{ 'sign-up-active': showSignUp }">
          <div class="overlay-container">
            <div class="overlay">
              <div class="overlay-left">
                <h2 class="title">Welcome Back!</h2>
                <p>What a pleasure see you again! Sign In with your account</p>
                <button
                  class="invert"
                  id="signIn"
                  @click="showSignUp = !showSignUp"
                >
                  Sign In
                </button>
              </div>
              <div class="overlay-right">
                <h2 class="title">Hello Friend! Do I know you?</h2>
                <p>If is your first time here, don't be shy SIGN UP!</p>
                <button
                  class="invert"
                  id="showSignUp"
                  @click="showSignUp = !showSignUp"
                >
                  Sign Up
                </button>
              </div>
            </div>
          </div>
          <form class="sign-up" @submit.prevent="submitForm()">
            <h2 class="account">Create your account</h2>
            <div class="social-login">
              <a href="#" class="social">
                <div class="social-icons">
                  <img
                    class="reg-img"
                    src="../assets/social-media-img/google.png"
                    alt="Registration-image"
                  />
                </div>
                <div class="social-icons">
                  <img
                    class="reg-img"
                    src="../assets/social-media-img/linkedin.png"
                    alt="Registration-image"
                  />
                </div>
                <div class="social-icons">
                  <img
                    class="reg-img"
                    src="../assets/social-media-img/github.png"
                    alt="Registration-image"
                  />
                </div>
              </a>
            </div>
            <p class="option">or use your email for registration</p>
            <input v-model="username" type="text" placeholder="Name" required />
            <input v-model="email" type="email" placeholder="Email" required />
            <input
              v-model="password"
              type="password"
              placeholder="Password"
              required
            />
            <button class="normal" type="submit">SignUp</button>
          </form>
          <form class="sign-in" action="#">
            <h2 class="account">Sign In</h2>
            <div class="social-login">
              <a href="#" class="social">
                <div class="social-icons">
                  <img
                    class="reg-img"
                    src="../assets/social-media-img/google.png"
                    alt="Registration-image"
                  />
                </div>
                <div class="social-icons">
                  <img
                    class="reg-img"
                    src="../assets/social-media-img/linkedin.png"
                    alt="Registration-image"
                  />
                </div>
                <div class="social-icons">
                  <img
                    class="reg-img"
                    src="../assets/social-media-img/github.png"
                    alt="Registration-image"
                  />
                </div>
              </a>
            </div>
            <p class="option">or use your account</p>
            <input type="email" placeholder="Email" />
            <input type="password" placeholder="Password" />
            <a href="#">Forgot your password?</a>
            <button class="normal">Sign In</button>
          </form>
        </div>
      </article>
    </main>
  </div>
</template>

<style scoped>
.home {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex-direction: column;
  width: 100vw;
  height: 100vh;
}
.header {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 100vw;
  height: 10vh;
}
.rectangle {
  width: 100%;
  height: 100%;
  background-color: #293241;
}
.arrow {
  position: absolute;
  width: 2%;
  height: 4%;
  margin-top: 2vh;
  margin-left: 1vw;
  z-index: 1000;
}
.arrow:hover {
  filter: drop-shadow(2px 2px 2px #ee6c4d);
  transition: 0.3s;
}
.arrow:active {
  filter: brightness(0.5);
  transition: 0.7s;
}
.main {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100vw;
  height: 100vh;
}
.container {
  position: relative;
  width: 75vw;
  height: 88vh;
  border-radius: 10px;
  overflow: hidden;
  background: #e0fbfc;
}
.overlay-container {
  position: absolute;
  top: 0;
  left: 50%;
  width: 50%;
  height: 100%;
  overflow: hidden;
  transition: transform 0.5s ease-in-out;
  z-index: 100;
}
.overlay {
  position: relative;
  left: -100%;
  height: 100%;
  width: 200%;
  background: linear-gradient(to bottom right, #3d5a80, #293241);
  transform: translateX(0);
  transition: transform 0.5s ease-in-out;
}
.overlay-left {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  top: 0;
  left: 0;
  padding: 70px 40px;
  width: calc(50% + 20vw);
  height: calc(50% + 30vh);
  text-align: center;
  transform: translateX(-20%);
  transition: transform 0.5s ease-in-out;
}
.overlay-right {
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  top: 0;
  right: 0;
  padding: 70px 40px;
  width: calc(50% - 5vw);
  height: calc(100% - 15vh);
  text-align: center;
  transform: translateX(0);
  transition: transform 0.5s ease-in-out;
}
.title {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 3rem;
  color: #e0fbfc;
  margin: 0;
}
.account {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 3rem;
  color: #293241;
  margin: 0;
}
p {
  font-family: Roboto, sans-serif;
  font-weight: 100;
  font-size: 1.5rem;
  color: #e0fbfc;
  margin: 5%;
}
p.option {
  margin-bottom: 1%;
  color: #293241;
}

button {
  width: 10vw;
  height: 5vh;
  background-color: #ee6c4d;
  border-radius: 10rem;
  border: 1px solid;
  cursor: pointer;
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 200%;
  color: #e0fbfc;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
}
button:active {
  transform: scale(0.9);
  transition: 0.5s;
}
button.invert:hover {
  background-color: transparent;
  border-color: #ee6c4d;
  color: #ee6c4d;
  transition: 0.7s;
}
button.normal:hover {
  background-color: transparent;
  border-color: #ee6c4d;
  color: #293241;
  transition: 0.7s;
}
button.invert {
  background-color: transparent;
  border-color: #e0fbfc;
}
.social {
  display: flex;
  align-items: center;
  justify-content: center;
}
.social-icons {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 4rem;
  height: 4rem;
  border-radius: 50%;
  background-color: #e0fbfc;
  margin: 2vh;
}
.social-icons:hover {
  filter: drop-shadow(2px 2px 2px #ee6c4d);
  transition: 0.3s;
}

.reg-img {
  width: 30px;
  height: 30px;
  opacity: 0.9;
  margin: 2vh;
}

form {
  position: absolute;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: space-around;
  flex-direction: column;
  padding: 90px 60px;
  width: calc(50% - 120px);
  height: calc(100% - 180px);
  text-align: center;
  background: linear-gradient(to bottom left, #98c1d9, #e0fbfc);
  transition: all 0.5s ease-in-out;
}

input {
  border: none;
  padding: 2% 10%;
  width: 20vw;
  border-radius: 5rem;
  border-bottom: 1px solid #ee6c4d;
  overflow: hidden;
  font-family: Roboto, sans-serif;
  font-weight: 500;
  font-size: 120%;
  text-align: center;
  color: #293241;
}
input:focus {
  outline: none;
  background-color: #e0fbfc;
  border: 1px solid #ee6c4d;
  border-bottom: none;
}
a {
  color: #ee6c4d;
  margin: 15px 0;
  font-size: 1rem;
}
.sign-in {
  left: 0;
  z-index: 2;
}
.sign-up {
  left: 0;
  z-index: 1;
  opacity: 0;
  animation: show 0.5s;
}
.sign-up-active .sign-in {
  transform: translateX(100%);
}
.sign-up-active .sign-up {
  transform: translateX(100%);
  z-index: 5;
  opacity: 1;
}
.sign-up-active .overlay-container {
  transform: translateX(-100%);
}
.sign-up-active .overlay {
  transform: translateX(50%);
}
</style>
