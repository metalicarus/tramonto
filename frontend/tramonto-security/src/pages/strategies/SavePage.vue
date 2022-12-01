<template>
  <q-page class="q-ma-lg">
    <div class="q-pa-md q-gutter-sm">
      <q-breadcrumbs>
        <q-breadcrumbs-el icon="home"
                          :to="anchorPage"/>
        <q-breadcrumbs-el icon="far fa-chess-knight"
                          :label="label"
                          :to="previousRoute"
        />
        <q-breadcrumbs-el icon="fa fa-circle-plus" label="Create"/>
      </q-breadcrumbs>
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <add-strategy :previous-route="previousRoute" :uuid="uuid"/>
    </q-card>
  </q-page>
</template>
<script lang="ts">
import AddStrategy from 'components/strategy/AddStrategy.vue';
import { ANCHOR_PAGE, STRATEGIES_PAGINATE } from 'src/consts/RoutesConsts';
import { STRATEGIES } from 'src/consts/LabelsConsts';
import { useRouter } from 'vue-router';

export default {
  components: { AddStrategy },
  setup() {
    const router = useRouter();
    const { uuid } = router.currentRoute.value.query;
    const previousRoute = STRATEGIES_PAGINATE;
    const anchorPage = ANCHOR_PAGE;
    const label = STRATEGIES;
    return {
      previousRoute,
      anchorPage,
      label,
      uuid,
    };
  },
};
</script>
