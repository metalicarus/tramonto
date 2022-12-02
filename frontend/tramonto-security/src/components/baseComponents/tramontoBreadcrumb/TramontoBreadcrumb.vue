<template>
  <div class="q-pa-md q-gutter-sm">
    <q-breadcrumbs>
      <q-breadcrumbs-el
        v-for="item in options"
        v-bind:key="item"
        :icon="item.icon"
        :label="item.label"
        :to="item.to"
      />
    </q-breadcrumbs>
  </div>
</template>

<script lang="ts">

export default {
  name: 'tramonto-breadcrumb',
  props: {
    options: {
      type: Array,
      required: true,
    },
  },
};
</script>

<style scoped>

</style>
