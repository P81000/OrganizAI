<script setup>
import {defineProps, getCurrentInstance, onMounted, ref} from 'vue';
import EventoService from "@/service/EventoService";
import TaskComponent from "./TaskComponent.vue";
import TarefaService from "@/service/TarefaService";

const {emit} = getCurrentInstance();
const props = defineProps(['evento', 'selectedDate']);

const form = ref({
  titulo: '',
  descricao: '',
  data_inicio: '',
  data_fim: '',
  cidade: '',
  estado: '',
});

const submitEvent = async () => {
  try {
    const response = await EventoService.setEventos(JSON.stringify(form.value));
    console.log(response);
    if (response) {
      alert("Event Created!!");
      emit("success");
      window.location.reload();
    } else {
      alert("Error");
    }
  } catch (error) {
    console.error("Erro: ", error);
  }

  form.titulo = '';
  form.descricao = '';
  form.data_inicio = '';
  form.data_fim = '';
  form.cidade = '';
  form.estado = '';
};

const close = () => {
  emit("close");
};

const deleteEvent = async (id) => {
  if (id) {
    try {
      const response = await EventoService.deleteEvento(id);
      if (response) {
        alert("Event Deleted!!");
        window.location.reload();
      } else {
        alert("Delete goes wrong");
      }
    } catch (error) {
      console.log("Error: ", error);
    }
  } else {
    alert("Cannot delete. Event not created yet.");
  }
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

onMounted(() => {
  if (props.selectedDate) {
    form.value.data_inicio = `${props.selectedDate.ano}-${String(props.selectedDate.mes).padStart(2, '0')}-${String(props.selectedDate.dia).padStart(2, '0')}T00:00`;
  }
  if (props.evento) {
    form.value.titulo = props.evento.titulo || '';
    form.value.descricao = props.evento.descricao || '';
    form.value.data_inicio = props.evento.data_inicio || '';
    form.value.data_fim = props.evento.data_fim || '';
    form.value.cidade = props.evento.cidade || '';
    form.value.estado = props.evento.estado || '';
  }
});
</script>

<template>
  <div class='eventForm'>
    <form @submit.prevent='submitEvent'>

      <header class='header'>
        <input
            type='text'
            v-model='form.titulo'
            placeholder='Title'
            class='titulo'
            required
        />
        <input
            class="checkbox"
            @click="close"
            type="checkbox"
            name=""
            id=""
        />
        <div class="hamburger-lines">
          <span class="line line1"></span>
          <span class="line line2"></span>
          <span class="line line3"></span>
        </div>
      </header>

      <main class='body'>
        <div class='datas'>
          <label class='txt'>Start: </label>
          <input
              type='datetime-local'
              v-model='form.data_inicio'
              placeholder='Start Date'
              class='date'
              required
          />
          <label class='txt'>Due: </label>
          <input
              type='datetime-local'
              v-model='form.data_fim'
              placeholder='Due Date'
              class='date'
              required
          />
        </div>
        <label class="txt">Event Description: </label><br />
        <input
            type='text'
            v-model='form.descricao'
            placeholder="Description goes here!"
            class='description'
        /><br/>

        <input
            type='text'
            v-model='form.cidade'
            class='cidade'
            placeholder='City'
            required
        />
        <input
            type='text'
            v-model='form.estado'
            class='estado'
            placeholder='State'
            required
        />

        <div class="tarefas-container" v-if="props.evento && Array.isArray(props.evento.tarefas)">
          <h3>Tarefas:</h3>
          <ul>
            <li v-for="tarefa in props.evento.tarefas">
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
      </main>
      <footer>
        <button class='normal' @click='submitEvent'>Save</button>
        <button class='invert' @click='evento && deleteEvent(evento.id)'>Delete</button>
      </footer>
    </form>
  </div>
</template>

<style scoped>
.eventForm {
  background-color: rgba(230, 240, 242, 1);
  border-radius: 12px;
  border: 1px solid #293241;
  display: flex;
}

.header {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  height: 7vh;
}

.checkbox {
  position: absolute;
  top: 3vh;
  right: 0.8vw;
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
  top: 3.5vh;
  right: 1vw;
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
  background: #293241;
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

input[type="checkbox"]:hover ~ .hamburger-lines .line1 {
  transform: translateX(0.1vw) translateY(0.05vh) rotate(45deg);
  background: #293241;
}

input[type="checkbox"]:hover ~ .hamburger-lines .line2 {
  transform: scaleY(0);
}

input[type="checkbox"]:hover ~ .hamburger-lines .line3 {
  transform: translateX(0.1vw) rotate(-45deg);
  background: #293241;
}

.titulo {
  border: none;
  width: 90%;
  border-bottom: 1px solid #ee6c4d;
  font-family: Roboto, sans-serif;
  font-weight: 1000;
  font-size: 250%;
  color: #293241;
  outline: none;
  background: none;
  transition: 0.5s;
}

.titulo::placeholder {
  opacity: 0.8;
}

.date {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 130%;
  color: #3D5A80;
  padding: 0.5rem;
  border: 1px solid #ee6c4d;
  border-radius: 12px;
  margin-right: 1rem;
  margin-bottom: 5rem;
  outline: none;
  transition: 0.5s;
}

.date:focus {
  transform: scale(1.03)
}

.txt {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 140%;
  color: #3D5A80;
  opacity: 1;
}

.description {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 150%;
  color: #293241;
  width: 100%;
  height: 20vh;
  border: 1px solid #ee6c4d;
  border-radius: 12px;
  outline: none;
  margin-top: 0.5rem;
  margin-bottom: 3rem;
  transition: 0.5s;
}
.description::placeholder {
  opacity: 0.5;
}

.cidade, .estado {
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 110%;
  color: #3D5A80;
  width: 40%;
  padding: 0.5rem;
  border: 1px solid #ee6c4d;
  border-radius: 12px;
  margin-right: 1rem;
  margin-bottom: 1rem;
  outline: none;
  background-color: #fff;
  transition: 0.5s;
}

.cidade:focus, .estado:focus {
  transform: scale(1.05);
}

footer {
  position: fixed;
  bottom: 3vh;
  display: flex;
  align-items: center;
  justify-content: center; /* Centralizar horizontalmente */
  width: 100%; /* Para ocupar toda a largura da tela */
}
button {
  width: 5vw;
  height: 3vh;
  background-color: #ee6c4d;
  border-radius: 10rem;
  border: 1px solid;
  cursor: pointer;
  font-family: Roboto, sans-serif;
  font-weight: 800;
  font-size: 150%;
  color: #e0fbfc;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
  margin-right: 5rem;
}
button:active {
  transform: scale(0.9);
  transition: 0.5s;
}
button.invert:hover {
  background-color: transparent;
  border-color: #ee6c4d;
  color: #ee6c4d;
  transition: 0.7s;
}
button.normal:hover {
  background-color: transparent;
  border-color: #ee6c4d;
  color: #293241;
  transition: 0.7s;
}
button.invert {
  background-color: transparent;
  border-color: #293241;
  color: #293241;
}
</style>