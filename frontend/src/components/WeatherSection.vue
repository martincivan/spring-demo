<script setup>
  import WeatherChart from "@/components/WeatherChart.vue";
  import {ref} from "vue";


  const response = await fetch('http://localhost:8080/api/city/all');
  const cities = await response.json()
  let city = ref(cities[0]);
</script>
<template>
  <div class="header">
    <h1>Weather Data</h1>
    <select v-model="city">
      <option :value="item" v-for="(item, index) in cities" :selected="index === 0">{{item}}</option>
    </select>
  </div>
  <Suspense>
    <WeatherChart :city="city" />
  </Suspense>
</template>