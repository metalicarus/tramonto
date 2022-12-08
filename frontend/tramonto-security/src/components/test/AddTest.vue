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
import { onMounted, ref, toRaw } from 'vue';
import Test from 'components/test/Test';
import TestTypeService from 'src/services/testType.service';
import { useQuasar } from 'quasar';
import AdequacyStep from 'components/test/steps/AdequacyStep.vue';
import CheckStep from 'components/test/steps/CheckStep.vue';
import ChecklistService from 'src/services/Checklist.service';

export default {
  name: 'AddTest',
  components: { CheckStep, AdequacyStep },
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
    const checklists = ref([]);
    const test = ref(Test);
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
      findAllChecklists();
      findAllTypes();
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
      step: ref(2),
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
