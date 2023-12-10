<template>
  <div id="cadastroTarefaForm">
    <h2>Cadastro de Tarefa</h2>
    <form id="tarefaForm" class="custom-login-form" @submit.prevent="submitTarefa>
      <label for="titulo">Título:</label>
      <input type="text" v-model="form.titulo" class="custom-input" required><br>

      <label for="descricao">Descrição:</label>
      <input type="text" v-model="form.descricao" class="custom-input" required><br>

      <label for="status">Status:</label>
      <input type="text" v-model="form.status" class="custom-input" required><br>

      <button type="submit" class="custom-button">Salvar Tarefa</button>
      <button type="button" class="custom-button" @close="$emit('close')">Fechar</button>
    </form>
    <button @click="deleteTarefa()"></button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import TarefaService from "@/service/TarefaService";

const form = ref({
  titulo: '',
  descricao: '',
  status: ''
});

const submitTarefa = async () => {
  console.log("Form Values Before Submission:", form);

  // Implement the logic to send the task to the server
  const tarefa = {
    titulo: form.value.titulo,
    descricao: form.value.descricao,
    status: form.value.status
  };

  console.log("Tarefa Object:", tarefa)
  try {
    console.log(JSON.stringify(tarefa));
    const response = await TarefaService.setTarefas(JSON.stringify(tarefa));
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
  form.status = '';
};

const deleteTarefa = async () => {
  try {
    const response = await TarefaService.deletTarefa(7);
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
