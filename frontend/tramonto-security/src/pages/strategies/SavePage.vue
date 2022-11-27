<template>
  <q-page class="q-ma-lg">
    <div class="q-pa-md q-gutter-sm">
      <q-breadcrumbs>
        <q-breadcrumbs-el icon="home" to="/"/>
        <q-breadcrumbs-el icon="far fa-chess-knight"
                          label="Strategies"
                          to="/strategies"
        />
        <q-breadcrumbs-el icon="fa fa-circle-plus" label="Create"/>
      </q-breadcrumbs>
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <add-strategy/>
    </q-card>
  </q-page>
</template>
<script lang="ts">
import AddStrategy from 'components/strategy/AddStrategy.vue';

export default {
  components: { AddStrategy },
};
</script>
