<template>
  <Line v-if="loaded" :data="chartData"/>
</template>

<script>
import {Line} from 'vue-chartjs'
import {CategoryScale, Chart as ChartJS, Legend, LinearScale, LineElement, PointElement, Title, Tooltip} from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, PointElement, LineElement, CategoryScale, LinearScale)

export default {
  name: 'LineChart',
  components: {Line},
  data: () => ({
    loaded: false,
    loading: false,
    chartData: null,
  }),
  props: ['city'],
  watch: {
    city: {
      handler: async function (newCity) {
        if (this.loading) {
          return;
        }
        this.loading = true;
        this.loaded = false;
        const response = await fetch('http://localhost:8080/api/weather/' + newCity);
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json();
        this.chartData = {
          labels: data.labels,
          datasets: [
            {
              label: newCity,
              data: data.data
            }
          ]
        };
        this.loaded = true
        this.loading = false;
      },
      immediate: true
    }
  }
}
</script>
<style>
.header {
  display: flex;
  justify-content: space-between;
}
</style>