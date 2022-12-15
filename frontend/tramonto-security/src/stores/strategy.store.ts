import { defineStore } from 'pinia';
import StrategySerivce from 'src/services/strategy.serivce';
import { useQuasar } from 'quasar';
import { ref, Ref } from 'vue';
import StrategyInputDto from 'src/services/dtos/StrategyInput.dto';

export const useStrategyStore = defineStore('strategies', () => {
  // state
  const $q = useQuasar();
  const strategies: Ref<StrategyInputDto[]> = ref([]);
  // actions
  const findAllStrategies = () => {
    $q.loading.show();
    StrategySerivce.findAll().then((response) => {
      strategies.value = response.data;
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
    strategies,
    findAllStrategies,
  };
});
