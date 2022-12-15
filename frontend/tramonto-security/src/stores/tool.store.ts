import { defineStore } from 'pinia';
import { ref, Ref } from 'vue';
import ToolService from 'src/services/Tool.service';
import { ToolDto } from 'src/services/dtos/Tool.dto';
import { useQuasar } from 'quasar';

export const useToolStore = defineStore('tools', () => {
  // state
  const testTools: Ref<ToolDto[]> = ref([]);
  const $q = useQuasar();
  // actions
  const findAllTools = () => {
    $q.loading.show();
    ToolService.findAll().then((response) => {
      testTools.value = response.data;
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
    testTools,
    findAllTools,
  };
});
