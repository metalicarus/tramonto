<template>
  <q-form flat @submit="save">
      <q-tabs
        v-model="tab"
        dense
        active-color="primary"
        indicator-color="primary"
        align="justify"
      >
        <q-tab name="tests" label="Testes" />
        <q-tab name="annotations" label="Anotações" />
      </q-tabs>
      <q-tab-panels v-model="tab" animated>
        <q-tab-panel name="tests">
          <q-stepper :model-value="step" active-color="primary" :header-nav="false" animated flat>
            <q-step
              :name="1"
              title="Adequacy"
              icon="fas fa-sliders-h"
            >
              <adequacy-step :model="test"
                             :approaches="approachStore.approaches"
                             :aggression="aggressionStore.aggressions"
                             :types="typeStore.testTypes"
              />
            </q-step>
            <q-step
              :name="2"
              title="Verificação"
              icon="fas fa-check-circle"
              active-icon="edit"
            >
              <check-step v-if="checklistStore.checklists.length > 0"
                          :checklists="checklistStore.checklists"
                          :types="['REQUIRED', 'RELATED', 'CUSTOMIZED']"
                          @update="test.checklists = $event"
              />
            </q-step>
            <q-step
              :name="3"
              title="Prepare"
              icon="fas fa-cogs"
              active-icon="edit"
            >
              <prepare-step v-if="strategyStore.strategies.length > 0
                            && toolStore.testTools.length > 0"
                            :strategies="strategyStore.strategies"
                            :tools="toolStore.testTools"
                            @update-strategies="test.strategies = $event"
                            @update-tools="test.tools = $event"
              />
            </q-step>
            <q-step
              :name="4"
              title="Execution"
              icon="fas fa-hourglass-half"
              active-icon="edit"
            >
              <execution-step v-if="categoryStore.vectors.length > 0"
                              :model="test"
                              :vectors="categoryStore.vectors"
              />
            </q-step>
          </q-stepper>
        </q-tab-panel>
      </q-tab-panels>
    <div class="q-pa-md q-gutter-md">
      <div class="row q-gutter-md">
        <q-btn color="primary" label="Submit" type="submit" outline size="large"/>
        <q-btn color="red"
               label="Cancel"
               outline
               size="large"
               @click="this.$router.push(previousRoute);"
        />
      </div>
    </div>
  </q-form>
</template>

<script lang="ts">
import { onMounted, ref } from 'vue';
import AdequacyStep from 'components/test/steps/AdequacyStep.vue';
import CheckStep from 'components/test/steps/CheckStep.vue';
import PrepareStep from 'components/test/steps/PrepareStep.vue';
import ExecutionStep from 'components/test/steps/ExecutionStep.vue';
import { useTestStore } from 'stores/test.store';
import { useApproachStore } from 'stores/approach.store';
import { useAggressionStore } from 'stores/aggression.store';
import { useTypeStore } from 'stores/types.store';
import { useToolStore } from 'stores/tool.store';
import { useStrategyStore } from 'stores/strategy.store';
import { useCategoryStore } from 'stores/category.store';
import { useChecklistStore } from 'stores/checklist.store';

export default {
  name: 'AddTest',
  components: {
    ExecutionStep, PrepareStep, CheckStep, AdequacyStep,
  },
  props: {
    uuid: {
      type: String,
      required: false,
    },
    previousRoute: {
      type: String,
      required: true,
    },
    nextRoute: {
      type: String,
      required: true,
    },
  },
  setup() {
    const $testStore = useTestStore();
    const $approachStore = useApproachStore();
    const $aggressionStore = useAggressionStore();
    const $strategyStore = useStrategyStore();
    const $categoryStore = useCategoryStore();
    const $typeStore = useTypeStore();
    const $toolStore = useToolStore();
    const $checklistStore = useChecklistStore();
    onMounted(() => {
      $strategyStore.findAllStrategies();
      $checklistStore.findAllChecklists();
      $categoryStore.findAllCategoryVectors();
      $typeStore.findAllTypes();
      $toolStore.findAllTools();
    });
    return {
      aggressionStore: $aggressionStore,
      checklistStore: $checklistStore,
      categoryStore: $categoryStore,
      approachStore: $approachStore,
      strategyStore: $strategyStore,
      toolStore: $toolStore,
      typeStore: $typeStore,
      test: $testStore.test,
      step: ref(1),
      tab: ref('tests'),
      save() {
        return 'oi';
      },
    };
  },
};
</script>

<style scoped>

</style>
