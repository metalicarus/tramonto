import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import { UserDto } from 'stores/dtos/UserDto';

class AuthenticationService {
  // eslint-disable-next-line class-methods-use-this
  login(email: string, password: string): Promise<AxiosResponse<any>> {
    return api.post('v1/auth/authenticate', {
      email,
      password,
    });
  }

  // eslint-disable-next-line class-methods-use-this
  findUserByToken(token: string): Promise<AxiosResponse<UserDto>> {
    return api.get(`v1/auth?token=${token}`);
  }

  // eslint-disable-next-line class-methods-use-this
  async findDifferentUsers(): Promise<AxiosResponse<Array<UserDto>>> {
    return api.get(`v1/auth/differentUsers`);
  }
}

export default new AuthenticationService();
