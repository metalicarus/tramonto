import { boot } from 'quasar/wrappers';
import { Cookies, Notify } from 'quasar';
import { useAuthenticationStore } from 'stores/authentication.store';
import { toRaw } from 'vue';
import { LOGIN_PAGE, UNAUTHORIZED } from 'src/consts/RoutesConsts';

export default boot(({
  router,
}) => {
  router.beforeEach(async (to, from, next) => {
    const $authStore = useAuthenticationStore();
    const tramontoSecurityAccessToken = Cookies.get('tramontoSecurityAccessToken');
    if (to.query.redirect === 'login') {
      to.query.redirect = '/';
    }
    if (to.path === UNAUTHORIZED) {
      next();
      return;
    }
    if (tramontoSecurityAccessToken === null && to.name !== 'login') {
      next({
        path: LOGIN_PAGE,
        query: { redirect: to.fullPath },
      });
      return;
    }
    if (tramontoSecurityAccessToken !== null && to.name === 'login') {
      next({ path: to.query.redirect });
      return;
    }
    if (tramontoSecurityAccessToken !== null
      && $authStore.$state.token === undefined) {
      await $authStore.setToken(tramontoSecurityAccessToken);
      await $authStore.findUser();
    } else if (to.path !== '/login' && to.path !== '/') {
      const $user = $authStore.user;
      const roles = toRaw($user?.roles);
      const isAuthorized = roles?.some((role) => role.routes.includes(to.path));
      if (!isAuthorized) {
        Notify.create({
          message: 'You are not authorized to access this route',
          type: 'warning',
        });
        next({ path: UNAUTHORIZED });
        return;
      }
    }
    next();
  });
});
