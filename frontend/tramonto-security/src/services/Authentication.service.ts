import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import UserDetailDto from 'src/services/dtos/UserDetail.dto';

class AuthenticationService {
  // eslint-disable-next-line class-methods-use-this
  login(email: string, password: string): Promise<AxiosResponse<any>> {
    return api.post('v1/auth/authenticate', {
      email,
      password,
    });
  }

  // eslint-disable-next-line class-methods-use-this
  findUserByToken(token: string): Promise<AxiosResponse<UserDetailDto>> {
    return api.get(`v1/auth?token=${token}`);
  }
}
export default new AuthenticationService();
