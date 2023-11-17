<template>
  <div id="cadastroTarefaForm">
    <h2>Cadastro de Tarefa</h2>
    <form id="tarefaForm" class="custom-login-form" @submit.prevent="submitTask">
      <label for="titulo">Título:</label>
      <input type="text" v-model="form.titulo" class="custom-input" required><br>

      <label for="descricao">Descrição:</label>
      <input type="text" v-model="form.descricao" class="custom-input" required><br>

      <label for="dataInicio">Data de Início:</label>
      <input type="datetime-local" v-model="form.data_inicio" class="custom-input" required><br>

      <label for="dataFim">Data de Fim:</label>
      <input type="datetime-local" v-model="form.data_fim" class="custom-input" required><br>

      <label for="localizacao">Localização:</label>
      <input type="text" v-model="form.localizacao" class="custom-input" required><br>

      <label for="idNotificacao">ID de Notificação:</label>
      <input type="text" v-model="form.id_notificacao" class="custom-input"><br>

      <label for="idInfoClima">ID de Informação de Clima:</label>
      <input type="text" v-model="form.id_info_clima" class="custom-input"><br>

      <label for="idInfoTrajeto">ID de Informação de Trajeto:</label>
      <input type="text" v-model="form.id_info_trajeto" class="custom-input"><br>

      <button type="submit" class="custom-button">Salvar Tarefa</button>
      <button type="button" class="custom-button" @close="$emit('close')">Fechar</button>
    </form>
    <button @click="deleteTask()"></button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import EventoService from "@/service/EventoService";

const form = ref({
  titulo: '',
  descricao: '',
  data_inicio: '',
  data_fim: '',
  localizacao: '',
  id_notificacao: '',
  id_info_clima: '',
  id_info_trajeto: ''
});

const submitTask = async () => {
  console.log("Form Values Before Submission:", form);

  // Implement the logic to send the task to the server
  const tarefa = {
    titulo: form.value.titulo,
    descricao: form.value.descricao,
    data_inicio: form.value.data_inicio,
    data_fim: form.value.data_fim,
    localizacao: form.value.localizacao,
    id_notificacao: form.value.id_notificacao,
    id_info_clima: form.value.id_info_clima,
    id_info_trajeto: form.value.id_info_trajeto
  };

  console.log("Tarefa Object:", tarefa)
  try {
    console.log(JSON.stringify(tarefa));
    const response = await EventoService.setEventos(JSON.stringify(tarefa));
    if(response){
      alert("Created");
    } else {
      alert("Error");
    }
  } catch(error) {
    console.error("Erro: ", error);
  }

  // Limpar o formulário após o cadastro
  form.titulo = '';
  form.descricao = '';
  form.data_inicio = '';
  form.data_fim = '';
  form.localizacao = '';
  form.id_notificacao = '';
  form.id_info_clima = '';
  form.id_info_trajeto = '';
};

const deleteTask = async () => {
  try {
    const response = await EventoService.deletEvento(7);
    if(response.ok) {
      alert("Delete OK");
    } else {
      alert("Delete not ok");
    }
  } catch(error){
    console.log("Error: ", error);
  }

};
</script>
