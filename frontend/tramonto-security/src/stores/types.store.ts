import { defineStore } from 'pinia';
import { Ref, ref } from 'vue';
import TestTypeService from 'src/services/testType.service';
import { useQuasar } from 'quasar';
import TestTypeDto from 'src/services/dtos/TestType.dto';

export const useTypeStore = defineStore('types', () => {
  // state
  const testTypes: Ref<TestTypeDto[]> = ref([]);
  const $q = useQuasar();
  // actions
  const findAllTypes = () => {
    $q.loading.show();
    TestTypeService.findAll().then((response) => {
      testTypes.value = response.data;
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
    testTypes,
    findAllTypes,
  };
});
