<!-- TODO: refatorar lógica do dia anterior e proximo dia -->
<!-- TODO: refatorar cartao de evento e evento -->
<script setup>
import { onMounted, ref } from "vue";
import Card from "./CardComponent.vue";
const dataAtual = ref(new Date());
const dataAux = ref(new Date());
const dias = ref([]);
const eventos = ref({});

const anteriorDia = () => {
  const novaData = new Date(dataAux.value);
  novaData.setDate(novaData.getDate() - 1);
  atualizarDias(novaData);
};

const proximoDia = () => {
  const novaData = new Date(dataAux.value);
  novaData.setDate(novaData.getDate() + 1);
  atualizarDias(novaData);
};

const atualizarDias = (novaData) => {
  dataAux.value = new Date(novaData); // Atualiza a data atual
  dias.value = [];
  for (let i = 0; i < 5; i++) {
    const dia = new Date(novaData);
    dia.setDate(novaData.getDate() + i);
    const formatoData = dia.toDateString().substring(0, 10);
    dias.value.push(formatoData);

    eventos.value[formatoData] = [];
  }
};

const isCurrentDay = (dia) => {
  const formatoData = dataAtual.value.toDateString().substring(0, 10);
  return dia === formatoData;
};

const addEvent = (dia) => {
  eventos.value[dia].push({});
};

onMounted(() => {
  atualizarDias(dataAtual.value);
});
</script>

<template>
  <div class="calendar">
    <header class="header">
      <div class="arrow-lines">
        <span @click="anteriorDia" class="arrow left"></span>
        <h3
          class="selectedDay"
          :class="{
            currentDay: isCurrentDay(dataAux.toDateString().substring(0, 10)),
          }"
        >
          {{ dataAux.toDateString().substring(0, 15) }}
        </h3>
        <span @click="proximoDia" class="arrow right"></span>
      </div>
    </header>
    <div class="agenda">
      <div
        class="dayContainer"
        v-for="dia in dias"
        :key="dia"
        :class="{ currentContainer: isCurrentDay(dia) }"
      >
        <h3
          class="day"
          :class="{
            text: isCurrentDay(dia),
          }"
        >
          {{ dia }}
          <img
            class="addEventIcon"
            src="../assets/add.png"
            alt="Add-Event-Icon"
            title="Add a new event"
            @click="addEvent(dia)"
          />
        </h3>
        <Card
          v-for="(evento, eventoIndex) in eventos[dia]"
          :key="eventoIndex"
          :contador="eventoIndex + 1"
          :dia="dia"
        />
      </div>
    </div>
  </div>
</template>

<style scoped>
.calendar {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 100vw;
  height: 100vh;
}
.header {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 7vh;
  background: #293241;
  margin-bottom: 0;
}
.arrow-lines {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 10vw;
  height: 100%;
  margin-right: 3vw;
}
.arrow {
  border: solid #e0fbfc;
  border-width: 0 5px 5px 0;
  border-radius: 1px;
  display: inline-block;
  padding: 0.5vh;
  cursor: pointer;
  transition: 0.2s;
}
.arrow:active {
  padding: 0.8vh;
}
.left {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
}
.left:hover {
  filter: drop-shadow(1px 0px 1px rgba(238, 108, 77, 0.8));
}
.right {
  transform: rotate(-45deg);
  -webkit-transform: rotate(-45deg);
}
.right:hover {
  filter: drop-shadow(0px 1px 1px rgba(238, 108, 77, 0.8));
}
.selectedDay {
  font-family: "Roboto";
  font-weight: 800;
  color: #e0fbfc;
  font-size: 150%;
  text-align: center;
  margin-left: 1vw;
  margin-right: 1vw;
  letter-spacing: 0;
  line-height: normal;
  text-shadow: 1px 1px 0px #293241;
}
.agenda {
  display: flex;
  justify-content: space-around;
  width: 100%;
  height: 100%;
}
.dayContainer {
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: flex-start;
  width: 20%;
  background: #e0fbfc;
  border-right: 1px solid #3d5a80;
  border-left: 1px solid #3d5a80;
}
.currentContainer {
  border-right: 2px solid #ee6c4d;
  border-left: 2px solid #ee6c4d;
}
.day {
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: "Roboto";
  font-weight: 800;
  color: #3d5a80;
  font-size: 200%;
  text-align: left;
  letter-spacing: 0;
  line-height: normal;
  margin-left: 1vw;
  margin-right: 1vw;
}
.addEventIcon {
  margin-left: 1vw;
  margin-right: 1vw;
  transition: 0.5s;
}
.addEventIcon:hover {
  filter: drop-shadow(0px 1px 1px rgba(238, 108, 77, 0.8));
}
.addEventIcon:active {
  transform: scale(0.9);
}
.currentDay {
  color: #ee6c4d;
}
.text {
  text-align: center;
  color: #ee6c4d;
  text-shadow: 1px 1px 1px #3d5a80;
  font-size: 270%;
}
</style>
