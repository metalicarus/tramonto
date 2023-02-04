import { defineStore } from 'pinia';
import { ref } from 'vue';
import VectorCategoryDto from 'src/services/dtos/VectorCategory.dto';
import VectorCategoryService from 'src/services/vectorCategory.service';
import { Loading, Notify } from 'quasar';

export const useCategoryStore = defineStore('categories', {
  state: () => ({
    vectors: ref<VectorCategoryDto[]>([]),
  }),
  actions: {
    async findAllCategoryVectors() {
      Loading.show();
      VectorCategoryService.findAll()
        .then((response) => {
          this.vectors = response.data;
        })
        .catch((error) => {
          Notify.create({
            message: `[ERROR]: ${error.response.data.message}`,
            color: 'negative',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
        })
        .finally(() => {
          Loading.hide();
        });
    },
  },
});
