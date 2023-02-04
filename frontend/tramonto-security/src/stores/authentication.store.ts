import { defineStore } from 'pinia';
import AuthenticationService from 'src/services/Authentication.service';
import { Cookies, Notify } from 'quasar';
import { ref } from 'vue';
import { UserDto } from 'stores/dtos/UserDto';

export const useAuthenticationStore = defineStore('auth', {
  state: () => ({
    user: ref<UserDto | undefined>(),
    selectableUsers: ref<UserDto[]>([]),
    token: ref(),
  }),
  getters: {
    getToken: (state) => ((state.token === undefined)
      ? Cookies.get('tramontoSecurityAccessToken') : state.token),
  },
  actions: {
    userRolesContain(roles: Array<string>) {
      return this.user?.roles.some((x) => {
        const index = roles.findIndex((y) => y === x.role);
        return index !== -1;
      });
    },
    async findDifferentUsers() {
      await AuthenticationService.findDifferentUsers()
        .then((response) => {
          this.selectableUsers = response.data;
        })
        .catch(() => {
          Notify.create({
            message: 'Could not load user\'s information',
            type: 'negative',
          });
        });
    },
    setToken(newToken: string) {
      this.token = newToken;
      Cookies.set('tramontoSecurityAccessToken', newToken);
    },
    async findUser() {
      Notify.create({
        message: 'Welcome',
        type: 'positive',
      });
      await AuthenticationService.findUserByToken(this.getToken)
        .then((response) => {
          this.user = response.data;
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
    },
    login(email: string, password: string) {
      return AuthenticationService.login(email, password)
        .then(async (response) => {
          await this.setToken(response.data.token);
          await this.findUser();
          return true;
        })
        .catch(() => {
          Notify.create({
            message: 'Incorrect user or password',
            type: 'negative',
          });
          return false;
        });
    },
  },
});
