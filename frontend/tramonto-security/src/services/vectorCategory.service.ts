import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import VectorCategoryDto from 'src/services/dtos/VectorCategory.dto';

class VectorCategoryService {
  // eslint-disable-next-line class-methods-use-this
  findAll(): Promise<AxiosResponse<VectorCategoryDto[]>> {
    return api.get('/v1/vectorCategory/findAll');
  }

  // eslint-disable-next-line class-methods-use-this
  findById(uuid: string): Promise<AxiosResponse<any>> {
    return api.get(`/v1/vectorCategory/${uuid}`);
  }

  // eslint-disable-next-line class-methods-use-this
  save: (strategy: VectorCategoryDto) => Promise<AxiosResponse<any>> = (strategy: VectorCategoryDto) => api.put('/v1/vectorCategory', strategy);

  // eslint-disable-next-line class-methods-use-this
  paginate(
    filter: string,
    page: number,
    perPage: number,
    sortBy: string,
    direction: string,
  ): Promise<AxiosResponse<any>> {
    return api.get(`/v1/vectorCategory?filter=${filter}&page=${page}&perPage=${perPage}&sortBy=vectorCategory&direction=ASC`);
  }
}
export default new VectorCategoryService();
