<template>
  <div class="calendar-page">
    <p class="briefing">
      OrganizAI is more than just an agenda!<br />Say goodbye to chaos and
      hello to efficient<br />
      scheduling and task management!
    </p>
    <h1>Calendário</h1>
    <div class="calendar">
      <div class="calendar-header">
        <span>Seg</span>
        <span>Ter</span>
        <span>Qua</span>
        <span>Qui</span>
        <span>Sex</span>
        <span>Sáb</span>
        <span>Dom</span>
      </div>
      <div class="calendar-body">
        <!-- Renderize os dias do calendário aqui -->
        <div
            v-for="day in calendarDays"
            :key="day.date"
            :class="['calendar-day', { 'current-day': isCurrentDay(day.date) }]"
        >
          {{ day.day }}
        </div>
      </div>
    </div>
    <button class="taregabtn" @click="openTaskModal">Cadastrar Tarefa</button>
    <img class="circles" src="../assets/circles.png" alt="Circles" />
    <img class="dots" src="../assets/dots.png" alt="Dots" />
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';


const getDaysInMonth = () => {
  const date = new Date();
  const year = date.getFullYear();
  const month = date.getMonth();
  const daysInMonth = new Date(year, month + 1, 0).getDate();

  const days = [];
  for (let day = 1; day <= daysInMonth; day++) {
    days.push({
      date: new Date(year, month, day),
      day,
    });
  }

  return days;
};

const calendarDays = ref(getDaysInMonth());

const openTaskModal = () => {
  
};

const isCurrentDay = (date) => {
  const today = new Date();
  return (
      date.getDate() === today.getDate() &&
      date.getMonth() === today.getMonth() &&
      date.getFullYear() === today.getFullYear()
  );
};
</script>
<style scoped>

.calendar-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}
.taregabtn {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 3vh;
  margin-top: 10px;
}
.calendar-container {
  text-align: center;
}
.briefing {
  font-family: Roboto,serif;
  font-weight: 800;
  font-size: 40px;
  color: #293241;
  margin-top: 8%;
  text-align: center;
}
.big{

height: 500px;

.calendar {
  border: 1px solid #ccc;
  max-width: 600px; 
  margin-top: 20px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  align-content: center;


}}

.calendar-header {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  background-color: #f0f0f0;
  font-weight: bold;
  padding: 10px;
  text-align: center;


}

.calendar-day {
  border: 1px solid #ccc;
  padding: 12px; 
  text-align: center;
  border-radius: 50%; 
  cursor: pointer;


}

.current-day {
  background-color: #98C1D9; /* Cor de destaque para o dia atual */
  color: white; /* Cor do texto no dia atual */
}

.calendar-body {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
}
.circles {
  position: absolute;
  top: 50%;
  left: 0;
  opacity: 0.5;
}
.dots {
  position: absolute;
  top: -20%;
  right: 0;
  opacity: 0.5;
}
</style>
