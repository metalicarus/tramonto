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
  getters: {
    isValidVector: (state) => (index: number) => state.test.vectors[index].title.length > 0
      && state.test.vectors[index].description.length > 0
      && state.test.vectors[index].expectedResults.length > 0
      && state.test.vectors[index].resultsObtained.length > 0,
  },
  actions: {
    async paginate() {
      const paginationStore = usePaginationStore();
      Loading.show();
      paginationStore.setPageDisable(true);
      paginationStore.setFilterDisable(true);
      try {
        const response = await TestService.paginate(paginationStore.filter, paginationStore.page - 1, this.pagination.perPage, 'created_at', 'DESC');
        this.rows = response.data.content;
        this.pagination.totalItems = response.data.totalItems - response.data.content.length;
        this.pagination.totalPages = Math.ceil(response.data.totalItems / this.pagination.perPage);
        paginationStore.setPageDisable(false);
        paginationStore.setFilterDisable(false);
      } catch {
        Notify.create({
          message: "The server couldn't process your request",
          type: 'negative',
        });
      } finally {
        Loading.hide();
      }
    },
    async findById(uuid: string) {
      Loading.show();
      try {
        const response = await TestService.findById(uuid);
        this.test = response.data;
      } catch {
        Notify.create({
          message: "The server couldn't process your request",
          type: 'negative',
        });
      } finally {
        Loading.hide();
      }
    },
    async update() {
      Loading.show();
      try {
        const response = await TestService.update(this.test);
        if (response.status === 201 || response.status === 200) {
          Notify.create({
            message: 'Test updated successfully!',
            type: 'positive',
          });
        }
        this.router.push({
          path: '/tests',
        });
      } catch {
        Notify.create({
          message: "The server couldn't process your request",
          type: 'negative',
        });
      } finally {
        Loading.hide();
      }
    },
    async save() {
      Loading.show();
      try {
        const response = await TestService.save(this.test);
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
      } catch {
        Notify.create({
          message: "The server couldn't process your request",
          type: 'negative',
        });
      } finally {
        Loading.hide();
      }
    },
    async persistVector(index: number) {
      Loading.show();
      if (this.isValidVector(index)) {
        try {
          const response = await TestService.addVector(this.test.id, this.test.vectors[index]);
          if (response.status === 201 || response.status === 200) {
            this.test.vectors[index] = response.data;
            Notify.create({
              message: 'Vector added successfully!',
              type: 'positive',
            });
          }
        } catch {
          Notify.create({
            message: 'The server couldn\'t process your request',
            type: 'negative',
          });
        } finally {
          Loading.hide();
        }
      } else {
        Loading.hide();
        Notify.create({
          message: 'Please, insert informations in vector',
          type: 'negative',
        });
      }
    },
    async changeVectorStatus(vectorId: string, approval: string) {
      try {
        Loading.show();
        if (!vectorId || !approval) {
          throw new Error('Invalid input parameters');
        }
        const response = await TestService.changeVectorStatus(vectorId, approval);
        if (response.status === 200 || response.status === 201) {
          Notify.create({
            message: 'Vector approval changed successfully',
            type: 'positive',
          });
        }
      } catch (error) {
        Notify.create({
          message: 'An error occurred while processing your request',
          type: 'negative',
        });
      } finally {
        Loading.hide();
      }
    },
    addVector() {
      const vector = new TestVector();
      vector.belongsToCurrentUser = true;
      this.test.vectors.push(vector);
    },
    addObjective() {
      this.test.objectives.push(new TestObjective());
    },
    removeObjective(index: number): void {
      this.test.objectives = this.test.objectives.filter((x, y) => index !== y);
    },
  },
});
