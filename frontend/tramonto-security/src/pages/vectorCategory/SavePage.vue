<template>
  <q-page class="q-ma-lg">
    <tramonto-breadcrumb
      :options="breadcrumbOptions"
    />
    <q-card bordered class="q-pa-lg" flat>
      <add-vector-category :next-route="nextRoute" :previous-route="previousRoute" :uuid="uuid" />
    </q-card>
  </q-page>
</template>

<script>
import { useRouter } from 'vue-router';
import AddVectorCategory from '../../components/vectorCategory/AddVectorCategory.vue';
import { ANCHOR_PAGE, VECTORS_CATEGORIES_PAGINATE, VECTORS_CATEGORIES_SAVE } from '../../consts/RoutesConsts';
import { VECTOR_CATEGORIES } from '../../consts/LabelsConsts';
import TramontoBreadcrumb from '../../components/baseComponents/tramontoBreadcrumb/TramontoBreadcrumb.vue';

export default {
  name: 'SavePage',
  components: { TramontoBreadcrumb, AddVectorCategory },
  setup() {
    const router = useRouter();
    const icon = 'fa fa-vector-square';
    const { uuid } = router.currentRoute.value.query;
    const previousRoute = VECTORS_CATEGORIES_PAGINATE;
    const anchorPage = ANCHOR_PAGE;
    const label = VECTOR_CATEGORIES;
    const nextRoute = VECTORS_CATEGORIES_SAVE;
    return {
      previousRoute,
      anchorPage,
      label,
      icon,
      uuid,
      nextRoute,
      breadcrumbOptions: [{
        icon: 'home',
        to: anchorPage,
      },
      {
        icon,
        label,
        to: previousRoute,
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
