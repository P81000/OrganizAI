<template>
  <div class="not-found">
    <div class="rectangle">
      <img class="logo" alt="logo" src="../assets/logo.png" />
    </div>
    <h2>Oops! 😮</h2>
    <h2>404 - Page not found</h2>
    <p>Are you lost buddy?</p>
    <p>Lets get back home, we miss you!</p>
    <router-link to="/">🏡 back to main page</router-link>
  </div>
</template>

<style scoped>
.not-found {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 100vw;
  height: 90vh;
  text-align: center;
  padding: 20px;
  font-size: 24px;
}
.rectangle {
  position: absolute;
  display: flex;
  justify-content: center;
  width: 100vw;
  height: 10vh;
  top: 0;
  left: 0;
  background-color: #293241;
  box-shadow: 0 10px 10px rgba(0, 0, 0, 0.5);
}
.logo {
  width: 5vw;
  height: 10vh;
}
h1 {
  font-family: Roboto, sans-serif;
  font-size: 100px;
  margin: 0;
}
h2 {
  font-family: Roboto, sans-serif;
  font-size: 36px;
  margin-top: 10px;
  color: #777;
}
p {
  font-family: Roboto, sans-serif;
  margin-top: 20px;
}
router-link {
  display: inline-block;
  margin-top: 10px;
  font-size: 24px;
  text-decoration: none;
}
</style>
