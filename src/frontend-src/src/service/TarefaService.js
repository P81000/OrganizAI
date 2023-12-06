import axios from 'axios';

const TASKS_API_BASE_URL = 'http://localhost:8080/eventos/tarefas';

class TarefaService{

    getTarefas(){
        return axios.get(TASKS_API_BASE_URL);
    }
    setTarefas(task, idEvento){
        return axios.post(TASKS_API_BASE_URL + "/criar/" + idEvento, task, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
    }
    deleteTarefa(id) {
        return axios.delete(TASKS_API_BASE_URL + "/delete/" + id);
    }
}

export default new TarefaService();