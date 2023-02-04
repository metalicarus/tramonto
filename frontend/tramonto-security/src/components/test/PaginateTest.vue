<template>
  <q-table
    :title="props.label"
    color="primary"
    :columns="$store.columns"
    :row-key="$store.$state.rowKey"
    :rows="$store.$state.rows"
    :hide-pagination="true"
    :rows-per-page-options="[10]"
  >
    <template v-slot:top-right>
      <url-filter @update="$store.$state.pagination.filter = $event; refresh()"/>
    </template>
    <template v-slot:loading>
      <q-inner-loading showing color="primary" />
    </template>
    <template v-slot:body-cell-status="props">
      <q-td :props="props">
        <div>
          <q-badge :color="props.value === 'ACTIVE' ? 'green' : 'red'" :label="props.value" />
        </div>
      </q-td>
    </template>
    <template v-slot:body-cell-actions="props">
      <q-td :props="props">
        <q-btn flat
               rounded
               size="sm"
               color="primary"
               icon="edit"
               @click="$router.push({ path: editRoute, query: { uuid: props.row.id }});"
        />
        <q-btn flat
               rounded
               size="sm"
               color="purple"
               icon="bolt"
               @click="$router.push({ path: shareRoute, query: { uuid: props.row.id }});"
        />
        <q-btn flat
               rounded
               size="sm"
               color="red"
               icon="terminal"
               @click="$router.push({ path: executionRoute, query: { uuid: props.row.id }});"
        />
      </q-td>
    </template>
  </q-table>
  <url-pagination
    :total-pages="$store.$state.pagination.totalPages"
    @update="$store.$state.pagination.page = $event; refresh()"
  />
</template>

<script setup lang="ts">

import { useTestStore } from 'stores/test.store';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';
import UrlFilter from '../baseComponents/urlFilter/UrlFilter.vue';
import UrlPagination from '../baseComponents/urlPagination/UrlPagination.vue';

const props = defineProps({
  executionRoute: {
    type: String,
    required: true,
  },
  editRoute: {
    type: String,
    required: true,
  },
  shareRoute: {
    type: String,
    required: true,
  },
  label: {
    type: String,
    required: true,
  },
});
const $store = useTestStore();
const $router = useRouter();
const refresh = () => {
  $store.paginate();
};
const redirect = () => {
  $router.push('/');
};
const setPagination = () => {
  const { query } = $router.currentRoute.value;
  $store.pagination.page = Number(query.page);
};
onMounted(() => {
  setPagination();
  refresh();
});
</script>

<style scoped>

</style>
