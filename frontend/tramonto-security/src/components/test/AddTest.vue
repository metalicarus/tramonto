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
                             :approaches="approaches"
                             :aggression="aggression"
                             :types="types"
              />
            </q-step>
            <q-step
              :name="2"
              title="Verificação"
              icon="fas fa-check-circle"
              active-icon="edit"
            >
              <check-step v-if="checklists.length > 0"
                          :checklists="checklists"
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
              <prepare-step v-if="strategies.length > 0 && tools.length > 0"
                            :strategies="strategies"
                            :tools="tools"
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
              <execution-step v-if="vectors.length > 0"
                              :model="test"
                              :vectors="vectors"
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
import Test from 'components/test/Test';
import TestTypeService from 'src/services/testType.service';
import { useQuasar } from 'quasar';
import AdequacyStep from 'components/test/steps/AdequacyStep.vue';
import CheckStep from 'components/test/steps/CheckStep.vue';
import ChecklistService from 'src/services/Checklist.service';
import PrepareStep from 'components/test/steps/PrepareStep.vue';
import StrategySerivce from 'src/services/strategy.serivce';
import StrategyInputDto from 'src/services/dtos/StrategyInput.dto';
import { ToolDto } from 'src/services/dtos/Tool.dto';
import ToolService from 'src/services/Tool.service';
import ExecutionStep from 'components/test/steps/ExecutionStep.vue';
import VectorCategoryDto from 'src/services/dtos/VectorCategory.dto';
import VectorCategoryService from 'src/services/vectorCategory.service';

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
    const $q = useQuasar();
    const types = ref();
    const strategyType: StrategyInputDto[] = [];
    const vectorType: VectorCategoryDto[] = [];
    const toolType: ToolDto[] = [];
    const strategies = ref(strategyType);
    const tools = ref(toolType);
    const vectors = ref(vectorType);
    const checklists = ref([]);
    const test = ref(Test);
    function findAllTools(): void {
      $q.loading.show();
      ToolService.findAll().then((response) => {
        tools.value = response.data;
      })
        .catch((error) => {
          $q.notify({
            message: `[ERROR]: ${error.response.data.message}`,
            color: 'negative',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
        })
        .finally(() => {
          $q.loading.hide();
        });
    }
    function findAllVectors(): void {
      $q.loading.show();
      VectorCategoryService.findAll().then((response) => {
        vectors.value = response.data;
      })
        .catch((error) => {
          $q.notify({
            message: `[ERROR]: ${error.response.data.message}`,
            color: 'negative',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
        })
        .finally(() => {
          $q.loading.hide();
        });
    }
    function findAllChecklists(): void {
      $q.loading.show();
      ChecklistService.findAll().then((response) => {
        checklists.value = response.data;
      })
        .catch((error) => {
          $q.notify({
            message: `[ERROR]: ${error.response.data.message}`,
            color: 'negative',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
        })
        .finally(() => {
          $q.loading.hide();
        });
    }
    function findAllStrategies(): void {
      $q.loading.show();
      StrategySerivce.findAll().then((response) => {
        strategies.value = response.data;
      })
        .catch((error) => {
          $q.notify({
            message: `[ERROR]: ${error.response.data.message}`,
            color: 'negative',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
        })
        .finally(() => {
          $q.loading.hide();
        });
    }
    function findAllTypes(): void {
      $q.loading.show();
      TestTypeService.findAll().then((response) => {
        types.value = response.data;
      })
        .catch((error) => {
          $q.notify({
            message: `[ERROR]: ${error.response.data.message}`,
            color: 'negative',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
        })
        .finally(() => {
          $q.loading.hide();
        });
    }
    onMounted(() => {
      findAllStrategies();
      findAllChecklists();
      findAllVectors();
      findAllTypes();
      findAllTools();
    });
    return {
      approaches: [
        {
          label: 'Covert',
          value: 'COVERT',
        },
        {
          label: 'Overt',
          value: 'OVERT',
        },
      ],
      aggression: [
        {
          label: 'High',
          value: 'HIGH',
        },
        {
          label: 'Medium',
          value: 'MEDIUM',
        },
        {
          label: 'Low',
          value: 'LOW',
        },
      ],
      types,
      test,
      checklists,
      strategies,
      vectors,
      tools,
      step: ref(4),
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
