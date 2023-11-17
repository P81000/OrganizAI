<script setup>
import { defineProps, ref } from "vue";
import EventComponent from "./EventComponent.vue";

const contentBlurred = ref(false);

defineProps({
  event: {
    type: Object,
  },
});

const showEvent = ref(false);

const openDetails = () => {
  showEvent.value = !showEvent.value;
  contentBlurred.value = showEvent.value;
};
</script>

<template>
  <div class="evento-container" @click="openDetails">
    <p>{{ event.titulo }}</p>
    <p>{{ event.localizacao }}</p>
    <div v-if="showEvent" class="detalhes">
      <div class="showEvent">
        <div class="event-content">
          <EventComponent :is="event"></EventComponent>
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
  width: 40vw;
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
