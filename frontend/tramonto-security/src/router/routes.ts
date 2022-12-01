import { RouteRecordRaw } from 'vue-router';
import {
  ANCHOR_PAGE,
  STRATEGIES_PAGINATE,
  STRATEGIES_SAVE,
  STRATEGIES_UPDATE,
} from 'src/consts/RoutesConsts';

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
    path: ANCHOR_PAGE,
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/IndexPage.vue'),
        meta: {
          type: 'main',
        },
      },
      {
        path: STRATEGIES_PAGINATE,
        component: () => import('pages/strategies/IndexPage.vue'),
        meta: {
          type: 'pagination',
        },
        beforeEnter: [addQueryStringPagination],
      },
      {
        path: STRATEGIES_SAVE,
        component: () => import('pages/strategies/SavePage.vue'),
        meta: {
          type: 'save',
        },
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
