<!-- TODO: refatorar cartao de evento e evento -->
<script setup>
import {getCurrentInstance, onMounted, ref} from "vue";
import Card from "./CardComponent.vue";
import Event from "./EventComponent.vue";
import EventoService from '../service/EventoService.js';

const dataAtual = ref(new Date());
const dataAux = ref(new Date());
const dias = ref([]);
const eventos = ref([]);
const showEvent = ref(false);

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
  dataAux.value = new Date(novaData);
  dias.value = [];
  for (let i = 0; i < 6; i++) {
    const dia = new Date(novaData);
    dia.setDate(novaData.getDate() + i);
    const formatoData = dia.toDateString().substring(0, 10);
    dias.value.push({
      dia: dia.getDate(),
      mes: dia.getMonth() + 1,
      ano: dia.getFullYear(),
      formatData: formatoData,
      fullInfo: dia,
    });
  }
};

const isCurrentDay = (dia) => {
  const currentDay = dataAtual.value.getDate();
  const currentMonth = dataAtual.value.getMonth() + 1;
  const currentYear = dataAtual.value.getFullYear();

  return (
      parseInt(dia.dia, 10) === currentDay &&
      parseInt(dia.mes, 10) === currentMonth &&
      parseInt(dia.ano, 10) === currentYear
  );
};

const isSameDate = (dia, evento) => {
  const [eventoYear, eventoMonth, eventoDay] = evento.split("T")[0].split("-");
  return (
      parseInt(eventoDay, 10) === dia.dia &&
      parseInt(eventoMonth, 10) === dia.mes &&
      parseInt(eventoYear, 10) === dia.ano
  );
};

const showModal = () => {
  showEvent.value = !showEvent.value;
};

const closeModal = () => {
  showEvent.value = !showEvent.value;
}
const handleKeyDown = (event) => {
  if (event.key === "ArrowLeft") {
    anteriorDia();
  }
  if (event.key === "ArrowRight") {
    proximoDia();
  }
};

onMounted(async () => {
  atualizarDias(dataAtual.value);
  window.addEventListener("keydown", handleKeyDown);
  if (eventos.value.length === 0) {
    try {
      const response = await EventoService.getEventos();
      for (const i of response.data){
        console.log(i);
        eventos.value.push(i);
      }
    } catch(error) {
      console.log("Erro getEventos: ", error);
    }
  }
});
</script>

<template>
  <div class="calendar">
    <header class="header">
      <div class="arrow-lines" title="Press or use your keyboard arrows">
        <span @click="anteriorDia" class="arrow left"></span>
        <h3
          class="selectedDay"
          :class="{
            currentDay: isCurrentDay({ 'dia': dataAux.getDate(),
                                           'mes': dataAux.getMonth() + 1,
                                           'ano': dataAux.getFullYear()
            }),
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
          {{ dia.formatData }}
          <img
            class="addEventIcon"
            src="../assets/add.png"
            alt="Add-Event-Icon"
            title="Add a new event"
            @click="showModal()"
          />
        </h3>
        <div v-for="evento in eventos" :key="evento.id">
          <Card
            v-if="isSameDate(dia, evento.data_inicio)"
            :eventoCard="evento"
          />
        </div>
      </div>
      <Event
        class="addEvent"
        v-if="showEvent"
        @close="closeModal()"
        :eventCard="eventos[eventos.length - 1]">
      </Event>
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
  background: linear-gradient(to left, #3d5a80, #293241);
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
  font-family: Roboto,serif;
  font-weight: 800;
  color: #e0fbfc;
  font-size: 150%;
  text-align: center;
  margin-left: 1vw;
  margin-right: 1vw;
  letter-spacing: 0;
  line-height: normal;
  text-shadow: 1px 1px 0 #293241;
}
.agenda {
  display: flex;
  justify-content: space-around;
  width: 100%;
  height: 100%;
  flex-wrap: nowrap;
  overflow-x: auto;
}
.dayContainer {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  width: 20%;
  background: #e0fbfc;
  border-right: 1px solid #3d5a80;
  border-left: 1px solid #3d5a80;
  overflow: auto;
  overflow-x: hidden;
}
.currentContainer {
  border-right: 2px solid #ee6c4d;
  border-left: 2px solid #ee6c4d;
}
.day {
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Roboto, serif;
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
  border-bottom: 1px solid #ee6c4d;
}

.addEvent {
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
::-webkit-scrollbar {
  width: 3px;
  height: 10px;
}
::-webkit-scrollbar-thumb {
  background-color: #3D5A80;
  border-radius: 10px;
}
::-webkit-scrollbar-track {
  background-color: transparent;
}
</style>
