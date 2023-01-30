import Autority from 'src/services/dtos/Autority';

export default interface UserDetailDto {
  username: string;
  status: string;
  id: string;
  authorities: Array<Autority>;
  check: false;
};
