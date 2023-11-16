import axios from 'axios';

const EVENTS_API_BASE_URL = 'http://localhost:8080/eventos';

class EventoService{

    getEventos(){
        return axios.get(EVENTS_API_BASE_URL);
    }
setEventos(event){
        return axios.post(EVENTS_API_BASE_URL + "/criar", event, {
            headers: {
                'Content-Type': 'application/json',
            },
        });
    }
    deletEvento(id) {
        return axios.delete(EVENTS_API_BASE_URL + "/delete/" + id);
    }
}

export default new EventoService();