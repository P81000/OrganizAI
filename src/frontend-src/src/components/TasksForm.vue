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
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';

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

const submitTask = () => {
  // Implemente a lógica para enviar a tarefa ao servidor
  const tarefa = {
    titulo: form.titulo,
    descricao: form.descricao,
    data_inicio: form.data_inicio,
    data_fim: form.data_fim,
    localizacao: form.localizacao,
    id_notificacao: form.id_notificacao,
    id_info_clima: form.id_info_clima,
    id_info_trajeto: form.id_info_trajeto
  };

  // Enviar os dados no formato JSON para o endpoint adequado (substitua com o URL correto)
  let email;
  fetch(`/eventos/${email}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(tarefa)
  })
      .then(response => {
        if (response.ok) {
          // Tarefa cadastrada com sucesso, você pode atualizar a página ou fazer outras ações aqui
          alert("Tarefa cadastrada com sucesso!");

        } else {
          // Tratar a resposta do servidor para erros de cadastro de tarefa
          console.error("Erro de cadastro de tarefa:", response.statusText);
          alert("Erro ao cadastrar a tarefa. Por favor, tente novamente.");
        }
      })
      .catch(error => {
        // Tratar erros de requisição
        console.error("Erro na requisição:", error);
        alert("Erro ao cadastrar a tarefa. Por favor, tente novamente.");
      });

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
</script>
