<template>
  <q-form class="q-gutter-md" flat @submit="save">
    <div class="q-pa-md q-gutter-md">
      <div class="row q-gutter-md">
        <div class="col-sm-3">
          <q-input v-model="vector.id" disable flat label="ID" outlined stack-label/>
        </div>
        <div class="col-sm-8">
          <q-input v-model="vector.vectorCategory"
                   flat
                   label="Vector Category"
                   outlined
                   stack-label
                   :rules="[ val => val && val.length > 0 || 'Please type something']"
          />
        </div>
      </div>
      <div class="row q-gutter-md">
        <div class="col-12">
          <q-input v-model="vector.description"
                   flat
                   label="Description"
                   outlined
                   stack-label type="textarea"
          />
        </div>
      </div>
      <div v-if="uuid" class="row q-gutter-md">
        <div class="col-2">
          <status-select :option="vector.status"
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
import VectorCategoryDto from '../../services/dtos/VectorCategory.dto';
import VectorCategoryService from '../../services/vectorCategory.service';

export default {
  name: 'add-vector-category',
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
    const vector = ref({
      id: '',
      vectorCategory: '',
      description: '',
      status: 'ACTIVE',
    });
    function setStatus(value: any) {
      vector.value.status = value;
    }
    function save() {
      $q.loading.show();
      VectorCategoryService.save(vector.value as VectorCategoryDto)
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
        VectorCategoryService.findById(props.uuid)
          .then((response) => {
            vector.value.id = response.data.id;
            vector.value.vectorCategory = response.data.vectorCategory;
            vector.value.status = response.data.status;
            vector.value.description = response.data.description;
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
      vector,
      save,
      setStatus,
    };
  },
};
</script>

<style scoped>

</style>
