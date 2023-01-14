import { defineStore } from 'pinia';
import { Ref, ref } from 'vue';
import Test from 'components/test/Test';
import TestService from 'src/services/test.service';
import TestDto from 'src/services/dtos/Test.dto';
import { QTableProps, useQuasar } from 'quasar';
import TestPaginationDto from 'stores/dtos/TestPagination.dto';
import { usePaginationStore } from 'stores/paginate.store';

export const useTestStore = defineStore('tests', () => {
  // state
  const test: Ref<TestDto> = ref(Test);
  const rows: Ref<TestPaginationDto[]> = ref([]);
  const rowKey = 'title';
  const columns: QTableProps['columns'] = [
    {
      name: 'id',
      label: 'ID',
      field: 'id',
      align: 'left',
    },
    {
      name: 'title',
      label: 'Title',
      field: 'title',
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
      format: (val: string) => `${val.split('T')[0].replace(/(\d{4})-(\d{2})-(\d{2}).*/, '$3/$2/$1')} ${val.split('T')[1]}`,
    },
    {
      name: 'updatedAt',
      label: 'Updated At',
      field: 'updatedAt',
      align: 'left',
      format: (val: string) => `${val.split('T')[0].replace(/(\d{4})-(\d{2})-(\d{2}).*/, '$3/$2/$1')} ${val.split('T')[1]}`,
    },
    {
      name: 'actions',
      label: 'Actions',
      field: '',
      align: 'left',
    },
  ];
  const pagination = ref({
    filter: '',
    totalItems: 11,
    totalPages: 1,
    page: 0,
    perPage: 10,
    sortBy: '',
    direction: '',
  });
  const $quasar = useQuasar();
  const $paginationStore = usePaginationStore();
  // actions
  const save = () => {
    TestService.save(test.value).then((response) => {
      console.log('sucesso');
      console.log(response);
    }).catch((response) => {
      console.log('errou');
      console.log(response);
    });
  };
  const paginate = () => {
    $quasar.loading.show();
    $paginationStore.setPageDisable(true);
    $paginationStore.setFilterDisable(true);
    TestService.paginate($paginationStore.filter, $paginationStore.page - 1, pagination.value.perPage, '', '')
      .then((response) => {
        rows.value = response.data.content;
        pagination.value.totalItems = response.data.totalItems - response.data.content.length;
        // eslint-disable-next-line max-len
        pagination.value.totalPages = Math.ceil(response.data.totalItems / pagination.value.perPage);
        $paginationStore.setPageDisable(false);
        $paginationStore.setFilterDisable(false);
      })
      .catch((response) => {
        console.log(response.data);
      })
      .finally(() => {
        $quasar.loading.hide();
      });
  };
  return {
    test,
    columns,
    rowKey,
    pagination,
    rows,
    save,
    paginate,
  };
});
