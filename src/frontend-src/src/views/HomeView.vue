<script setup>
import { onBeforeUnmount, onMounted, ref } from "vue";

const showFixedHeader = ref(false);

const handleScroll = () => {
  showFixedHeader.value = window.scrollY > 100;
};

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
});

onBeforeUnmount(() => {
  window.removeEventListener("scroll", handleScroll);
});
</script>

<template>
  <div class="home">
    <header class="header">
      <div
        class="fixed-header"
        :class="{ 'show-fixed-header': showFixedHeader }"
      >
        <router-link to="/registration" class="button-signin">
          <div class="text-signin">SIGN IN</div>
        </router-link>
        <router-link to="/registration" class="button-signup">
          <div class="text-signup">SIGN UP</div>
        </router-link>
      </div>
      <div class="rectangle">
        <div class="img-container">
          <img
            class="computer-vector"
            src="../assets/computer.png"
            alt="Computer vector"
          />
          <img
            class="setup-vector"
            src="../assets/setup.png"
            alt="Setup vector"
          />
        </div>
        <div class="button-container">
          <router-link to="/registration" class="button-signin">
            <div class="text-signin">SIGN IN</div>
          </router-link>
          <router-link to="/registration" class="button-signup">
            <div class="text-signup">SIGN UP</div>
          </router-link>
        </div>
      </div>
      <div class="welcome-container">
        <h1 class="Welcome">
          Welcome to
          <span class="welcome-name">OrganizAI</span>
        </h1>
      </div>
    </header>
    <main class="main">
      <img class="circles" src="../assets/circles.png" alt="Circles" />
      <img class="dots" src="../assets/dots.png" alt="Dots" />
      <p class="briefing">
        OrganizAI is more than just an agenda!<br />Say goodbye to chaos and
        hello to efficient<br />
        scheduling and task management!
      </p>
      <router-link to="/registration" class="button-get-started">
        <div class="text-get-started">Get Started!</div>
      </router-link>
      <img class="line" src="../assets/line.png" alt="Line" />
      <section class="summary-section">
        <img
          class="woman-at-pc"
          src="../assets/woman-at-pc.png"
          alt="Woman at pc"
        />
        <p class="summary">
          OrganizAI is an innovative project with a mission: To harness the
          potential of AI and transform the way you manage your time. We're not
          just another calendar app, we're your intelligent assistant focused on
          helping you make the most out of every moment.
        </p>
        <ol>
          <li>
            Task Prioritization: AI-driven task prioritization based on goals
            and deadlines.
          </li>
          <li>
            Time Analysis: Insights into time usage for productivity
            enhancement. And more features!
          </li>
        </ol>
        <img class="man-at-pc" src="../assets/man-at-pc.png" alt="Man at pc" />
      </section>
    </main>
    <footer class="footer">
      <div class="bottom-rectangle">
        <div class="social-media-container">
          <a href="https://github.com/P81000/OrganizAI" target="_blank">
            <img
              class="social-media"
              src="../assets/social-media-img/github.png"
              alt="Github"
            />
          </a>
          <router-link to="/about">
            <img
              class="social-media"
              src="../assets/social-media-img/instagram.png"
              alt="Instagram"
            />
          </router-link>
          <router-link to="/about">
            <img
              class="social-media"
              src="../assets/social-media-img/linkedin.png"
              alt="Linkedin"
            />
          </router-link>
        </div>
        <div class="util-container">
          <a href="https://github.com/P81000/OrganizAI" target="_blank">
            <span class="text-util">About Us</span>
          </a>
          <a href="https://github.com/P81000/OrganizAI" target="_blank">
            <span class="text-util">Contact</span>
          </a>
        </div>
        <p class="footer-©">© 2023 OrganizAI. All rights reserved.</p>
      </div>
    </footer>
  </div>
</template>

