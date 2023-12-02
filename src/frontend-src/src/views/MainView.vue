<script setup>
import CalendarComponent from "@/components/CalendarComponent.vue";
import AccountComponent from "@/components/AccountComponent.vue";
import AssistantComponent from "@/components/AssistantComponent.vue";
import { shallowRef } from "vue";

const ShowLeftBar = shallowRef(true);
const contentBlurred = shallowRef(false);
const activeComponent = shallowRef(CalendarComponent);

const toggleLeftBar = () => {
  ShowLeftBar.value = !ShowLeftBar.value;
  contentBlurred.value = !ShowLeftBar.value;
};

const setActiveComponent = (component) => {
  activeComponent.value = component;
};
</script>

<template>
  <div class="main-page">
    <div class="left-bar" :class="{ show: !ShowLeftBar }">
      <input
        class="checkbox"
        @click="toggleLeftBar"
        type="checkbox"
        name=""
        id=""
      />
      <div class="hamburger-lines">
        <span class="line line1"></span>
        <span class="line line2"></span>
        <span class="line line3"></span>
      </div>
      <nav v-if="!ShowLeftBar">
        <a
          @click="setActiveComponent(CalendarComponent)"
          class="nav-link"
          :class="{ activeLink: activeComponent === CalendarComponent }"
          >Calendar
        </a>
        <a
          @click="setActiveComponent(AssistantComponent)"
          class="nav-link"
          :class="{ activeLink: activeComponent === AssistantComponent }"
          >Assistant
        </a>
        <a
          @click="setActiveComponent(AccountComponent)"
          class="nav-link"
          :class="{ activeLink: activeComponent === AccountComponent }"
          >Account
        </a>
      </nav>
    </div>
    <div class="content" :class="{ 'blur-content': contentBlurred }">
      <component :is="activeComponent"></component>
    </div>
  </div>
</template>

<style scoped>
.main-page {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  justify-content: flex-start;
  width: 100vw;
  height: 100vh;
}
.left-bar {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 0;
  height: 100vh;
  background: linear-gradient(to top left, #3d5a80, #293241);
  transition: width 0.5s;
  z-index: 100;
}
.checkbox {
  position: absolute;
  top: 1.5vh;
  left: 0.8vw;
  display: block;
  height: 3vh;
  width: 3vh;
  z-index: 5;
  opacity: 0;
  cursor: pointer;
  transition: 0.3s;
}
.hamburger-lines {
  position: absolute;
  top: 2vh;
  left: 1vw;
  height: 2.5vh;
  width: 1.5vw;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.hamburger-lines .line {
  display: grid;
  height: 15%;
  width: 100%;
  border-radius: 12px;
  background: #e0fbfc;
  transition: 0.5s;
}

.hamburger-lines .line1 {
  transform-origin: 0 0;
  transition: transform 0.4s ease-in-out;
}

.hamburger-lines .line2 {
  transition: transform 0.2s ease-in-out;
}

.hamburger-lines .line3 {
  transform-origin: 0 100%;
  transition: transform 0.4s ease-in-out;
}
input[type="checkbox"]:checked ~ .hamburger-lines .line1 {
  transform: translateX(0.1vw) translateY(0.05vh) rotate(45deg);
  background: #e0fbfc;
}

input[type="checkbox"]:checked ~ .hamburger-lines .line2 {
  transform: scaleY(0);
}

input[type="checkbox"]:checked ~ .hamburger-lines .line3 {
  transform: translateX(0.1vw) rotate(-45deg);
  background: #e0fbfc;
}

input[type="checkbox"]:hover ~ .hamburger-lines .line {
  transition: 0.2s;
  filter: drop-shadow(2px 2px 5px rgba(238, 108, 77, 0.4));
}
nav {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 10vh;
}
a {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  color: #e0fbfc;
  font-size: 100%;
  text-align: center;
  line-height: normal;
  cursor: pointer;
  transition: 0.5s;
  margin: 0.5vh;
}
a:hover {
  filter: drop-shadow(1px 1px 1px rgba(238, 108, 77, 0.5));
}
.activeLink {
  font-size: 200%;
  color: #ee6c4d;
}
.show {
  width: 15vw;
}
.content {
  position: fixed;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  z-index: 1;
}
.blur-content {
  filter: blur(1px);
}
</style>
