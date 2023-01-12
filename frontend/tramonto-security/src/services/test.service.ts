import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import TestDto from 'src/services/dtos/Test.dto';

class TestService {
  // eslint-disable-next-line class-methods-use-this
  save(test: TestDto): Promise<AxiosResponse<any>> {
    return api.post('/v1/test', test);
  }

  // eslint-disable-next-line class-methods-use-this
  paginate(
    filter: string,
    page: number,
    perPage: number,
    sortBy: string,
    direction: string,
  ): Promise<AxiosResponse<any>> {
    return api.get(`/v1/test?filter=${filter}&page=${page}&perPage=${perPage}&sortBy=vectorCategory&direction=ASC`);
  }
}
export default new TestService();
