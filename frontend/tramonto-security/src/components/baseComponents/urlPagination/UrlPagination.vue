<template>
  <div class="row justify-center q-mt-md">
    <q-pagination
      :model-value="page"
      :max="totalPages"
      @update:model-value="setCurrentPage"
      color="primary"
      size="sm"
    />
  </div>
</template>

<script lang="ts">
import { ref } from 'vue';
import QueryStringReplace from 'src/utils/QueryStringReplace.vue';

export default {
  name: 'UrlPagination',
  props: {
    totalPages: {
      type: Number,
      default: 10,
    },
  },
  emits: ['update'],
  setup(props: never, ctx: { emit: (arg0: string, arg1: number) => void; }) {
    const page = ref(1);
    const { replaceParam } = QueryStringReplace.setup();
    function setCurrentPage(newPage: number): void {
      page.value = newPage;
      ctx.emit('update', newPage - 1);
      replaceParam('page', newPage - 1);
    }
    return {
      setCurrentPage,
      page,
    };
  },
};
</script>

<style scoped>

</style>
