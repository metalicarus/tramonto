import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import TestDto from 'src/services/dtos/Test.dto';

class TestService {
  // eslint-disable-next-line class-methods-use-this
  save(test: TestDto): Promise<AxiosResponse<any>> {
    return api.put('/v1/test', test);
  }
}
export default new TestService();
