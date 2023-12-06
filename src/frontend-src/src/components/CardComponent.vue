<script setup>
import { defineProps, ref, getCurrentInstance } from "vue";
import EventComponent from "./EventComponent.vue";
import TaskComponent from "./TaskComponent.vue";
import TarefaService from "@/service/TarefaService";

defineProps({
  eventoCard: {
    type: Object,
  },
});

const { emit } = getCurrentInstance();
const showEvent = ref(false);

const openDetails = () => {
    showEvent.value = true;
};

const closeDetails = () => {
  emit("close");
  showEvent.value = false;
};

const showTaskForm = ref(false);

const openTaskForm = () => {
  showTaskForm.value = true;
};

const closeTaskForm = () => {
  showTaskForm.value = false;
};

const handleTaskSuccess = () => {
  closeTaskForm();
};

const deleteTask = async (id) => {
   try {
     const response = await TarefaService.deleteTarefa(id);
     if (response) {
       alert("Task Deleted!!");
     } else {
       alert("Delete not ok");
     }
   } catch (error) {
     console.log("Error: ", error);
   }
};
</script>

<template>
  <div class="evento-container" @click="openDetails">
    <p class="evento-info titulo">{{ eventoCard.titulo }}</p>
    <p class="evento-info desc">{{ eventoCard.descricao }}</p>
    <div v-if="showEvent" class="detalhes">
      <div class="showEvent">
        <div class="event-content">
          <EventComponent :evento="eventoCard"></EventComponent>
          <div class="tarefas-container">
            <h3>Tarefas:</h3>
            <ul>
              <li v-for="tarefa in eventoCard.tarefas">
                {{ tarefa.titulo }}
                 <button @click="deleteTask(tarefa.idTarefa)">Deletar Tarefa</button>
              </li>
            </ul>
          </div>
          <button @click="openTaskForm">Adicionar Tarefa</button>
            <TaskComponent
              v-if="showTaskForm"
              @close="closeTaskForm"
              @success="handleTaskSuccess"
              :eventoId="eventoCard.id"
            />
          <button @click="closeDetails">Fechar Detalhes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.evento-container {
  width: 15vw;
  height: 10vh;
  border-radius: 12px;
  background: rgba(152, 193, 217, 0.3);
  margin: 0.5vw;
  padding: 1%;
  cursor: pointer;
  position: relative;
  box-shadow: 1px 2px 5px #293241;
}
.evento-info{
  font-style: italic;
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 120%;
  text-align: left;
  margin-left: 3%;
  color: #293241;
}
.titulo {
  font-size: 150%;
  border-bottom: 1px solid #ee6c4d;
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
</style>