<style scoped>
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
/* header start */
.header {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  width: 100vw;
  height: 45vh;
}
.fixed-header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  background-color: #293241;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  height: 0;
  z-index: 1000;
  opacity: 0;
  transition: height 0.3s, opacity 0.3s;
}
.show-fixed-header {
  height: 8%;
  opacity: 0.9;
}
.rectangle {
  display: flex;
  justify-content: center;
  align-content: center;
  top: 0;
  width: 100%;
  height: 60%;
  background-color: #293241;
  z-index: 1;
}
.img-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
}
.computer-vector {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
  margin-left: 1%;
}
.setup-vector {
  max-width: 100%;
  max-height: 100%;
  width: auto;
  height: auto;
  margin-right: 1%;
}
.button-container {
  display: flex;
  position: absolute;
  top: 2%;
  right: 0;
}
.button-signin {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 8vw;
  height: 4vh;
  margin: 1%;
  background-color: #e0fbfc;
  border-radius: 10rem;
  border: 1px solid #000000;
  cursor: pointer;
  text-decoration: none;
}
.text-signin {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  color: #293241;
  font-size: 130%;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
}
.button-signup {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 8vw;
  height: 4vh;
  margin: 1%;
  background-color: #3d5a80;
  border-radius: 10rem;
  border: 1px solid #000000;
  cursor: pointer;
  text-decoration: none;
}
.text-signup {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  color: #e0fbfc;
  font-size: 130%;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
}
.button-signup:hover,
.button-signin:hover {
  background-color: #ee6c4d;
  border-color: #e0fbfc;
  transition: 0.7s;
}
.button-signup:active,
.button-signin:active {
  background-color: transparent;
  border-color: #ee6c4d;
  transition: 0.7s;
}
.welcome-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  position: absolute;
  top: 3%;
  z-index: 2;
}
.Welcome {
  font-family: Roboto, sans-serif;
  font-weight: 900;
  font-size: 10vh;
  color: #e0fbfc;
  text-align: center;
  text-shadow: 0 8px 2px rgba(0, 0, 0, 0.6);
  padding-top: 3%;
  cursor: default;
}
.welcome-name {
  display: block;
  font-family: Roboto, sans-serif;
  font-weight: 900;
  font-size: 25vh;
  color: #e0fbfc;
}
/* header end */
/* main start */
.main {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}
.circles {
  position: absolute;
  top: 50%;
  left: 0;
  opacity: 0.5;
}
.dots {
  position: absolute;
  top: -20%;
  right: 0;
  opacity: 0.5;
}
.briefing {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 40px;
  color: #293241;
  margin-top: 8%;
  text-align: center;
}
.button-get-started {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 20vw;
  height: 8vh;
  background-color: #ee6c4d;
  border-radius: 10rem;
  border: 1px solid #000000;
  cursor: pointer;
  text-decoration: none;
}
.text-get-started {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 250%;
  color: #293241;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
}
.button-get-started:hover {
  background-color: #e0fbfc;
  border-color: #e0fbfc;
  transition: 0.7s;
}
.button-get-started:active {
  background-color: transparent;
  border-color: #ee6c4d;
  transition: 0.7s;
}
.line {
  padding-top: 15%;
}
.summary-section {
  display: flex;
  justify-content: space-evenly;
  width: 100vw;
  padding: 5%;
  margin-bottom: 10%;
}
.summary {
  width: 480px;
  height: 265px;
  font-family: Roboto, sans-serif;
  font-weight: 300;
  font-size: 32px;
  font-style: normal;
  color: #293241;
  flex-shrink: 0;
  text-align: justify;
  line-height: 107.9%;
  letter-spacing: 2.72px;
  margin-left: -10px;
}
ol {
  width: 480px;
  height: 269px;
  font-family: Roboto, sans-serif;
  font-weight: 300;
  font-size: 32px;
  font-style: normal;
  color: #293241;
  text-align: justify;
  line-height: 107.9%;
  letter-spacing: 2.7px;
  margin-left: 1%;
}
li {
  list-style-type: disc;
  margin-bottom: 3%;
}
.woman-at-pc,
.man-at-pc {
  width: 20%;
  height: 20%;
  margin-right: 100px;
}
/* main end */
/* footer start */
.footer {
  display: flex;
  justify-content: center;
  align-items: flex-end;
  width: 100vw;
  height: 15vh;
}
.bottom-rectangle {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 100%;
  height: 100%;
  background-color: #293241;
}
.social-media-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 10%;
  margin-top: 2%;
  margin-bottom: 0;
}
.social-media:hover {
  filter: drop-shadow(2px 2px 2px #ee6c4d);
  transition: 0.3s;
}
.util-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 7%;
  margin-top: 1%;
}
a {
  text-decoration: none;
}
.text-util {
  color: #e0fbfc;
  font-family: Roboto, sans-serif;
  font-size: 1rem;
}
.text-util:hover {
  filter: drop-shadow(2px 2px 2px #ee6c4d);
  transition: 0.3s;
}
.footer-© {
  color: #e0fbfc;
  font-family: Roboto, sans-serif;
  margin-top: 1%;
}
</style>
