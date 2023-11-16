<!-- TODO: refatorar cartao de evento e evento -->
<script setup>
import { onMounted, ref } from "vue";
import Card from "./CardComponent.vue";
import Event from "./EventComponent.vue";
import EventoService from '../service/EventoService.js';

const dataAtual = ref(new Date());
const dataAux = ref(new Date());
const dias = ref([]);
const events = ref([]);
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
  for (let i = 0; i < 5; i++) {
    const dia = new Date(novaData);
    dia.setDate(novaData.getDate() + i);
    const formatoData = dia.toDateString().substring(0, 10);
    dias.value.push({
      dia: dia.getDate(),
      mes: dia.getMonth() + 1,
      ano: dia.getFullYear(),
      formatData: formatoData,
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

const isSameDate = (dia, event) => {
  const [eventYear, eventMonth, eventDay] = event.split("T")[0].split("-");
  return (
      parseInt(eventDay, 10) === dia.dia &&
      parseInt(eventMonth, 10) === dia.mes &&
      parseInt(eventYear, 10) === dia.ano
  );
};

const showModal = () => {
  console.log(showEvent.value);
  showEvent.value = !showEvent.value;
};

onMounted(async () => {
  atualizarDias(dataAtual.value);
  try {
    const response = await EventoService.getEventos();
    for (const i of response.data){
      console.log(i);
      events.value.push(i);
    }
  } catch(error) {
    console.log("Erro getEventos: ", error);
  }
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
        <div v-for="event in events" :key="event.id">
          <Card
            v-if="isSameDate(dia, event.data_inicio)"
            :event="event"
          />
        </div>
      </div>
      <Event class="addEvent" v-if="showEvent" @close="showModal()"></Event>
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
  align-items: start;
  justify-content: flex-start;
  width: 20%;
  background: #e0fbfc;
  border-right: 1px solid #3d5a80;
  border-left: 1px solid #3d5a80;
  overflow: auto;
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

.addEvent {
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

</style>
