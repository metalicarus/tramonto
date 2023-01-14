import { boot } from 'quasar/wrappers';
import { useRouter } from 'vue-router';
import { LocalStorage } from 'quasar';

export default boot(({ router }) => {
  const tramontoSecurityAccessToken = LocalStorage.getItem('tramontoSecurityAccessToken');
  router.beforeEach((to, from) => {
    if (tramontoSecurityAccessToken === null) {
      return { name: 'Login' };
    }
  });
});
