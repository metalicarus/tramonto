<template>
  <q-page  class="q-ma-lg">
    <div class="q-pa-md q-gutter-sm">
      <q-breadcrumbs>
        <q-breadcrumbs-el icon="home" :to="anchorRoute"/>
        <q-breadcrumbs-el :icon="strategyIcon"
                          label="Strategies"
                          :to="mainRoute"
        />
      </q-breadcrumbs>
    </div>
    <div class="q-pa-sm">
      <q-btn outline
             color="primary"
             :icon-right="strategyIcon"
             label="New"
             @click="this.$router.push('/strategies/save')"
      >
      </q-btn>
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <paginate-stategy :edit-route="editRoute" />
    </q-card>
  </q-page>
</template>

<script lang="ts">
import PaginateStategy from 'components/strategy/PaginateStategy.vue';
import { useRoute } from 'vue-router';
import { ANCHOR_PAGE, STRATEGIES_PAGINATE, STRATEGIES_SAVE } from 'src/consts/RoutesConsts';

export default {
  components: { PaginateStategy },
  setup() {
    const strategyIcon = 'far fa-chess-knight';
    const anchorRoute = ANCHOR_PAGE;
    const mainRoute = STRATEGIES_PAGINATE;
    const editRoute = STRATEGIES_SAVE;
    const router = useRoute();
    return {
      strategyIcon,
      anchorRoute,
      mainRoute,
      editRoute,
      router,
    };
  },
};
</script>

<style scoped>

</style>
