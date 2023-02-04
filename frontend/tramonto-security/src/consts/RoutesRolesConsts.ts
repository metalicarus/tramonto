import { TESTS_SAVE } from 'src/consts/RoutesConsts';
import { ROLE_TESTER_ADVANCED, ROLE_TESTER_INTERMEDIARY } from 'src/consts/RolesConsts';

interface KeyValue {
  [key: string]: any
}
export const RoutesRolesConsts: KeyValue = {
  [TESTS_SAVE]: [ROLE_TESTER_ADVANCED, ROLE_TESTER_INTERMEDIARY],
};
