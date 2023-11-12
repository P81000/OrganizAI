<script setup>
import { ref } from "vue";
let msg = ref(0);

const plus = () => {
  msg.value++;
};

const minus = () => {
  msg.value--;
};
</script>

<template>
  <div>
    <h1>{{ msg }}</h1>
    <button @click="plus">Incrementar</button>
    <button @click="minus">Decrementar</button>
  </div>
</template>

<style scoped>
div {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
