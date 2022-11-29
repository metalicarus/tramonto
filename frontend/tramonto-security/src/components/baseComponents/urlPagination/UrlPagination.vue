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
import { useRouter } from 'vue-router';
import { ref } from 'vue';

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
    const router = useRouter();
    function setCurrentPage(newPage: number): void {
      page.value = newPage;
      router.push({
        query: {
          page: newPage - 1,
          perPage: router.currentRoute.value.query.perPage,
          sortBy: router.currentRoute.value.query.sortBy,
          direction: router.currentRoute.value.query.direction,
        },
      });
      ctx.emit('update', newPage - 1);
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
