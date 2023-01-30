<template>
  <q-form ref="form" flat @submit="submit">
    <q-tabs
      v-model="tab"
      active-color="primary"
      align="justify"
      dense
      indicator-color="primary"
    >
      <q-tab label="Tests" name="tests"/>
      <q-tab label="Notes" name="annotations"/>
    </q-tabs>
    <q-tab-panels v-model="tab" animated>
      <q-tab-panel name="tests">
        <q-stepper v-model="step"
                   active-color="primary"
                   animated
                   error-color="red"
                   flat
                   header-nav
                   @before-transition="validateStep"
        >
          <q-step
            :active-icon="steps.step1.activeIcon"
            :done="steps.step1.done"
            :done-color="steps.step1.doneColor"
            :error="steps.step1.error"
            :icon="steps.step1.icon"
            :name="steps.step1.name"
            :title="steps.step1.title"
          >
            <adequacy-step :aggression="$aggressionStore.aggressions"
                           :approaches="$approachStore.approaches"
                           :model="test"
                           :types="$typeStore.testTypes"
            />
          </q-step>
          <q-step
            :active-icon="steps.step2.activeIcon"
            :done="steps.step2.done"
            :done-color="steps.step2.doneColor"
            :error="steps.step2.error"
            :icon="steps.step2.icon"
            :name="steps.step2.name"
            :title="steps.step2.title"
          >
            <check-step v-if="$checklistStore.checklists.length > 0"
                        :checklists="$checklistStore.checklists"
                        :types="['REQUIRED', 'RELATED', 'CUSTOMIZED']"
                        @update="test.checklists = $event"
            />
          </q-step>
          <q-step
            :active-icon="steps.step3.activeIcon"
            :done="steps.step3.done"
            :done-color="steps.step3.doneColor"
            :error="steps.step3.error"
            :icon="steps.step3.icon"
            :name="steps.step3.name"
            :title="steps.step3.title"
          >
            <prepare-step v-if="$strategyStore.strategies.length > 0"
                          :strategies="$strategyStore.strategies"
                          @update-strategies="test.strategies = $event"
                          @update-tools="test.tools = $event"
            />
          </q-step>
          <q-step
            :active-icon="steps.step4.activeIcon"
            :done="steps.step4.done"
            :done-color="steps.step4.doneColor"
            :icon="steps.step4.icon"
            :name="steps.step4.name"
            :title="steps.step4.title"
          >
            <share-step v-if="$authStore.selectableUsers.length > 0"
                        :selectable-users="$authStore.selectableUsers"
                        :selected-users="[]"
                        @update-selected-users="test.testers = $event"
            />
          </q-step>
          <q-step
            :active-icon="steps.step5.activeIcon"
            :disable="test.id === ''"
            :done="steps.step5.done"
            :done-color="steps.step5.doneColor"
            :icon="steps.step5.icon"
            :name="steps.step5.name"
            :title="steps.step5.title"
          >
            <execution-step v-if="$categoryStore.vectors.length > 0"
                            :model="test"
                            :vectors="$categoryStore.vectors"
            />
          </q-step>
        </q-stepper>
      </q-tab-panel>
    </q-tab-panels>
    <div class="q-pa-md q-gutter-md">
      <div class="row q-gutter-md">
        <q-btn color="primary" label="Submit" outline size="large" type="submit"/>
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

<script lang="ts" setup>
import AdequacyStep from 'components/test/steps/AdequacyStep.vue';
import CheckStep from 'components/test/steps/CheckStep.vue';
import PrepareStep from 'components/test/steps/PrepareStep.vue';
import ExecutionStep from 'components/test/steps/ExecutionStep.vue';
import { onMounted, ref } from 'vue';
import { useTestStore } from 'stores/test.store';
import { useApproachStore } from 'stores/approach.store';
import { useAggressionStore } from 'stores/aggression.store';
import { useStrategyStore } from 'stores/strategy.store';
import { useCategoryStore } from 'stores/category.store';
import { useTypeStore } from 'stores/types.store';
import { useChecklistStore } from 'stores/checklist.store';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';
import { TESTS_SHARE } from 'src/consts/RoutesConsts';
import { useAuthenticationStore } from 'stores/authentication.store';
import ShareStep from 'components/test/steps/ShareStep.vue';

