import { defineStore } from 'pinia';
import { Ref, ref } from 'vue';
import Test from 'components/test/Test';
import TestService from 'src/services/test.service';
import TestDto from 'src/services/dtos/Test.dto';

export const useTestStore = defineStore('tests', () => {
  // state
  const test: Ref<TestDto> = ref(Test);
  // actions
  const save = () => {
    TestService.save(test.value).then((response) => {
      console.log('sucesso');
      console.log(response);
    }).catch((response) => {
      console.log('errou');
      console.log(response);
    });
  };
  return {
    test,
    save,
  };
});
