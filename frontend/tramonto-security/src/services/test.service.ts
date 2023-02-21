import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import TestDto from 'src/services/dtos/Test.dto';
import { TestVector } from 'components/test/Test';

class TestService {
  // eslint-disable-next-line class-methods-use-this
  findById(uuid: string): Promise<AxiosResponse<TestDto>> {
    return api.get(`/v1/test/${uuid}`);
  }

  // eslint-disable-next-line class-methods-use-this
  addVector(testId: string, vector: TestVector): Promise<AxiosResponse<TestVector>> {
    return api.post(`/v1/test/addVector?testId=${testId}`, vector);
  }

  // eslint-disable-next-line class-methods-use-this
  save(test: TestDto): Promise<AxiosResponse<TestDto>> {
    return api.post('/v1/test', test);
  }

  // eslint-disable-next-line class-methods-use-this
  update(test: TestDto): Promise<AxiosResponse<TestDto>> {
    return api.put('/v1/test', test);
  }

  // eslint-disable-next-line class-methods-use-this
  paginate(
    filter: string,
    page: number,
    perPage: number,
    sortBy: string,
    direction: string,
  ): Promise<AxiosResponse<any>> {
    return api.get(`/v1/test?filter=${filter}&page=${page}&perPage=${perPage}&sortBy=${sortBy}&direction=${direction}`);
  }
}

export default new TestService();
