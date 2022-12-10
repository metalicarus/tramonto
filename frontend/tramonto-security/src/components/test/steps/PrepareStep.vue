<template>
  <div class="q-gutter-md">
    <div class="row q-gutter-md">
      <div class="col-12 q-pa-sm rounded-borders">
        <q-list bordered
                padding>
          <q-expansion-item expand-separator label="Strategies">
            <q-item v-ripple
                    clickable
                    v-for="(item, index) in copyStrategies"
                    :key="index"
            >
              <q-item-section side top>
                <q-checkbox v-model="item.check"
                            @update:model-value="selectStrategy"
                />
              </q-item-section>
              <q-item-section>
                <q-item-label>{{ item.strategy }}</q-item-label>
                <q-item-label caption> {{ item.description }} </q-item-label>
              </q-item-section>
            </q-item>
          </q-expansion-item>
          <q-expansion-item expand-separator label="Tools">
            <q-item v-ripple
                    clickable
                    v-for="(item, index) in copyTools"
                    :key="index"
            >
              <q-item-section side top>
                <q-checkbox v-model="item.check"
                            @update:model-value="selectTool"
                />
              </q-item-section>
              <q-item-section>
                <q-item-label>{{ item.tool }}</q-item-label>
                <q-item-label caption> {{ item.description }} </q-item-label>
              </q-item-section>
            </q-item>
          </q-expansion-item>
        </q-list>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { onMounted, ref } from 'vue';

export default {
  name: 'PrepareStep',
  props: {
    strategies: {
      type: Array,
      required: true,
    },
    tools: {
      type: Array,
      required: true,
    },
  },
  setup(props: any, ctx: any) {
    const selectedStrategies = ref([]);
    const selectedTools = ref([]);

    const copyStrategies = ref(props.strategies);
    const copyTools = ref(props.tools);

    function setFalseInTools() {
      copyTools.value.forEach((x: { check: boolean; }) => {
        x.check = false;
      });
    }
    function setFalseInStrategies() {
      copyStrategies.value.forEach((x: { check: boolean; }) => {
        x.check = false;
      });
    }
    function selectTool() {
      ctx.emit('update-tools', copyTools.value.filter((x: { check: boolean; }) => x.check));
    }
    function selectStrategy() {
      ctx.emit('update-strategies', copyStrategies.value.filter((x: { check: boolean; }) => x.check));
    }
    onMounted(() => {
      setFalseInStrategies();
      setFalseInTools();
    });
    return {
      selectedStrategies,
      selectedTools,
      copyStrategies,
      copyTools,
      selectStrategy,
      selectTool,
    };
  },
};
</script>

<style scoped>

</style>
