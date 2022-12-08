<template>
  <q-form class="q-gutter-md" flat @submit="save">
    <div class="q-pa-md q-gutter-md">
      <div class="row q-gutter-md">
        <div class="col-sm-3">
          <q-input v-model="checklist.id" disable flat label="ID" outlined stack-label/>
        </div>
        <div class="col-sm-8">
          <q-input v-model="checklist.checklist"
                   flat
                   label="Checklist"
                   outlined
                   stack-label
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12">
          <q-input v-model="checklist.description"
                   flat
                   label="Description"
                   outlined
                   stack-label type="textarea"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-3">
          <q-checkbox v-model="checklist.default"
                      label="Is Default?"
          />
        </div>
        <div class="col-3">
          <q-select v-model="checklist.type"
                    :options="[
                      {
                        value: 'CUSTOMIZED',
                        label: 'Customized'
                      },
                      {
                        value: 'REQUIRED',
                        label: 'Required',
                      },
                      {
                        value: 'RELATED',
                        label: 'Related'
                      }
                    ]"
                    label="Type"
                    option-value="value"
                    option-label="label"
                    emit-value
                    map-options
                    outlined
            />
        </div>
        <div v-if="uuid" class="col-3">
          <status-select :option="checklist.status"
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
import { useQuasar } from 'quasar';
import { useRouter } from 'vue-router';
import { onMounted, ref } from 'vue';
import StatusSelect from 'components/baseComponents/statusSelect/StatusSelect.vue';
import ChecklistService from '../../services/Checklist.service';

export default {
  name: 'AddChecklist',
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
    nextRoute: {
      type: String,
      required: true,
    },
  },
  setup(props: any) {
    const $q = useQuasar();
    const router = useRouter();
    const checklist = ref({
      id: '',
      checklist: '',
      description: '',
      status: 'ACTIVE',
      type: '',
      default: false,
    });
    function setStatus(value: never) {
      checklist.value.status = value;
    }
    function save() {
      $q.loading.show();
      ChecklistService.save(checklist.value)
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
          router.push({ path: props.nextRoute, query: { uuid: response.data.id } });
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
        ChecklistService.findById(props.uuid)
          .then((response) => {
            checklist.value.id = response.data.id;
            checklist.value.checklist = response.data.checklist;
            checklist.value.type = response.data.type;
            checklist.value.status = response.data.status;
            checklist.value.default = response.data.default;
            checklist.value.description = response.data.description;
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
      checklist,
      save,
      setStatus,
    };
  },
};
</script>

<style scoped>

</style>
