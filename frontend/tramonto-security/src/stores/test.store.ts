import { defineStore } from 'pinia';
import TestPaginationDto from 'stores/dtos/TestPagination.dto';
import TestTableDefinition from 'stores/TestTableDefinition';
import { Loading, Notify, QTableProps } from 'quasar';
import Test, { TestVector } from 'components/test/Test';
import TestDto from 'src/services/dtos/Test.dto';
import { usePaginationStore } from 'stores/paginate.store';
import TestService from 'src/services/test.service';
import { ref } from 'vue';
import { TestObjective } from 'stores/dtos/TestObjective.dto';

export const useTestStore = defineStore('tests', {
  state: () => ({
    rows: ref<TestPaginationDto[]>([]),
    rowKey: ref<string>(TestTableDefinition.rowKey),
    columns: TestTableDefinition.columns as QTableProps['columns'],
    test: ref<TestDto>(Test),
    pagination: ref({
      filter: '',
      totalItems: 11,
      totalPages: 1,
      page: 0,
      perPage: 10,
      sortBy: '',
      direction: '',
    }),
  }),
  actions: {
    paginate() {
      const $paginationStore = usePaginationStore();
      Loading.show();
      $paginationStore.setPageDisable(true);
      $paginationStore.setFilterDisable(true);
      TestService.paginate($paginationStore.filter, $paginationStore.page - 1, this.pagination.perPage, 'created_at', 'DESC')
        .then((response) => {
          this.rows = response.data.content;
          this.pagination.totalItems = response.data.totalItems - response.data.content.length;
          this.pagination.totalPages = Math.ceil(response.data.totalItems
            / this.pagination.perPage);
          $paginationStore.setPageDisable(false);
          $paginationStore.setFilterDisable(false);
        })
        .catch(() => {
          Notify.create({
            message: 'The server couldn\'t process your request',
            type: 'negative',
          });
        })
        .finally(() => {
          Loading.hide();
        });
    },
    async findById(uuid: string) {
      Loading.show();
      await TestService.findById(uuid)
        .then((response) => {
          this.test = response.data;
        })
        .catch(() => {
          Notify.create({
            message: 'The server couldn\'t process your request',
            type: 'negative',
          });
        })
        .finally(() => {
          Loading.hide();
        });
    },
    update() {
      Loading.show();
      TestService.update(this.test)
        .then((response) => {
          if (response.status === 201 || response.status === 200) {
            Notify.create({
              message: 'Test updated successfully!',
              type: 'positive',
            });
          }
          this.router.push({
            path: '/tests',
          });
        })
        .catch(() => {
          Notify.create({
            message: 'The server couldn\'t process your request',
            type: 'negative',
          });
        })
        .finally(() => {
          Loading.hide();
        });
    },
    async save() {
      Loading.show();
      TestService.save(this.test)
        .then((response) => {
          if (response.status === 201 || response.status === 200) {
            Notify.create({
              message: 'Test created successfully!',
              type: 'positive',
            });
          }
          this.router.push({
            path: '/tests/save',
            query: { uuid: response.data.id },
          });
        })
        .catch(() => {
          Notify.create({
            message: 'The server couldn\'t process your request',
            type: 'negative',
          });
        })
        .finally(() => {
          Loading.hide();
        });
    },
    persistVector(index: number) {
      Loading.show();
      TestService.addVector(this.test.id, this.test.vectors[index])
        .then((response) => {
          if (response.status === 201 || response.status === 200) {
            this.test.vectors[index] = response.data;
            Notify.create({
              message: 'Vector added successfully!',
              type: 'positive',
            });
          }
        })
        .catch(() => {
          Notify.create({
            message: 'The server couldn\'t process your request',
            type: 'negative',
          });
        })
        .finally(() => {
          Loading.hide();
        });
    },
    addVector() {
      this.test.vectors.push(new TestVector());
    },
    addObjective() {
      this.test.objectives.push(new TestObjective());
    },
    removeObjective(index: number): void {
      this.test.objectives = this.test.objectives.filter((x, y) => index !== y);
    },
  },
});
