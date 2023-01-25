<template>
  <q-page class="q-ma-lg">
    <tramonto-breadcrumb
      :options="breadcrumbOptions"
    />
    <q-card bordered class="q-pa-lg" flat>
      <add-test :next-route="'/'"
                :uuid="uuid === undefined ? '' : uuid"
                :previous-route="'/'"
      />
    </q-card>
  </q-page>
</template>

<script lang="ts">
import { ANCHOR_PAGE, TESTS_ICON } from 'src/consts/RoutesConsts';
import { TESTS } from 'src/consts/LabelsConsts';
import AddTest from 'components/test/AddTest.vue';
import { useRouter } from 'vue-router';
import TramontoBreadcrumb from '../../components/baseComponents/tramontoBreadcrumb/TramontoBreadcrumb.vue';

export default {
  name: 'SavePage',
  components: { AddTest, TramontoBreadcrumb },
  setup() {
    const router = useRouter();
    const { uuid } = router.currentRoute.value.query;
    return {
      uuid,
      breadcrumbOptions: [
        {
          icon: 'home',
          to: ANCHOR_PAGE,
        },
        {
          icon: TESTS_ICON,
          label: TESTS,
          to: '',
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
