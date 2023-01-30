import BaseDto from 'stores/dtos/Base.dto';

export default interface Role extends BaseDto {
  authority: string;
  role: string;
  routes: Array<string>;
};
