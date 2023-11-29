<template>
  <div id="cadastroEventoForm">
    <h2>Cadastro de Evento</h2>

    <form id="eventoForm" class="custom-login-form" @submit.prevent="submitEvento">
      <div class="form-row">
        <div class="form-group">
          <label for="titulo">Título:</label>
          <input id="titulo" v-model="form.titulo" class="custom-input" required>
        </div>

        <div class="form-group">
          <label for="descricao">Descrição:</label>
          <textarea id="descricao" v-model="form.descricao" class="custom-input" required></textarea>
        </div>

          <div class="form-column">
            <div class="form-group">
            <label for="dataInicio">Início:</label>
            <input type="datetime-local" v-model="form.data_inicio" class="custom-input" required>
          </div>

          <div class="form-group">
            <label for="dataFim">Fim:</label>
            <input type="datetime-local" v-model="form.data_fim" class="custom-input" required>
          </div>

          <div class="form-group">
            <label for="localizacao">Localização:</label>
            <input type="text" v-model="form.localizacao" class="custom-input" required>
          </div>

          <div class="form-group">
            <label for="idNotificacao">ID de Notificação:</label>
            <input type="text" v-model="form.id_notificacao" class="custom-input">
          </div>
        </div>
      </div>
    </form>
    <!--button @click="createEvento()">Criar Evento</button!-->
    <button @click="deleteEvento()">Deletar Evento</button>
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

const submitEvento = async () => {
  console.log("Form Values Before Submission:", form);

  // Implement the logic to send the event to the server
  const evento = {
    titulo: form.value.titulo,
    descricao: form.value.descricao,
    data_inicio: form.value.data_inicio,
    data_fim: form.value.data_fim,
    localizacao: form.value.localizacao,
    id_notificacao: form.value.id_notificacao,
    id_info_clima: form.value.id_info_clima,
    id_info_trajeto: form.value.id_info_trajeto
  };

  console.log("Evento Object:", evento)
  try {
    console.log(JSON.stringify(evento));
    const response = await EventoService.setEventos(JSON.stringify(evento));
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

const deleteEvento = async () => {
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

<style scoped>
.form-group {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
}

.custom-input {
  width: 75%;
}

label[for="descricao"] {
  display: flex;
  align-items: center;
}

#descricao {
  margin-left: 5px;
}

label[for="titulo"] {
  display: flex;
  align-items: center;
}

#titulo {
  margin-left: 5px;
}

#cadastroEventoForm {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  padding: 15px;
  background-color: #f9f9f9;
  border-radius: 5px;
}

#eventoForm {
  display: grid;
  grid-gap: 10px;
}

.custom-input {
  padding: 10px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.custom-login-form label {
  font-weight: bold;
}

.custom-button {
  margin-top: 10px;
  padding: 10px;
  border-radius: 5px;
  background-color: #007bff;
  color: #fff;
  border: none;
  cursor: pointer;
}

.custom-button:hover {
  background-color: #0056b3;
}

.form-row {
  flex: 1;
  padding: 10px;
  box-sizing: border-box;
}

.form-column {
  flex: 1;
  margin-right: 10px;
}

.form-column:last-child {
  margin-right: 0;
}
</style>