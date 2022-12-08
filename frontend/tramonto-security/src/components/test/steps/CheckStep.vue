<template>
 <div class="q-gutter-md">
   <div class="row q-gutter-md">
     <div class="col-12 q-pa-sm rounded-borders"
          v-for="(type, index) in types"
          :key="index">
       <q-list bordered
               padding
               v-if="hasChecklistType(type)"
       >
         <q-expansion-item
           expand-separator
           :label="type"
         >
           <q-item v-ripple
                   clickable
                   v-for="(item, itemIndex) in filterChecklistsByType(type)"
                   :key="itemIndex">
             <q-item-section side top>
               <q-checkbox v-model="item.check" @update:model-value="selectChecklist" />
             </q-item-section>
             <q-item-section>
               <q-item-label>{{ item.checklist }}</q-item-label>
               <q-item-label caption> {{ item.description }} </q-item-label>
             </q-item-section>
           </q-item>
         </q-expansion-item>

       </q-list>
     </div>
   </div>
 </div>
</template>

<script lang="ts">
import { onMounted, ref, toRaw } from 'vue';

export default {
  name: 'CheckStep',
  props: {
    types: {
      type: Array,
      required: true,
    },
    checklists: {
      type: Array,
      required: true,
    },
  },
  setup(props: any, ctx: any) {
    const selected = ref([]);
    const copyChecklists = ref(props.checklists);
    function filterChecklistsByType(type: string) {
      return copyChecklists.value.filter((x: { type: string; }) => x.type === type);
    }
    function hasChecklistType(type: string): boolean {
      return props.checklists.find((x: { type: string; }) => x.type === type);
    }
    function selectChecklist() {
      ctx.emit('update', copyChecklists.value.filter((x: { check: boolean; }) => x.check));
    }
    function setFalseInChecklists() {
      copyChecklists.value.forEach((x: { check: boolean; }) => {
        x.check = false;
      });
    }
    onMounted(() => {
      setFalseInChecklists();
    });
    return {
      selected,
      copyChecklists,
      hasChecklistType,
      selectChecklist,
      filterChecklistsByType,
    };
  },
};
</script>

<style scoped>

</style>
