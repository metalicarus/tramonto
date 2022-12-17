import { defineStore } from 'pinia';
import { useQuasar } from 'quasar';
import ChecklistService from 'src/services/Checklist.service';
import { ref, Ref } from 'vue';
import { Checklist } from 'stores/dtos/Checklist.dto';

export const useChecklistStore = defineStore('checklists', () => {
  // state
  const $q = useQuasar();
  const checklists: Ref<Checklist[]> = ref([]);
  // actions
  const findAllChecklists = () => {
    $q.loading.show();
    ChecklistService.findAll().then((response) => {
      checklists.value = response.data;
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
    checklists,
    findAllChecklists,
  };
});
