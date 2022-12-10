<template>
  <div v-for="(item, index) in model.vectors" :key="index" class=" q-gutter-md">
    <q-expansion-item :label="item.title" :default-opened="test.vectors.length === 1">
      <template v-slot:header>
        <q-item-section>
          <div class="row q-gutter-sm justify-end">
            <div class="col-12 col-md-8">
              {{ `${index + 1}º Vector: ${item.title}` }}
            </div>
            <div class="col-12 col-md-2 q-gutter-sm">
              <q-btn v-if="test.vectors.length > 1"
                     size="small"
                     icon="fa fa-remove"
                     outline
                     color="red"
                     @click="test.removeVector(index)"
              />
              <q-btn size="small"
                     icon="fa fa-plus"
                     outline
                     color="green"
                     @click="test.addVector()"
              />
            </div>
          </div>

        </q-item-section>
      </template>
      <div class="row q-gutter-md">
        <div class="col-12">
          <q-input v-model="item.title"
                   label="Title"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-12">
          <q-input v-model="item.description"
                   label="Description"
                   type="textarea"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-12">
          <q-input v-model="item.expectedResults"
                   label="Expected results"
                   type="textarea"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-12">
          <q-input v-model="item.resultsObtained"
                   label="Results obtained"
                   type="textarea"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-5">
          <q-badge color="red">
            Reproducibility Rate: {{ item.reproducibility }} %
          </q-badge>
          <q-slider v-model="item.reproducibility"
                    color="red"
          />
        </div>
        <div class="col-12 col-md-5">
          <q-badge color="blue">
            Impact Rate: {{ item.impact }} %
          </q-badge>
          <q-slider v-model="item.impact"
                    color="blue"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12 col-md-3">
          <q-select v-model="item.vectorCategory.id"
                    :options="vectors"
                    option-label="vectorCategory"
                    option-value="id"
                    label="Vector Category"
                    stack-label
                    outlined
                    emit-value
                    map-options
                    :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
      </div>
    </q-expansion-item>
  </div>
</template>
<script setup lang="ts">
import VectorCategoryDto from 'src/services/dtos/VectorCategory.dto';
import { PropType, toRef } from 'vue';
import Test from 'components/test/Test';

const props = defineProps({
  vectors: {
    type: Array as PropType<Array<VectorCategoryDto>>,
    required: true,
  },
  model: {
    type: Object as PropType<typeof Test>,
    required: true,
  },
});

const test = toRef(props, 'model');

</script>
<style scoped>

</style>
