<template>
  <q-layout view="lHh Lpr fff">
    <q-page-container>
      <q-page
        class="window-height window-width row justify-center items-center"
        style="background: linear-gradient(#009FFD, #2A2A72);"
      >
        <div class="column q-pa-lg">
          <div class="row">
            <q-card class="shadow-24" square style="width:400px;height:540px;">
              <q-card-section class="bg-blue-9">
                <h4 class="text-h5 text-white q-my-md">Tramonto Security</h4>
              </q-card-section>
              <q-card-section>
                <q-form  ref="myForm" class="q-px-sm q-pt-xl">
                  <q-input
                    ref="email"
                    v-model="data.email"
                    :rules="[ val => val && val.length > 0 || 'Please insert your e-mail']"
                    label="Email"
                    lazy-rules
                    outlined
                    type="email">
                  </q-input>
                  <q-input
                    ref="password"
                    v-model="data.password"
                    :rules="[ val => val && val.length > 0 || 'Please insert your password']"
                    label="Password"
                    type="password"
                    lazy-rules
                    outlined
                  />
                </q-form>
              </q-card-section>
              <q-card-actions class="q-px-lg">
                <q-btn
                  :label="data.btnLabel"
                  class="full-width text-white"
                  color="primary"
                  size="lg"
                  unelevated @click="submit"/>
              </q-card-actions>
              <q-card-section
                class="text-center q-pa-sm">
                <p class="text-grey-6">Forgot your password?</p>
              </q-card-section>
            </q-card>
          </div>
        </div>
      </q-page>
    </q-page-container>
  </q-layout>
</template>
<script>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthenticationStore } from '../stores/authentication.store';

export default {
  name: 'LoginLayout',
  setup() {
    const data = ref({
      email: '',
      username: '',
      password: '',
      btnLabel: 'Log in',
    });
    const myForm = ref(null);
    const store = useAuthenticationStore();
    const $router = useRouter();
    function submit() {
      myForm.value.validate().then(async (success) => {
        if (success) {
          const loginResult = await store.login(data.value.email, data.value.password);
          if (loginResult) {
            const from = $router.currentRoute.value.query.redirect;
            await $router.push(from === undefined ? '/' : from);
          }
        }
      });
    }
    return {
      data,
      myForm,
      submit,
    };
  },
};
</script>
