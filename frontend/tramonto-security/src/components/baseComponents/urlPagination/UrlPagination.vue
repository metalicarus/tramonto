<template>
  <div class="row justify-center q-mt-md">
    <q-pagination
      :model-value="page"
      @update:model-value="setPage"
      :max="totalPages"
      :disable="disable"
      color="primary"
      size="sm"
    />
  </div>
</template>

<script lang="ts">
import { usePaginationStore } from 'stores/paginate.store';
import { onMounted, toRef } from 'vue';

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
    const $store = usePaginationStore();
    const setPage = (value: number) => {
      $store.setPage(value);
      ctx.emit('update', value);
    };
    onMounted(() => {
      $store.touchPage();
    });
    return {
      page: toRef($store, 'page'),
      disable: toRef($store, 'disablePage'),
      setPage,
    };
  },
};
</script>

<style scoped>

</style>
