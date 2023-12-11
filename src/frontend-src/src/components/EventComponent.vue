<script setup>
import { ref, getCurrentInstance, onMounted, toRefs } from 'vue';
import EventoService from "@/service/EventoService";

const { emit } = getCurrentInstance();
const props = defineProps(['evento', 'dia']);
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
      } else {
        alert("Delete not ok");
      }
    } catch (error) {
      console.log("Error: ", error);
    }
  } else {
    alert("Cannot delete. Task not created yet.");
  }
};

onMounted(() => {
  // Preencher os campos do formulário com as informações do evento, se disponíveis
  if (props.evento) {
    console.log(props.evento);
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
  <div id="cadastroEventoForm">
    <h2>Cadastro de Evento</h2>
    <form id="eventoForm" class="custom-login-form" @submit.prevent="submitEvent">
      <label for="titulo">Título:</label>
      <input type="text" v-model="form.titulo" class="titulo" required><br>

      <label for="descricao">Descrição:</label>
      <input type="text" v-model="form.descricao" class="descricao" required><br>

      <label for="dataInicio">Data de Início:</label>
      <input type="datetime-local" v-model="form.data_inicio" class="dataIn" required><br>

      <label for="dataFim">Data de Fim:</label>
      <input type="datetime-local" v-model="form.data_fim" class="dataOut" required><br>

      <label for="cidade">Cidade:</label>
      <input type="text" v-model="form.cidade" class="cidade" required><br>

      <label for="estado">Estado:</label>
      <input type="text" v-model="form.estado" class="estado" required><br>

      <button type="submit" class="save">Save</button>
      <button type="button" class="custom-button" @click="close">Close</button>
    </form>
    <button @click="evento && deleteEvent(evento.id)">Delete</button>
    <div class="details">Details</div>

  </div>
</template>
