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
      <paginate-vector-category :edit-route="editRoute" :label="label" />
    </q-card>
  </q-page>
</template>

<script>
import { useRoute } from 'vue-router';

import PaginateVectorCategory from 'components/vectorCategory/PaginateVectorCategory.vue';
import {
  ANCHOR_PAGE,
  VECTORS_CATEGORIES_PAGINATE, VECTORS_CATEGORIES_SAVE,
} from '../../consts/RoutesConsts';

export default {
  name: 'IndexPage',
  components: { PaginateVectorCategory },
  setup() {
    const icon = 'fa fa-vector-square';
    const label = 'Vector Categories';
    const anchorRoute = ANCHOR_PAGE;
    const mainRoute = VECTORS_CATEGORIES_PAGINATE;
    const editRoute = VECTORS_CATEGORIES_SAVE;
    const router = useRoute();
    return {
      icon,
      anchorRoute,
      label,
      mainRoute,
      editRoute,
      router,
    };
  },
};
</script>

<style scoped>

</style>
