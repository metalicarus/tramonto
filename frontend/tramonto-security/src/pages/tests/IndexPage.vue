<template>
  <q-page class="q-ma-lg">
    <div class="q-pa-md q-gutter-sm">
      <q-breadcrumbs>
        <q-breadcrumbs-el :to="anchorRoute" icon="home"/>
        <q-breadcrumbs-el :icon="icon"
                          :label="label"
                          :to="mainRoute"
        />
      </q-breadcrumbs>
    </div>
    <div class="q-pa-sm">
      <q-btn :disable="!acceptedRoles"
             :icon-right="icon"
             color="primary"
             label="New"
             outline
             @click="this.$router.push(editRoute)"
      >
      </q-btn>
    </div>
    <q-card bordered class="q-pa-lg" flat>
      <paginate-test :edit-route="editRoute"
                     :label="label"
                     :share-route="shareRoute"
      />
    </q-card>
  </q-page>
</template>

<script>
import {
  ANCHOR_PAGE, TESTS_ICON, TESTS_PAGINATE, TESTS_SAVE, TESTS_SHARE,
} from '../../consts/RoutesConsts';
import { TESTS } from '../../consts/LabelsConsts';
import PaginateTest from '../../components/test/PaginateTest.vue';
import { useAuthenticationStore } from '../../stores/authentication.store';
import { RoutesRolesConsts } from '../../consts/RoutesRolesConsts';

export default {
  name: 'IndexPage',
  components: { PaginateTest },
  setup() {
    const $authStore = useAuthenticationStore();
    return {
      icon: TESTS_ICON,
      label: TESTS,
      acceptedRoles: $authStore.userRolesContain(RoutesRolesConsts[TESTS_SAVE]),
      mainRoute: TESTS_PAGINATE,
      anchorRoute: ANCHOR_PAGE,
      editRoute: TESTS_SAVE,
      shareRoute: TESTS_SHARE,
    };
  },
};
</script>

<style scoped>

</style>
