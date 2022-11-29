<template>
  <q-input
    outlined
    dense
    :model-value="text"
    debounce="300"
    placeholder="Search">
    <template v-slot:append>
      <q-icon name="search" />
    </template>
  </q-input>
</template>

<script lang="ts">
import { ref } from 'vue';

export default {
  name: 'UrlFilter',
  setup() {
    const text = ref('');
    return {
      text,
    };
  },
};
</script>

<style scoped>

</style>
