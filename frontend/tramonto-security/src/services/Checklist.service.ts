import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import { Checklist } from 'stores/dtos/Checklist.dto';

class ChecklistService {
  // eslint-disable-next-line class-methods-use-this
  async findAll(): Promise<AxiosResponse<Checklist[]>> {
    return api.get('/v1/checklist/findAll');
  }

  // eslint-disable-next-line class-methods-use-this
  findById(uuid: string): Promise<AxiosResponse<any>> {
    return api.get(`/v1/checklist/${uuid}`);
  }

  // eslint-disable-next-line class-methods-use-this
  save: (strategy: Checklist) => Promise<AxiosResponse<any>> = (checklist: Checklist) => api.put('/v1/checklist', checklist);

  // eslint-disable-next-line class-methods-use-this
  paginate(
    filter: string,
    page: number,
    perPage: number,
    sortBy: string,
    direction: string,
  ): Promise<AxiosResponse<any>> {
    return api.get(`/v1/checklist?filter=${filter}&page=${page}&perPage=${perPage}&sortBy=checklist&direction=ASC`);
  }
}
export default new ChecklistService();
