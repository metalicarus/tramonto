<template>
  <q-form class="q-gutter-md" flat @submit="save">
    <div class="q-pa-md q-gutter-md">
      <div class="row q-gutter-md">
        <div class="col-sm-3">
          <q-input v-model="strategy.id" disable flat label="ID" outlined stack-label/>
        </div>
        <div class="col-sm-8">
          <q-input v-model="strategy.strategy"
                   flat
                   label="Strategy"
                   outlined
                   stack-label
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12">
          <q-input v-model="strategy.description"
                   flat
                   label="Description"
                   outlined
                   stack-label type="textarea"
          />
        </div>
      </div>
      <div v-if="uuid" class="row q-gutter-md">
        <div class="col-2">
          <status-select :option="strategy.status"
                         @update="setStatus"
          />
        </div>
      </div>
    </div>
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
import StrategySerivce from 'src/services/strategy.serivce';
import StrategyInputDto from 'src/services/dtos/StrategyInput.dto';
import { useQuasar } from 'quasar';
import { onMounted, ref } from 'vue';
import StatusSelect from 'components/baseComponents/statusSelect/StatusSelect.vue';
import { useRouter } from 'vue-router';

export default {
  name: 'add-strategy',
  components: { StatusSelect },
  props: {
    uuid: {
      type: String,
      required: false,
    },
    previousRoute: {
      type: String,
      required: true,
    },
  },
  setup(props: any) {
    const $q = useQuasar();
    const router = useRouter();
    const strategy = ref({
      id: '',
      strategy: '',
      description: '',
      status: 'ACTIVE',
    });
    function resetForm() {
      strategy.value.strategy = '';
      strategy.value.id = '';
      strategy.value.description = '';
      strategy.value.status = 'ACTIVE';
    }
    function setStatus(value: any) {
      strategy.value.status = value;
    }
    function save() {
      $q.loading.show();
      StrategySerivce.save(strategy.value as StrategyInputDto)
        .then((response) => {
          $q.notify({
            message: '[SUCCESS]: Successfully saved record',
            color: 'positive',
            multiLine: true,
            actions: [
              {
                label: 'Reply',
                color: 'yellow',
              },
            ],
          });
          router.push({ path: '/strategies/save', query: { uuid: response.data.id } });
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
      if (props.uuid !== undefined) {
        $q.loading.show();
        StrategySerivce.findById(props.uuid)
          .then((response) => {
            strategy.value.id = response.data.id;
            strategy.value.strategy = response.data.strategy;
            strategy.value.status = response.data.status;
            strategy.value.description = response.data.description;
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
    });
    return {
      strategy,
      save,
      setStatus,
    };
  },
};
</script>

<style scoped>

</style>