const props = defineProps({
  uuid: {
    type: String,
    required: true,
  },
  previousRoute: {
    type: String,
    required: true,
  },
  nextRoute: {
    type: String,
    required: true,
  },
});
const steps = ref({
  step1: {
    name: 1,
    title: 'Adequacy',
    icon: 'fas fa-sliders-h',
    activeIcon: 'edit',
    error: false,
    doneColor: 'green',
    done: false,
  },
  step2: {
    name: 2,
    title: 'Checking',
    icon: 'fas fa-check-circle',
    activeIcon: 'edit',
    error: false,
    doneColor: 'green',
    done: false,
  },
  step3: {
    name: 3,
    title: 'Prepare',
    icon: 'fas fa-cogs',
    activeIcon: 'edit',
    error: false,
    doneColor: 'green',
    done: false,
  },
  step4: {
    name: 4,
    title: 'Sharing',
    icon: 'fas fa-share-nodes',
    activeIcon: 'edit',
    error: false,
    doneColor: 'green',
    done: false,
  },
  step5: {
    name: 5,
    title: 'Execution',
    icon: 'fas fa-hourglass-half',
    activeIcon: 'edit',
    error: false,
    doneColor: 'green',
    done: false,
  },
});
const step = ref(1);
const tab = ref('tests');
const form = ref(null);
const $testStore = useTestStore();
const $approachStore = useApproachStore();
const $aggressionStore = useAggressionStore();
const $strategyStore = useStrategyStore();
const $categoryStore = useCategoryStore();
const $authStore = useAuthenticationStore();
const $typeStore = useTypeStore();
const $checklistStore = useChecklistStore();
const { test } = storeToRefs($testStore);
const $router = useRouter();
const currentRoute = ref($router.currentRoute.value.path);

const validateStep = (newVal: never, oldVal: never) => {
  form.value.validate()
    .then((response: never) => {
      if (oldVal === steps.value.step1.name) {
        steps.value.step1.error = !response;
        steps.value.step1.done = response;
      }
      if (oldVal === steps.value.step2.name) {
        steps.value.step2.error = test.value.checklists.length === 0;
        steps.value.step2.done = test.value.checklists.length > 0;
      }
      if (oldVal === steps.value.step3.name) {
        steps.value.step3.error = test.value.strategies.length === 0;
        steps.value.step3.done = test.value.strategies.length > 0;
      }
      if (oldVal === steps.value.step5.name) steps.value.step5.error = !response;
    });
};
const submit = () => {
  if (props.uuid.length > 0) {
    $testStore.update();
  } else {
    $testStore.save();
  }
};
const setTrueInChecklist = () => {
  test.value.checklists.forEach((ch) => {
    const find = $checklistStore.checklists.find((x) => x.id === ch.id);
    if (find) {
      find.check = true;
    }
  });
};
const setTrueInTesters = () => {
  test.value.testers.forEach((ch) => {
    const find = $authStore.selectableUsers.find((x) => x.id === ch.id);
    if (find) {
      find.check = true;
    }
  });
};
const setTrueInStrategy = () => {
  test.value.strategies.forEach((st) => {
    const find = $strategyStore.strategies.find((x) => x.id === st.id);
    if (find) {
      find.check = true;
    }
  });
};
const setStep = () => {
  if (currentRoute.value === TESTS_SHARE) {
    step.value = 4;
  }
};
onMounted(async () => {
  if (props.uuid.length > 0) {
    await $testStore.findById(props.uuid);
  } else {
    $testStore.$reset();
  }
  $categoryStore.findAllCategoryVectors();
  $typeStore.findAllTypes();
  await $checklistStore.findAllChecklists();
  await $strategyStore.findAllStrategies();
  await $authStore.findDifferentUsers();
  setTrueInChecklist();
  setTrueInStrategy();
  setTrueInTesters();
  setStep();
});
</script>

<style scoped>

</style>
