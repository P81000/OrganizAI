<template>
  <div>
    <div class="calendario-header">
      <button @click="anteriorDia"></button>
      <h2>{{ dataAtual }}</h2>
      <button @click="proximoDia">></button>
    </div>
    <div class="dias">
      <div class="dia" v-for="dia in dias" :key="dia">{{ dia }}</div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";

const dataAtual = ref(new Date());
const dias = ref([]);

const anteriorDia = () => {
  dataAtual.value.setDate(dataAtual.value.getDate() - 1);
  atualizarDias();
};

const proximoDia = () => {
  dataAtual.value.setDate(dataAtual.value.getDate() + 1);
  atualizarDias();
};

const atualizarDias = () => {
  dias.value = [];
  for (let i = 0; i < 7; i++) {
    const dia = new Date(dataAtual.value);
    dia.setDate(dataAtual.value.getDate() + i);
    dias.value.push(dia.getDate());
  }
};

onMounted(() => {
  atualizarDias();
});
</script>

<style>
.calendario-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.dias {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.dia {
  border: 1px solid #ccc;
  padding: 8px;
  text-align: center;
}
</style>
