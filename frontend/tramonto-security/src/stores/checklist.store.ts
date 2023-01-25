import { defineStore } from 'pinia';
import { Loading, Notify } from 'quasar';
import ChecklistService from 'src/services/Checklist.service';
import { ref } from 'vue';
import { Checklist } from 'stores/dtos/Checklist.dto';

export const useChecklistStore = defineStore('checklists', {
  state: () => ({
    checklists: ref<Checklist[]>([]),
  }),
  actions: {
    async findAllChecklists() {
      Loading.show();
      await ChecklistService.findAll().then((response) => {
        this.checklists = response.data;
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
