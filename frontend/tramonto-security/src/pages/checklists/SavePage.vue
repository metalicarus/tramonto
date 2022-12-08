<template>
  <q-page class="q-ma-lg">
    <tramonto-breadcrumb
      :options="breadcrumbOptions"
    />
    <q-card bordered class="q-pa-lg" flat>
      <add-checklist :previous-route="previousRoute"
                     :next-route="nextRoute"
                     :uuid="uuid"
      />
    </q-card>
  </q-page>
</template>

<script>
import { useRouter } from 'vue-router';
import TramontoBreadcrumb from '../../components/baseComponents/tramontoBreadcrumb/TramontoBreadcrumb.vue';
import {
  ANCHOR_PAGE, CHECKLISTS_ICON, CHECKLISTS_PAGINATE, CHECKLISTS_SAVE,
} from '../../consts/RoutesConsts';
import { CHECKLISTS } from '../../consts/LabelsConsts';
import AddChecklist from '../../components/checklist/AddChecklist.vue';

export default {
  name: 'SavePage',
  components: { AddChecklist, TramontoBreadcrumb },
  setup() {
    const router = useRouter();
    const { uuid } = router.currentRoute.value.query;
    return {
      previousRoute: CHECKLISTS_PAGINATE,
      nextRoute: CHECKLISTS_SAVE,
      uuid,
      breadcrumbOptions: [{
        icon: 'home',
        to: ANCHOR_PAGE,
      },
      {
        icon: CHECKLISTS_ICON,
        label: CHECKLISTS,
        to: CHECKLISTS_PAGINATE,
      },
      {
        icon: 'fa fa-circle-plus',
        label: 'Create',
      }],
    };
  },
};
</script>

<style scoped>

</style>
