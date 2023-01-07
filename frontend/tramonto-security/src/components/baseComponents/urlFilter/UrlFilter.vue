<template>
  <q-input
    outlined
    dense
    v-model="$store.filter"
    debounce="300"
    :disable="$store.disableFilter"
    placeholder="Search">
    <template v-slot:after>
      <q-btn outline @click="setFilter">
        <q-icon name="search" />
      </q-btn>
    </template>
  </q-input>
</template>

<script lang="ts" setup>
import { usePaginationStore } from 'stores/paginate.store';
import { onMounted } from 'vue';

const $store = usePaginationStore();
const emit = defineEmits(['update']);
const setFilter = () => {
  $store.setFilter($store.filter);
  emit('update', $store.filter);
};
onMounted(() => {
  $store.touchFilter();
});
</script>

<style scoped>

</style>
