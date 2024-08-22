<script setup>
import WeatherSection from "@/components/WeatherSection.vue";
</script>

<template>
  <main>
    <Suspense>
      <WeatherSection />
    </Suspense>
  </main>
</template>

<style scoped>
main {
  width: 100%;
}
</style>
