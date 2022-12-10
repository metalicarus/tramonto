import { AxiosResponse } from 'axios';
import { api } from 'boot/axios';
import { ToolDto } from 'src/services/dtos/Tool.dto';

class ToolService {
  // eslint-disable-next-line class-methods-use-this
  findAll(): Promise<AxiosResponse<ToolDto[]>> {
    return api.get('/v1/checklist/findAll');
  }
}
export default new ToolService();
