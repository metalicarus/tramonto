import { defineStore } from 'pinia';
import AuthenticationService from 'src/services/Authentication.service';
import { Cookies, Notify } from 'quasar';
import { Ref, ref } from 'vue';
import UserDetailDto from 'src/services/dtos/UserDetail.dto';

export const useAuthenticationStore = defineStore('auth', () => {
  /* STATE */
  const user: Ref<UserDetailDto | undefined> = ref();
  const token = ref();
  const $service = AuthenticationService;
  /* ACTIONS */
  const setToken = (newToken: string) => {
    token.value = newToken;
    Cookies.set('tramontoSecurityAccessToken', newToken);
  };
  const getToken: () => string = () => ((token.value === undefined) ? Cookies.get('tramontoSecurityAccessToken') : token.value);
  // eslint-disable-next-line max-len
  const isAuthorized = (role: string) => false;
  const findUser = async () => {
    Notify.create({
      message: 'Welcome',
      type: 'positive',
    });
    await $service.findUserByToken(getToken())
      .then((response) => {
        user.value = response.data;
        Notify.create({
          message: 'User profile loaded successfully',
          type: 'positive',
        });
      })
      .catch(() => {
        Notify.create({
          message: 'Could not load user information',
          type: 'negative',
        });
      });
  };
  const login = async (email: string, password: string) => $service.login(email, password)
    .then(async (response) => {
      await setToken(response.data.token);
      await findUser();
      return true;
    })
    .catch(() => {
      Notify.create({
        message: 'Incorrect user or password',
        type: 'negative',
      });
      return false;
    });
  return {
    token,
    user,
    login,
    findUser,
    setToken,
    isAuthorized,
  };
});
