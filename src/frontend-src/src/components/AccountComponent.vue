<script setup>
let i = 0;
const print = () => {
  i++;
  console.log(i);
};
</script>

<template>
  <div>
    <p>bbbbb</p>
    <button @click="print">Press</button>
  </div>
</template>

<style scoped>
p {
  font-family: Roboto;
  font-weight: 800;
  color: #293241;
  font-size: 130%;
  text-align: center;
  letter-spacing: 0;
  line-height: normal;
}
</style>
