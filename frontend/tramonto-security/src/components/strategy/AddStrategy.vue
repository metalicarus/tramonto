<template>
  <q-form class="q-gutter-md" flat @reset="save" @submit="save">
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
    </div>
    <div class="q-pa-md q-gutter-md">
      <div class="row q-gutter-md">
        <q-btn color="primary" label="Submit" type="submit" outline size="large"/>
      </div>
    </div>
  </q-form>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import StrategySerivce from 'src/services/strategy.serivce';
import StrategyInputDto from 'src/services/dtos/StrategyInput.dto';

export default defineComponent({
  name: 'add-strategy',
  data() {
    return {
      strategy: {
        id: '',
        strategy: '',
        description: '',
        status: 'ACTIVE',
      },
    };
  },
  methods: {
    save() {
      StrategySerivce.save(this.strategy as StrategyInputDto)
        .then(() => {
          this.$q.notify({
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
        })
        .catch((error) => {
          this.$q.notify({
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
        });
    },
  },
});

</script>

<style scoped>

</style>
