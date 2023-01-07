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
      >
      </q-btn>
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <paginate-test :label="label" :edit-route="editRoute" />
    </q-card>
  </q-page>
</template>

<script>
import {
  ANCHOR_PAGE, TESTS_ICON, TESTS_PAGINATE, TESTS_SAVE,
} from '../../consts/RoutesConsts';
import { TESTS } from '../../consts/LabelsConsts';
import PaginateTest from '../../components/test/PaginateTest.vue';

export default {
  name: 'IndexPage',
  components: { PaginateTest },
  setup() {
    return {
      icon: TESTS_ICON,
      label: TESTS,
      mainRoute: TESTS_PAGINATE,
      anchorRoute: ANCHOR_PAGE,
      editRoute: TESTS_SAVE,
    };
  },
};
</script>

<style scoped>

</style>
