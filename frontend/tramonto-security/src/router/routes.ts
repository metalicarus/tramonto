import { RouteRecordRaw } from 'vue-router';
import {
  ANCHOR_PAGE,
  CHECKLISTS_PAGINATE,
  CHECKLISTS_SAVE, LOGIN_PAGE,
  STRATEGIES_PAGINATE,
  STRATEGIES_SAVE,
  TESTS_PAGINATE,
  TESTS_SAVE, TESTS_SHARE, UNAUTHORIZED,
  VECTORS_CATEGORIES_PAGINATE,
  VECTORS_CATEGORIES_SAVE,
} from 'src/consts/RoutesConsts';
import { ROLE_TESTER_ADVANCED, ROLE_TESTER_BASIC } from 'src/consts/RolesConsts';

// eslint-disable-next-line consistent-return
function addQueryStringPagination(to: any) {
  if (Object.keys(to.query).length === 0) {
    return {
      path: to.path,
      query: {
        filter: '',
        page: 0,
        perPage: 10,
        sortBy: '',
        direction: 'ASC',
      },
    };
  }
}

const routes: RouteRecordRaw[] = [
  {
    path: LOGIN_PAGE,
    name: 'login',
    component: () => import('layouts/LoginLayout.vue'),
  },
  {
    path: ANCHOR_PAGE,
    component: () => import('layouts/MainLayout.vue'),
    meta: {
      role: ROLE_TESTER_BASIC,
    },
    children: [
      {
        path: UNAUTHORIZED,
        component: () => import('pages/Unauthorized.vue'),
      },
      {
        path: '',
        component: () => import('pages/IndexPage.vue'),
        meta: {
          type: 'main',
          role: ROLE_TESTER_BASIC,
        },
      },
      {
        path: STRATEGIES_PAGINATE,
        component: () => import('pages/strategies/IndexPage.vue'),
        meta: {
          type: 'pagination',
          role: ROLE_TESTER_ADVANCED,
        },
        beforeEnter: [addQueryStringPagination],
      },
      {
        path: STRATEGIES_SAVE,
        component: () => import('pages/strategies/SavePage.vue'),
        meta: {
          type: 'save',
          role: ROLE_TESTER_ADVANCED,
        },
      },
      {
        path: VECTORS_CATEGORIES_PAGINATE,
        component: () => import('pages/vectorCategory/IndexPage.vue'),
        meta: {
          type: 'pagination',
          role: ROLE_TESTER_ADVANCED,
        },
        beforeEnter: [addQueryStringPagination],
      },
      {
        path: VECTORS_CATEGORIES_SAVE,
        component: () => import('pages/vectorCategory/SavePage.vue'),
        meta: {
          type: 'save',
          role: ROLE_TESTER_ADVANCED,
        },
      },
      {
        path: TESTS_SAVE,
        component: () => import('pages/tests/SavePage.vue'),
        meta: {
          type: 'save',
          role: ROLE_TESTER_ADVANCED,
        },
      },
      {
        path: TESTS_SHARE,
        component: () => import('pages/tests/SavePage.vue'),
        meta: {
          type: 'share',
          role: ROLE_TESTER_ADVANCED,
        },
      },
      {
        path: TESTS_PAGINATE,
        component: () => import('pages/tests/IndexPage.vue'),
        meta: {
          type: 'pagination',
          role: ROLE_TESTER_ADVANCED,
        },
        beforeEnter: [addQueryStringPagination],
      },
      {
        path: CHECKLISTS_PAGINATE,
        component: () => import('pages/checklists/IndexPage.vue'),
        meta: {
          type: 'pagination',
          role: ROLE_TESTER_ADVANCED,
        },
        beforeEnter: [addQueryStringPagination],
      },
      {
        path: CHECKLISTS_SAVE,
        component: () => import('pages/checklists/SavePage.vue'),
        meta: { type: 'save' },
      },
    ],
  },
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];
export default routes;
