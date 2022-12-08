<template>
  <q-page  class="q-ma-lg">
    <div class="q-pa-md q-gutter-sm">
      <q-breadcrumbs>
        <q-breadcrumbs-el icon="home" :to="anchorRoute"/>
        <q-breadcrumbs-el :icon="icon"
                          :label="label"
                          :to="mainRoute"
        />
      </q-breadcrumbs>
    </div>
    <div class="q-pa-sm">
      <q-btn outline
             color="primary"
             :icon-right="icon"
             label="New"
             @click="this.$router.push(editRoute)"
      />
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <paginate-checklist :edit-route="editRoute"
                          :label="label"
      />
    </q-card>
  </q-page>
</template>

<script>
import {
  ANCHOR_PAGE, CHECKLISTS_ICON, CHECKLISTS_PAGINATE, CHECKLISTS_SAVE,
} from '../../consts/RoutesConsts';
import { CHECKLISTS } from '../../consts/LabelsConsts';
import PaginateChecklist from '../../components/checklist/PaginateChecklist.vue';

export default {
  name: 'IndexPage',
  components: { PaginateChecklist },
  setup() {
    return {
      icon: CHECKLISTS_ICON,
      label: CHECKLISTS,
      mainRoute: CHECKLISTS_PAGINATE,
      anchorRoute: ANCHOR_PAGE,
      editRoute: CHECKLISTS_SAVE,
    };
  },
};
</script>

<style scoped>

</style>
