import { defineStore } from 'pinia';
import { ref } from 'vue';
import Test from 'components/test/Test';

export const useTestStore = defineStore('tests', () => {
  // state
  const test = ref(Test);
  return {
    test,
  };
});
