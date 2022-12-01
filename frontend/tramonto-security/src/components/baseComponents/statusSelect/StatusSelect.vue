<template>
  <q-select v-model="model"
            :options="options"
            option-value="value"
            option-label="label"
            label="Status"
            outlined
            emit-value
            map-options
            @update:model-value="update"
  />
</template>

<script lang="ts">
import { ref } from 'vue';

export default {
  name: 'StatusSelect',
  props: {
    option: {
      type: [String, Number],
      required: false,
    },
  },
  setup(props: any, ctx: any) {
    const model = ref(props.option);
    function update() {
      ctx.emit('update', model.value);
    }
    return {
      model,
      options: [
        {
          value: 'ACTIVE',
          label: 'Active',
        },
        {
          value: 'INACTIVE',
          label: 'Inactive',
        },
      ],
      update,
    };
  },
};
</script>

<style scoped>

</style>
