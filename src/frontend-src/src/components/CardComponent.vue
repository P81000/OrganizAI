<script setup>
import { defineProps, ref } from "vue";
import EventComponent from "./EventComponent.vue";

const contentBlurred = ref(false);
const event = ref(EventComponent);

defineProps({
  contador: {
    type: Number,
  },
  dia: {
    type: String,
  },
});
const detalhesVisiveis = ref(false);

const openDetails = () => {
  detalhesVisiveis.value = !detalhesVisiveis.value;
  contentBlurred.value = detalhesVisiveis.value;
};
</script>

<template>
  <div class="evento-container" @click="openDetails">
    <p>Evento {{ contador }} do {{ dia }}</p>
    <div v-if="detalhesVisiveis" class="detalhes">
      <div class="showEvent">
        <div class="event-content">
          <component :is="event"></component>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.evento-container {
  width: auto;
  height: 15vh;
  background-color: #f0f0f0;
  margin: 1vw;
  cursor: pointer;
  position: relative;
}

.showEvent {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 40vw; /* ajuste conforme necessário */
  height: 80vh;
  background-color: #f0f0f0;
  z-index: 1000;
}

.event-content {
  width: 100%;
  height: 100%;
}

.blur-content {
  filter: blur(1px);
}
</style>
