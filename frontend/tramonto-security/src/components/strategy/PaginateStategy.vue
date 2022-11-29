<template>
  <q-table
    title="Strategies"
    color="primary"
    :columns="columns"
    :row-key="rowKey"
    :loading="loading"
    :rows="rows"
    :rows-per-page-options="[5,10,15]"
    :hide-pagination="true"
  >
    <template v-slot:top-right>
      <url-filter />
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
  </q-table>
  <url-pagination
    :total-pages="pagination.totalItems"
    @update="updatePage"
  />
</template>
<script lang="ts">

import { Columns, RowKey } from 'components/strategy/table/StrategyTableDefine';
import { onMounted, ref } from 'vue';
import StrategySerivce from 'src/services/strategy.serivce';
import UrlPagination from 'components/baseComponents/urlPagination/UrlPagination.vue';
import UrlFilter from 'components/baseComponents/urlFilter/UrlFilter.vue';

export default {
  components: { UrlFilter, UrlPagination },
  setup() {
    const columns = Columns;
    const rowKey = ref(RowKey);
    const loading = ref(true);
    const rows = ref([]);
    const current = ref(3);
    const pagination = ref({
      totalItems: 11,
      page: 0,
      perPage: 10,
      sortBy: '',
      direction: '',
    });
    function refresh(): void {
      loading.value = true;
      StrategySerivce.paginate(pagination.value.page, pagination.value.perPage, '', '').then((response) => {
        rows.value = response.data.content;
        loading.value = false;
        pagination.value.totalItems = response.data.totalItems - response.data.content.length;
      })
        .catch((error) => {
          console.log(error);
        });
    }
    function updatePage(page: number): void {
      pagination.value.page = page;
      refresh();
    }
    onMounted(() => {
      refresh();
    });
    return {
      columns,
      rowKey,
      loading,
      rows,
      current,
      pagination,
      updatePage,
    };
  },
};
</script>
<style scoped>

</style>
