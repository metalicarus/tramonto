import { defineStore } from 'pinia';
import StrategySerivce from 'src/services/strategy.serivce';
import { Loading, Notify } from 'quasar';
import { ref } from 'vue';
import { StrategyDto } from 'src/services/dtos/StrategyInput.dto';

export const useStrategyStore = defineStore('strategies', {
  state: () => ({
    strategies: ref<StrategyDto[]>([]),
  }),
  actions: {
    async findAllStrategies() {
      Loading.show();
      await StrategySerivce.findAll()
        .then((response) => {
          this.strategies = response.data;
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
