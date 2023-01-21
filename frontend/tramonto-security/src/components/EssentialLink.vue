<template>
  <q-item :href="essential.link" clickable tag="a">
    <q-item-section v-if="essential.icon" avatar>
      <q-icon :name="essential.icon"/>
    </q-item-section>
    <q-item-section>
      <q-item-label>{{ essential.title }}</q-item-label>
      <q-item-label caption>{{ essential.caption }}</q-item-label>
    </q-item-section>
  </q-item>
</template>

<script lang="ts" setup>
import { PropType } from 'vue';
import EssentialLink from 'components/EssentialLink';

defineProps({
  essential: {
    type: Object as PropType<typeof EssentialLink>,
    required: true,
  },
});

</script>
