import { defineStore } from 'pinia';
import { useQuasar } from 'quasar';
import { ref, Ref } from 'vue';
import VectorCategoryDto from 'src/services/dtos/VectorCategory.dto';
import VectorCategoryService from 'src/services/vectorCategory.service';

export const useCategoryStore = defineStore('categories', () => {
  // state
  const $q = useQuasar();
  const vectors: Ref<VectorCategoryDto[]> = ref([]);
  // actions
  const findAllCategoryVectors = () => {
    $q.loading.show();
    VectorCategoryService.findAll().then((response) => {
      vectors.value = response.data;
    })
      .catch((error) => {
        $q.notify({
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
        $q.loading.hide();
      });
  };
  return {
    vectors,
    findAllCategoryVectors,
  };
});
