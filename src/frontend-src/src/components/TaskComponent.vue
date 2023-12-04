<script setup>
import { ref, getCurrentInstance, onMounted, toRefs } from 'vue';
import TarefaService from "@/service/TarefaService";

const { emit } = getCurrentInstance();
const props = defineProps(['tarefa']);
const form = ref({
  titulo: '',
  descricao: '',
  status: '',
});

const submitTask = async () => {
  try {
    const response = await TarefaService.setTarefas(JSON.stringify(form.value));
    console.log(response);
    if (response) {
      alert("Task Created!!");
      emit("success");
    } else {
      alert("Error");
    }
  } catch (error) {
    console.error("Erro: ", error);
  }

  form.titulo = '';
  form.descricao = '';
  form.status = '';
};

const close = () => {
  emit("close");
};

const deleteTask = async (id) => {
  if (id) {
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
  } else {
    alert("Cannot delete. Task not created yet.");
  }
};

onMounted(() => {
  // Preencher os campos do formulário com as informações da tarefa, se disponíveis
  if (props.tarefa) {
    console.log(props.tarefa);
    form.value.titulo = props.tarefa.titulo || '';
    form.value.descricao = props.tarefa.descricao || '';
    form.value.data_inicio = props.tarefa.status || '';
  }
});
</script>

<template>
  <div id="task-form">
    <h2>Cadastro de Tarefa</h2>
    <form id="tarefaForm" class="custom-login-form" @submit.prevent="submitTask">
      <label for="titulo">Título:</label>
      <input type="text" v-model="form.titulo" class="titulo" required><br>

      <label for="descricao">Descrição:</label>
      <input type="text" v-model="form.descricao" class="descricao" required><br>

      <label for="status">Status:</label>
      <input type="text" v-model="form.status" class="status" required><br>

      <button type="submit" class="save">Save</button>
      <button type="button" class="custom-button" @click="close">Close</button>
    </form>
    <button @click="deleteTask(tarefa.id)">Delete</button>
  </div>
</template>
