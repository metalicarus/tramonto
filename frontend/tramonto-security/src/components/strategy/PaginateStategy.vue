<template>
  <q-table
    title="Strategies"
    :columns="columns"
    color="primary"
    row-key="strategy"
    :loading="loading"
    :rows="rows"
  >
    <template v-slot:loading>
      <q-inner-loading showing color="primary" />
    </template>
  </q-table>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import StrategySerivce from 'src/services/strategy.serivce';

export default defineComponent({
  name: 'PaginateStategy',
  data() {
    return {
      loading: true,
      columns: [
        {
          name: 'id',
          label: 'ID',
          field: 'id',
          align: 'left',
        },
        {
          name: 'strategy',
          label: 'Strategy',
          field: 'strategy',
          align: 'left',
        },
        {
          name: 'status',
          label: 'Status',
          field: 'status',
          align: 'left',
        },
        {
          name: 'createdAt',
          label: 'Created At',
          field: 'createdAt',
          align: 'left',
        },
        {
          name: 'updatedAt',
          label: 'Updated At',
          field: 'updatedAt',
          align: 'left',
        },
      ],
      rows: [],
    };
  },
  created() {
    this.search();
  },
  methods: {
    search() {
      StrategySerivce.paginate().then((response) => {
        this.rows = response.data.content;
        this.loading = false;
      }).catch((error) => {
        console.log(error.response);
      });
    },
  },
});
</script>
<style scoped>

</style>
