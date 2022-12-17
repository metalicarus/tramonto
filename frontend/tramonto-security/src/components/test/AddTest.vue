<template>
  <q-form ref="form" flat @submit="testStore.save()">
      <q-tabs
        v-model="tab"
        dense
        active-color="primary"
        indicator-color="primary"
        align="justify"
      >
        <q-tab name="tests" label="Tests" />
        <q-tab name="annotations" label="Notes" />
      </q-tabs>
      <q-tab-panels v-model="tab" animated>
        <q-tab-panel name="tests">
          <q-stepper v-model="step"
                     active-color="primary"
                     header-nav
                     animated
                     error-color="red"
                     flat
                     @before-transition="validateStep"
          >
            <q-step
              :name="steps.step1.name"
              :title="steps.step1.title"
              :icon="steps.step1.icon"
              :error="steps.step1.error"
              :active-icon="steps.step1.activeIcon"
            >
              <adequacy-step :model="test"
                             :approaches="approachStore.approaches"
                             :aggression="aggressionStore.aggressions"
                             :types="typeStore.testTypes"
              />
            </q-step>
            <q-step
              :name="2"
              :title="steps.step2.title"
              :icon="steps.step2.icon"
              :active-icon="steps.step2.activeIcon"
            >
              <check-step v-if="checklistStore.checklists.length > 0"
                          :checklists="checklistStore.checklists"
                          :types="['REQUIRED', 'RELATED', 'CUSTOMIZED']"
                          @update="test.checklists = $event"
              />
            </q-step>
            <q-step
              :name="3"
              :title="steps.step3.title"
              :icon="steps.step3.icon"
              :active-icon="steps.step3.activeIcon"
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
              :title="steps.step4.title"
              :icon="steps.step4.icon"
              :active-icon="steps.step4.activeIcon"
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
import { onMounted, ref } from 'vue';

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
    const steps = ref({
      step1: {
        name: 1,
        title: 'Adequacy',
        icon: 'fas fa-sliders-h',
        activeIcon: 'edit',
        error: false,
      },
      step2: {
        name: 2,
        title: 'Checking',
        icon: 'fas fa-check-circle',
        activeIcon: 'edit',
        error: false,
      },
      step3: {
        name: 3,
        title: 'Prepare',
        icon: 'fas fa-cogs',
        activeIcon: 'edit',
        error: false,
      },
      step4: {
        name: 5,
        title: 'Execution',
        icon: 'fas fa-hourglass-half',
        activeIcon: 'edit',
        error: false,
      },
    });
    const form = ref(null);
    const $testStore = useTestStore();
    const $approachStore = useApproachStore();
    const $aggressionStore = useAggressionStore();
    const $strategyStore = useStrategyStore();
    const $categoryStore = useCategoryStore();
    const $typeStore = useTypeStore();
    const $toolStore = useToolStore();
    const $checklistStore = useChecklistStore();
    const validateStep = (newVal: never, oldVal: never) => {
      form.value.validate().then((response: never) => {
        if (oldVal === steps.value.step1.name) steps.value.step1.error = !response;
        if (oldVal === steps.value.step2.name) steps.value.step2.error = !response;
        if (oldVal === steps.value.step3.name) steps.value.step3.error = !response;
        if (oldVal === steps.value.step4.name) steps.value.step4.error = !response;
      });
    };
    onMounted(() => {
      $strategyStore.findAllStrategies();
      $checklistStore.findAllChecklists();
      $categoryStore.findAllCategoryVectors();
      $typeStore.findAllTypes();
      $toolStore.findAllTools();
    });
    return {
      validateStep,
      steps,
      form,
      aggressionStore: $aggressionStore,
      checklistStore: $checklistStore,
      categoryStore: $categoryStore,
      approachStore: $approachStore,
      strategyStore: $strategyStore,
      testStore: $testStore,
      toolStore: $toolStore,
      typeStore: $typeStore,
      test: $testStore.test,
      step: ref(1),
      tab: ref('tests'),
    };
  },
};
</script>

<style scoped>

</style>
