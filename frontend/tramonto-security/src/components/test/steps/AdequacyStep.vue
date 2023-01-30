<template>
  <div class=" q-gutter-md">
    <div class="row q-gutter-md">
      <div class="col-12 col-md-3">
        <q-input v-model="test.id"
                 :disable="true"
                 label="ID"
                 outlined
                 stack-label
        />
      </div>
      <div class="col-12 col-md-8">
        <q-input v-model="test.title"
                 :rules="[ val => val && val.length > 0 || 'Please type something']"
                 label="Title"
                 outlined
                 stack-label
        />
      </div>
    </div>
    <div class="row q-gutter-md">
      <div class="col-12 col-md-12">
        <q-input v-model="test.description"
                 :rules="[ val => val && val.length > 0 || 'Please type something']"
                 label="Description"
                 outlined
                 stack-label
                 type="textarea"
        />
      </div>
    </div>
    <div v-for="(item, index) in test.objectives" :key="index" class="row q-gutter-md">
      <div class="col-12 col-md-9">
        <q-input v-model="item.objective"
                 :label="`Objective ${index + 1}`"
                 :rules="[ val => val && val.length > 0 || 'Please type something']"
                 outlined
                 stack-label
        />
      </div>
      <div class="col-4 col-md-1">
        <q-btn color="green"
               icon="fa fa-plus"
               outline
               size="large"
               @click="$testStore.addObjective()"
        />
      </div>
      <div class="col-4 col-md-1">
        <q-btn v-if="test.objectives.length > 1"
               color="red"
               icon="fa fa-remove"
               outline
               size="large"
               @click="$testStore.removeObjective(index)"
        />
      </div>
    </div>
    <div class="row q-gutter-md">
      <div class="col-12 col-md-4">
        <q-input :model-value="initialAndFinalDateFormat"
                 label="Initial and Final date"
                 outlined
                 stack-label
        >
          <template v-slot:append>
            <q-icon class="cursor-pointer" name="event">
              <q-popup-proxy
                cover
                transition-hide="scale"
                transition-show="scale"
              >
                <q-date v-model="initialAndFinalDateRange"
                        range>
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup color="primary" flat label="Close"/>
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
      <div class="col-12 col-md-2">
        <q-input v-model="test.estimatedTime"
                 :rules="[ val => val && val.length > 0 || 'Please type something']"
                 label="Estimated Time"
                 mask="##:##:##"
                 outlined
                 stack-label
        />
      </div>
      <div class="col-12 col-md-2">
        <q-checkbox v-model="test.retest"
                    label="Re-test"
                    outlined
                    stack-label
        />
      </div>
      <div v-if="test.retest" class="col-12 col-md-2">
        <q-input :model-value="retestDateFormat"
                 label="Re-test Date"
                 outlined
                 stack-label
        >
          <template v-slot:append>
            <q-icon class="cursor-pointer" name="event">
              <q-popup-proxy
                cover
                transition-hide="scale"
                transition-show="scale"
              >
                <q-date v-model="setRetestDate"
                        :options="minRestestDate">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup color="primary" flat label="Close"/>
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
    </div>
    <div class="row q-gutter-md">

    </div>
    <div class="row q-gutter-md">
      <div class="col-12 col-md-3">
        <q-select v-model="test.type"
                  :options="types"
                  :rules="[ val => !!val > 0 || 'Please select something']"
                  emit-value
                  label="Type"
                  map-options
                  option-label="type"
                  option-value="uuid"
                  outlined
                  stack-label
        />
      </div>
      <div class="col-12 col-md-3">
        <q-select v-model="test.approach"
                  :options="approaches"
                  :rules="[ val => !!val > 0 || 'Please select something']"
                  emit-value
                  label="Approach"
                  map-options
                  option-label="label"
                  option-value="value"
                  outlined
                  stack-label
        />
      </div>
      <div class="col-12 col-md-3">
        <q-select v-model="test.aggression"
                  :options="aggression"
                  :rules="[ val => !!val > 0 || 'Please select something']"
                  emit-value
                  label="Aggression"
                  map-options
                  option-label="label"
                  option-value="value"
                  outlined
                  stack-label
        />
      </div>
    </div>
    <div class="row q-gutter-md">
      <div class="col-12 col-md-12">
        <q-input v-model="test.generalObservation"
                 label="General Observation"
                 outlined
                 stack-label
                 type="textarea"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {
  computed, PropType, Ref, toRef,
} from 'vue';
import TestDto from 'src/services/dtos/Test.dto';
import { useTestStore } from 'stores/test.store';
import { date as dtFn } from 'quasar';

const props = defineProps({
  model: {
    type: Object as PropType<TestDto>,
    required: true,
  },
  approaches: {
    type: Array,
    required: true,
  },
  aggression: {
    type: Array,
    required: true,
  },
  types: {
    type: Array,
    required: true,
  },
});
const $testStore = useTestStore();
const test: Ref<TestDto> = toRef(props, 'model');
const formatDate = (timestamp: string) => dtFn.formatDate(timestamp, 'DD/MM/YYYY');
const initialAndFinalDateFormat = computed(() => ((test.value.finalDate)
  ? `from: ${formatDate(test.value.initialDate)} to: ${formatDate(test.value.finalDate)}`
  : ''));
const retestDateFormat = computed(() => formatDate(test.value.retestDate));
const initialAndFinalDateRange = computed({
  get() {
    return {
      from: dtFn.formatDate(test.value.initialDate, 'YYYY/MM/DDT00:00:00'),
      to: dtFn.formatDate(test.value.finalDate, 'YYYY/MM/DDT00:00:00'),
    };
  },
  set(value) {
    if (value !== null) {
      test.value.initialDate = dtFn.formatDate(value.from, 'YYYY-MM-DDT00:00:00');
      test.value.finalDate = dtFn.formatDate(value.to, 'YYYY-MM-DDT00:00:00');
      test.value.retestDate = '';
    }
  },
});
const setRetestDate = computed({
  get() {
    return dtFn.formatDate(test.value.retestDate, 'YYYY/MM/DDT00:00:00');
  },
  set(value) {
    if (value !== null) {
      test.value.retestDate = dtFn.formatDate(value, 'YYYY-MM-DDT00:00:00');
    }
  },
});
function minRestestDate(date: string) {
  return dtFn.formatDate(date, 'YYYY-MM-DDT00:00:00') > test.value.finalDate;
}
</script>

<style scoped>

</style>
