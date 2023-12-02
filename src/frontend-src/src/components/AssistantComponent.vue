<script setup>
import {onMounted, ref} from 'vue';
import EventoService from "@/service/EventoService";
import AssistantService from "@/service/AssistantService";

const eventos = ref([])
const userMessage = ref('');
const chatMessages = ref([]);
const outputs = ref([
  "PIMPIMLANDIA: An event with the title 'PIMPIM' is scheduled, but the date seems to be undefined (01/01/0001). It might be necessary to check and confirm the date to ensure participation.",
  "Potato Here: There is an event called 'Potato' scheduled for 31/12/2023. It would be interesting to review the description to better understand the purpose of the event.",
  "Weather in Ubatuba: Two events, 'test1' and 'test2,' are scheduled in Ubatuba from 18/11/2023 to 20/11/2023. It could be an opportunity to optimize time and combine activities on these dates.",
  "Meeting in Caraguatatuba: 'hello' and 'beach' are events in Caraguatatuba with close dates (19/11/2023 to 21/11/2023). It might be efficient to plan these activities together to save time.",
  "Rain in São José dos Campos: 'rain' is scheduled for 21/11/2023 to 22/11/2023 in São José dos Campos. Make sure to be prepared for the weather conditions.",
  "Infinite Test in São José dos Campos: An event called 'Infinite Test' is scheduled for 19/12/2023 in São José dos Campos. Check if there are necessary preparations for that day.",
  "Greetings from Limeira: 'Hello World' is scheduled for 21/12/2024 in Limeira. It might be an opportunity for networking or exploring the region.",
  "Event in Americanas: 'HERE WE GO' is scheduled for 21/12/2024 in Americanas. Make sure to understand the purpose of this event and prepare accordingly."
]);
const sendMessage = () => {
  const trimmedMessage = userMessage.value.trim();
  if (trimmedMessage !== '') {
    chatMessages.value.push({ text: trimmedMessage, type: 'sent' });
    userMessage.value = '';

    setTimeout(() => {
      chatMessages.value.push({ text: "Hi matte, I'm here to help you to manage your time! " +
            "As a new born AI, for now, I can only generate a resume of your events!", type: 'received' });

      const outputText = outputs.value.join('\n');

      setTimeout(() => {
        chatMessages.value.push({ text: outputText, type: 'received' });

        setTimeout(() => {
          chatMessages.value.push({ text: "Keep using so I can get to know you better and offer a more personalized experience", type: 'received' });
        }, 500);
      }, 500);
    }, 500);
  }
};

onMounted(async () => {
  try {
    const response = await EventoService.getEventos();
    for (const i of response.data){
      console.log(i);
      eventos.value.push(i);
    }
  } catch(error) {
    console.log("Erro getEventos: ", error);
  }
});
</script>

<template>
  <div class="assistant">
    <header class="header"></header>
    <div class="chat">
      <div v-if="chatMessages.length === 0" class="message placeholder">
        Welcome to your personal assistant OrganizAI!!<br>
        Type a message to generate a resume of your activities and <br>
        Let me help you to become more productive!
      </div>
      <div v-for="message in chatMessages" :key="message.id" :class="[message.type, 'message']">
        <!-- Use a span with white-space: pre-line; to preserve line breaks -->
        <span style="white-space: pre-line;">{{ message.text }}</span>
      </div>
    </div>
    <div class="user-input">
      <input v-model="userMessage" @keyup.enter="sendMessage" placeholder="Type 'Hello' to start" />
      <div class="btn">
        <img class="enter" src="../assets/enter.png" alt="enter" @click="sendMessage" />
      </div>
    </div>
    <img class="circles" src="../assets/circles.png" alt="Circles" />
    <img class="dots" src="../assets/dots.png" alt="Dots" />
  </div>
</template>

<style scoped>
.assistant {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  height: 100%;
  position: relative;
}
.header {
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 7vh;
  background: linear-gradient(to left, #3d5a80, #293241);
  margin-bottom: 0;
}
.chat {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 79%;
  height: 89vh;
  border-radius: 30px 30px 5px 5px;
  margin-bottom: 1vh;
  background: linear-gradient(to bottom right, #E0FBFC, #E0FBFC);
  overflow: auto;
  overflow-x: hidden;
  margin-top: 3vh;
  z-index: 100;
}

.message{
  padding: 1% 5%;
  margin: 2vh;
  border-radius: 10px;
  font-family: Roboto, sans-serif;
  font-weight: 500;
  font-size: 150%;
  text-align: justify;
  color: #293241;
  z-index: 1000;
}
.sent {
  background-color: #3d5a80;
  color: white;
  align-self: flex-end;
  padding-left: 20vh;
}

.received {
  background-color: #e0e0e0;
  align-self: flex-start;
}

.user-input {
  display: flex;
  width: 78%;
}
.user-input {
  position: fixed;
  bottom: 2vh;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 2px solid #3D5A80;
  border-radius: 50px;
  padding-left: 1ch;
  padding-right: 1ch;
  font-family: Roboto, sans-serif;
  font-weight: 500;
  font-size: 120%;
  text-align: center;
  color: #293241;
  background: linear-gradient(to bottom right, #E0FBFC, #E0FBFC);
  z-index: 10000;
}
input {
  border: none;
  width: 100vw;
  height: 4vh;
  border-radius: 5rem;
  overflow: hidden;
  font-family: Roboto, sans-serif;
  font-weight: 500;
  font-size: 130%;
  text-align: center;
  color: #293241;
  margin-right: 1vw;
  background: none;
}
input:focus {
  outline: none;
}
input::placeholder {
  color: #3D5A80;
  opacity: 0.4;
}
.btn {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 3vw;
  height: 3vh;
  background: #3D5A80;
  border-radius: 50px;
  transition: 0.2s;
}
.enter {
  max-width: 2vw;
  max-height: 2vh;
  cursor: pointer;
}
.btn:active {
  transform: scale(0.9);
  background: transparent;
  border: 2px solid #ee6c4d;
}
.circles {
  position: absolute;
  top: 10vh;
  left: 0;
  opacity: 0.6;
  filter: blur(1px);
}
.dots {
  position: absolute;
  top: 20vh;
  right: -10vw;
  opacity: 0.6;
  filter: blur(1px);
}
.message.placeholder {
  margin-top: 30vh;
  font-style: italic;
  font-family: Roboto, sans-serif;
  font-weight: 300;
  font-size: 190%;
  text-align: center;
  transform: translate(0%, 50%);
  color: #808080;
}
::-webkit-scrollbar-track {
  background-color: transparent;
}
</style>
