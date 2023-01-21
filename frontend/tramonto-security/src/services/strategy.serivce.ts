import { api } from 'boot/axios';
import { AxiosResponse } from 'axios';
import { StrategyDto } from 'src/services/dtos/StrategyInput.dto';

class StrategyService {
  // eslint-disable-next-line class-methods-use-this
  findAll(): Promise<AxiosResponse<StrategyDto[]>> {
    return api.get(`/v1/strategy/findAll`);
  }

  // eslint-disable-next-line class-methods-use-this
  findById(uuid: string): Promise<AxiosResponse<any>> {
    return api.get(`/v1/strategy/${uuid}`);
  }

  // eslint-disable-next-line class-methods-use-this
  save: (strategy: StrategyDto) => Promise<AxiosResponse<any>> = (strategy: StrategyDto) => api.put('/v1/strategy', strategy);

  // eslint-disable-next-line class-methods-use-this
  paginate(
    filter: string,
    page: number,
    perPage: number,
    sortBy: string,
    direction: string,
  ): Promise<AxiosResponse<any>> {
    return api.get(`/v1/strategy?filter=${filter}&page=${page}&perPage=${perPage}&sortBy=strategy&direction=ASC`);
  }
}
export default new StrategyService();
