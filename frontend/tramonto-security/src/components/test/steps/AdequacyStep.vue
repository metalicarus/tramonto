<template>
   <div class=" q-gutter-md">
      <div class="row q-gutter-md">
        <div class="col-12 col-md-3">
          <q-input v-model="test.uuid"
                   :disable="true"
                   outlined
                   stack-label
                   label="ID"
          />
        </div>
        <div class="col-12 col-md-8">
          <q-input v-model="test.title"
                   outlined
                   stack-label
                   label="Title"
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-12">
          <q-input v-model="test.description"
                   type="textarea"
                   outlined
                   stack-label
                   label="Description"
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
      </div>
      <div v-for="(item, index) in test.objectives" :key="index" class="row q-gutter-md">
        <div class="col-12 col-md-9">
          <q-input v-model="item.objective"
                   outlined
                   stack-label
                   :label="`Objective ${index + 1}`"
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
        <div class="col-4 col-md-1">
          <q-btn size="large"
                 icon="fa fa-plus"
                 outline
                 color="green"
                 @click="test.addObjective()"
          />
        </div>
        <div class="col-4 col-md-1">
          <q-btn v-if="test.objectives.length > 1"
                 size="large"
                 icon="fa fa-remove"
                 outline
                 color="red"
                 @click="test.removeObjective(index)"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-4">
          <q-input :model-value="test.formatInitialAndFinalDate()"
                   outlined
                   label="Initial and Final date"
                   stack-label
          >
            <template v-slot:append>
              <q-icon name="event" class="cursor-pointer">
                <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                  <q-date v-model="test.initialAndFinalDate" range>
                    <div class="row items-center justify-end">
                      <q-btn v-close-popup label="Close" color="primary" flat />
                    </div>
                  </q-date>
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </div>
        <div class="col-12 col-md-2">
          <q-input v-model="test.estimatedTime"
                   type="date"
                   outlined
                   label="Estimated Time"
                   stack-label
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
        <div class="col-12 col-md-2">
          <q-checkbox v-model="test.retest"
                      outlined
                      label="Re-test"
                      stack-label
          />
        </div>
        <div v-if="test.retest" class="col-12 col-md-2">
          <q-input v-model="test.retestDate"
                   type="date"
                   outlined
                   label="Re-test Date"
                   stack-label
                   :rules="[ val => val && val.length > 0 && test.retest
                              || 'Please type something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">

      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-3">
         <q-select v-model="test.type"
                    :options="types"
                    option-label="type"
                    option-value="uuid"
                    label="Type"
                    stack-label
                    outlined
                    emit-value
                    map-options
                    :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
        <div class="col-12 col-md-3">
          <q-select v-model="test.approach"
                    :options="approaches"
                    option-label="label"
                    option-value="value"
                    label="Approach"
                    stack-label
                    outlined
                    emit-value
                    map-options
                    :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
        <div class="col-12 col-md-3">
          <q-select v-model="test.aggression"
                    :options="aggression"
                    option-label="label"
                    option-value="value"
                    label="Aggression"
                    stack-label
                    outlined
                    emit-value
                    map-options
                    :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-12">
          <q-input v-model="test.generalObservation"
                   type="textarea"
                   outlined
                   stack-label
                   label="General Observation"
          />
        </div>
      </div>
    </div>
</template>

<script lang="ts">
import { toRef } from 'vue';

export default {
  name: 'AdequacyStep',
  props: {
    model: {
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
  },
  setup(props: never) {
    return {
      test: toRef(props, 'model'),
    };
  },
};
</script>

<style scoped>

</style>
