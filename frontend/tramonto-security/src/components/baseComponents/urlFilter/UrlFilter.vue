<template>
  <q-input
    outlined
    dense
    v-model="text"
    debounce="300"
    placeholder="Search">
    <template v-slot:after>
      <q-btn outline @click="setText">
        <q-icon name="search" />
      </q-btn>
    </template>
  </q-input>
</template>

<script lang="ts">
import { ref } from 'vue';
import QueryStringReplace from 'src/utils/QueryStringReplace.vue';

export default {
  name: 'UrlFilter',
  setup(props: any, ctx: any) {
    const text = ref('');
    const { replaceParam } = QueryStringReplace.setup();
    function setText() {
      replaceParam('filter', text.value);
      ctx.emit('update', text.value);
    }
    return {
      text,
      setText,
    };
  },
};
</script>

<style scoped>

</style>
