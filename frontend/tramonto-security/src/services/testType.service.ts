import { AxiosResponse } from 'axios';
import TestTypeDto from 'src/services/dtos/TestType.dto';
import { api } from 'boot/axios';

class TestTypeService {
  // eslint-disable-next-line class-methods-use-this
  findAll(): Promise<AxiosResponse<TestTypeDto[]>> {
    return api.get(`/v1/test-type`);
  }
}
export default new TestTypeService();
