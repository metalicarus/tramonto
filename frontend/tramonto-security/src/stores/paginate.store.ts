import { defineStore } from 'pinia';
import { Ref, ref } from 'vue';
import { useRouter } from 'vue-router';

interface Query {
  [key: string]: any;
}
export const usePaginationStore = defineStore(
  'pagination',
  () => {
    const filter: Ref<string> = ref('');
    const disableFilter: Ref<boolean> = ref(false);
    const page: Ref<number> = ref(0);
    const disablePage: Ref<boolean> = ref(false);
    const $router = useRouter();
    const replaceParam = (field: string, value: any) => {
      const params = Object.keys($router.currentRoute.value.query);
      const newQuery: Query = {};
      params.forEach((x) => {
        newQuery[x] = (x === field) ? value : $router.currentRoute.value.query[x];
      });
      $router.push({
        query: { ...newQuery },
      });
    };
    const setPageDisable = (value: boolean) => {
      disablePage.value = value;
    };
    const setFilterDisable = (value: boolean) => {
      disableFilter.value = value;
    };
    const touchPage = () => {
      page.value = Number($router.currentRoute.value.query.page) + 1;
    };
    const touchFilter = () => {
      filter.value = <string> $router.currentRoute.value.query.filter;
    };
    const setFilter = (value: string) => {
      setFilterDisable(true);
      replaceParam('filter', value);
      filter.value = value;
      setFilterDisable(false);
    };
    const setPage = (value: number) => {
      setPageDisable(true);
      replaceParam('page', value - 1);
      page.value = value;
      setPageDisable(false);
    };
    return {
      page,
      setPage,
      disablePage,
      disableFilter,
      touchPage,
      touchFilter,
      setPageDisable,
      setFilterDisable,
      filter,
      setFilter,
    };
  },
);
