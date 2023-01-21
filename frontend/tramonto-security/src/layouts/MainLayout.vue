<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          aria-label="Menu"
          dense
          flat
          icon="menu"
          round
          @click="toggleLeftDrawer"
        />
        <q-toolbar-title> Tramonto Security</q-toolbar-title>
        <div>v1.0 beta</div>
      </q-toolbar>
    </q-header>
    <q-drawer v-model="leftDrawerOpen" bordered show-if-above>
      <q-list>
        <q-item-label header> Menu</q-item-label>
        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          :essential="link"
          v-bind="link"
        />
      </q-list>
    </q-drawer>
    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import EssentialLink from 'components/EssentialLink.vue';
import {
  CHECKLISTS_ICON,
  CHECKLISTS_PAGINATE,
  STRATEGIES_PAGINATE,
  TESTS_ICON,
  TESTS_PAGINATE,
  VECTORS_CATEGORIES_PAGINATE,
} from 'src/consts/RoutesConsts';
import { CHECKLISTS } from 'src/consts/LabelsConsts';
import { useAuthenticationStore } from 'stores/authentication.store';
import { Cookies } from 'quasar';
import EssentialLinkProps from 'components/EssentialLinkProps';

const essentialLinks: EssentialLinkProps [] = [
  {
    title: 'Strategies',
    caption: 'Testing strategies',
    icon: 'far fa-chess-knight',
    link: `#${STRATEGIES_PAGINATE}`,
  },
  {
    title: 'Vector Categories',
    caption: 'Vector Categories',
    icon: 'fa fa-vector-square',
    link: `#${VECTORS_CATEGORIES_PAGINATE}`,
  },
  {
    title: CHECKLISTS,
    caption: 'Testing checklists',
    icon: CHECKLISTS_ICON,
    link: `#${CHECKLISTS_PAGINATE}`,
  },
  {
    title: 'Tests',
    caption: 'Tests for Tramonto',
    icon: TESTS_ICON,
    link: `#${TESTS_PAGINATE}`,
  },
];

const leftDrawerOpen = ref(false);

function toggleLeftDrawer() {
  leftDrawerOpen.value = !leftDrawerOpen.value;
}

onMounted(() => {
  const $authStore = useAuthenticationStore();
  if (Cookies.get('tramontoSecurityAccessToken') !== null
    && $authStore.$state.token === undefined) {
    $authStore.setToken(Cookies.get('tramontoSecurityAccessToken'));
    $authStore.findUser();
  }
});
</script>
