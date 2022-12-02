<template>
  <q-table
    :title="label"
    color="primary"
    :columns="columns"
    :row-key="rowKey"
    :loading="loading"
    :rows="rows"
    :hide-pagination="true"
    :rows-per-page-options="[10]"
  >
    <template v-slot:top-right>
      <url-filter @update="updateFilter"/>
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
      </q-td>
    </template>
  </q-table>
  <url-pagination
    :total-pages="pagination.totalItems"
    @update="updatePage"
  />
</template>

<script lang="ts">
import UrlFilter from 'components/baseComponents/urlFilter/UrlFilter.vue';
import UrlPagination from 'components/baseComponents/urlPagination/UrlPagination.vue';
import { Columns, RowKey } from 'components/vectorCategory/table/VectorCategoryTableDefine';
import { onMounted, ref } from 'vue';
import VectorCategoryService from 'src/services/vectorCategory.service';

export default {
  components: { UrlFilter, UrlPagination },
  props: {
    editRoute: {
      type: String,
      required: true,
    },
    label: {
      type: String,
      required: true,
    },
  },
  setup() {
    const columns = Columns;
    const rowKey = ref(RowKey);
    const loading = ref(true);
    const rows = ref([]);
    const pagination = ref({
      filter: '',
      totalItems: 11,
      page: 0,
      perPage: 10,
      sortBy: '',
      direction: '',
    });
    function refresh(): void {
      loading.value = true;
      VectorCategoryService.paginate(pagination.value.filter, pagination.value.page, pagination.value.perPage, '', '').then((response) => {
        rows.value = response.data.content;
        loading.value = false;
        pagination.value.totalItems = response.data.totalItems - response.data.content.length;
      })
        .catch((error) => {
          console.log(error);
        });
    }
    function updateFilter(value: any): void {
      pagination.value.filter = value;
      refresh();
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
      pagination,
      updatePage,
      updateFilter,
    };
  },
};
</script>

<style scoped>

</style>
