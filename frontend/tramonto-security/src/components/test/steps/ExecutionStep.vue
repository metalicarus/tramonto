<template>
  <q-btn class="q-ma-sm" color="green" flat label="Add Vector" @click="$testStore.addVector()"/>
  <q-list bordered class="rounded-borders">

    <q-item-label header>Vector List</q-item-label>
    <q-separator spaced/>

    <q-item v-if="test.vectors.length === 0">
      No registered vectors
    </q-item>
    <q-item v-for="(item,index) in test.vectors" :key="index">
      <q-item-section avatar top>
        <q-icon :color="item.id.length === 0 ? 'black' : 'green'"
                name="fa-solid fa-microscope"
                size="24px"
        />
      </q-item-section>

      <q-item-section class="col-2 gt-sm" top>
        <q-item-label v-if="item.id.length === 0" class="q-mt-sm">Unsaved</q-item-label>
        <q-item-label v-if="item.id.length > 0" class="q-mt-sm">Saved</q-item-label>
      </q-item-section>

      <q-item-section top>
        <q-item-label lines="1">
          <span class="text-weight-medium">{{ item.title }}</span>
        </q-item-label>
        <q-item-label caption lines="1"> {{ item.description }}</q-item-label>
      </q-item-section>
      <q-item-section top>
        <q-item-label lines="1">
          <span class="text-weight-medium">{{ item.approval }}</span>
        </q-item-label>
        <q-item-label caption lines="1">Approval status</q-item-label>
      </q-item-section>
      <q-item-section side top>
        <div class="text-grey-8 q-gutter-xs">
          <q-btn class="gt-xs"
                 dense
                 flat
                 icon="edit"
                 round
                 size="12px"
                 @click="itemIndex = index; dialog = !dialog"
          />
          <q-btn :disable="!test.belongsToCurrentUser && !test.vectors[index].belongsToCurrentUser"
                 class="gt-xs"
                 icon="done"
                 size="12px"
                 dense
                 flat
                 round
                 @click="$testStore.persistVector(index)"
          />
        </div>
      </q-item-section>

    </q-item>
  </q-list>
  <q-dialog
    v-model="dialog"
    :maximized="maximizedToggle"
    persistent
    transition-hide="slide-down"
    transition-show="slide-up"
  >
    <q-card class="text-white" :style="!maximizedToggle ? 'width: 1400px' : ''">
      <q-bar class="bg-primary">
        <q-space/>
        <q-btn dense
               flat
               icon="crop_square"
               @click="maximizedToggle = !maximizedToggle"
        >
          <q-tooltip v-if="!maximizedToggle" class="bg-white text-primary">Maximize</q-tooltip>
        </q-btn>
        <q-btn v-close-popup dense flat icon="close">
          <q-tooltip class="bg-white text-primary">Close</q-tooltip>
        </q-btn>
      </q-bar>
      <div class="q-pa-md">
        <div class="col-12 col-md-12">
          <q-input v-model="test.vectors[itemIndex].title"
                   :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
                   label="Title"
                   stack-label
                   outlined
                   :rules="[ val => !! val > 0 || 'Please select something']"
          />
        </div>
        <div class="col-12 col-md-12">
          <q-input v-model="test.vectors[itemIndex].description"
                   :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
                   label="Description"
                   type="textarea"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
        <div class="col-12 col-md-12">
          <q-input v-model="test.vectors[itemIndex].expectedResults"
                   :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
                   label="Expected results"
                   type="textarea"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
        <div class="col-12 col-md-12">
          <q-input v-model="test.vectors[itemIndex].resultsObtained"
                   :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
                   label="Results obtained"
                   type="textarea"
                   stack-label
                   outlined
                   :rules="[ val => !!val > 0 || 'Please select something']"
          />
        </div>
        <div class="col-12 col-md-5">
          <q-badge color="red">
            Reproducibility Rate: {{ test.vectors[itemIndex].reproducibility }} %
          </q-badge>
          <q-slider v-model="test.vectors[itemIndex].reproducibility"
                    :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
                    color="red"
          />
        </div>
        <div class="col-12 col-md-5">
          <q-badge color="blue">
            Impact Rate: {{ test.vectors[itemIndex].impact }} %
          </q-badge>
          <q-slider v-model="test.vectors[itemIndex].impact"
                    :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
                    color="blue"
          />
        </div>
        <div class="col-12 col-md-12">
          <q-select v-model="test.vectors[itemIndex].category.id"
                    :disable="!test.belongsToCurrentUser &&
                    !test.vectors[itemIndex].belongsToCurrentUser"
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
      <div class="q-pa-md">
        <div class="col-12 col-md-12">
          <q-select v-model="test.vectors[itemIndex].approval"
                    :options="approvalOptions"
                    :disable="!test.belongsToCurrentUser"
                    @update:model-value="$testStore.changeVectorStatus(
                      test.vectors[itemIndex].id,
                      test.vectors[itemIndex].approval)"
                    option-label="description"
                    option-value="value"
                    label="Approval"
                    stack-label
                    outlined
                    emit-value
                    map-options
          />
        </div>
      </div>
    </q-card>
  </q-dialog>
</template>
<script lang="ts" setup>
import VectorCategoryDto from 'src/services/dtos/VectorCategory.dto';
import {
  PropType, ref, Ref, toRef,
} from 'vue';
import { useTestStore } from 'stores/test.store';
import TestDto from 'src/services/dtos/Test.dto';

const props = defineProps({
  vectors: {
    type: Array as PropType<Array<VectorCategoryDto>>,
    required: true,
  },
  model: {
    type: Object as PropType<TestDto>,
    required: true,
  },
});
const $testStore = useTestStore();
const test: Ref<TestDto> = toRef(props, 'model');
const approvalOptions = [
  {
    value: 'EDIT',
    description: 'Edit',
  },
  {
    value: 'APPROVED',
    description: 'Approved',
  },
  {
    value: 'PENDING',
    description: 'Pending',
  },
  {
    value: 'REJECTED',
    description: 'Rejected',
  },
];
const dialog = ref<boolean>(false);
const maximizedToggle = ref<boolean>(true);
const itemIndex = ref<number>(0);
</script>
<style scoped>

</style>
