<template>
  <q-page  class="q-ma-lg">
    <div class="q-pa-md q-gutter-sm">
      <q-breadcrumbs>
        <q-breadcrumbs-el icon="home" to="/"/>
        <q-breadcrumbs-el icon="far fa-chess-knight"
                          label="Strategies"
                          to="/strategies"
        />
      </q-breadcrumbs>
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <paginate-stategy />
    </q-card>
  </q-page>
</template>

<script lang="ts">
import PaginateStategy from 'components/strategy/PaginateStategy.vue';

export default {
  components: { PaginateStategy },
};
</script>

<style scoped>

</style>
