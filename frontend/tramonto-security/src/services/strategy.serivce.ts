import { api } from 'boot/axios';
import StrategyInputDto from 'src/services/dtos/StrategyInput.dto';
import { AxiosResponse } from 'axios';

class StrategySerivce {
  // eslint-disable-next-line class-methods-use-this
  save: (strategy: StrategyInputDto) => Promise<AxiosResponse<any>> = (strategy: StrategyInputDto) => api.put('/v1/strategy', strategy);

  // eslint-disable-next-line class-methods-use-this
  paginate(): Promise<AxiosResponse<any>> {
    return api.get('v1/strategy?filter=&page=0&perPage=10&sortBy=strategy&direction=ASC');
  }
}
export default new StrategySerivce();
