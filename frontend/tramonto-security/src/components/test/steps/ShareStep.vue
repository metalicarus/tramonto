<template>
  <div class="q-gutter-md">
    <div class="row q-gutter-md">
      <div class="col-12 q-pa-sm rounded-borders">
        <q-list bordered
                padding>
          <q-expansion-item :disable="!belongsToCurrentUser"
                            expand-separator label="Selectable Users"
          >
            <q-item v-for="(item, index) in copySelectables"
                    :key="index"
                    v-ripple
                    clickable
            >
              <q-item-section side top>
                <q-checkbox
                  v-model="item.check"
                  @update:model-value="selectUser"
                />
              </q-item-section>
              <q-item-section>
                <q-item-label>Name: {{ `${item.firstName} ${item.lastName}` }}</q-item-label>
                <q-item-label caption>
                  | E-mail: {{ item.email }} | Roles: {{
                    item.roles.map(x => x.role)
                      .join(',')
                  }}
                </q-item-label>
              </q-item-section>
            </q-item>
          </q-expansion-item>
        </q-list>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, PropType, ref } from 'vue';
import { UserDto } from 'stores/dtos/UserDto';

const props = defineProps({
  belongsToCurrentUser: {
    type: Boolean,
    required: true,
  },
  selectableUsers: {
    type: Array as PropType<Array<UserDto>>,
    required: true,
  },
});
const emit = defineEmits(['update-selected-users']);
const copySelectables = ref<UserDto[]>(props.selectableUsers);
const selectUser = () => {
  emit('update-selected-users', copySelectables.value.filter((x) => x.check));
};
const setFalseInSelectables = () => {
  copySelectables.value.forEach((x) => {
    if (!x.check) {
      x.check = false;
    }
  });
};
onMounted(() => {
  setFalseInSelectables();
});
</script>

<style scoped>

</style>
