import BaseDto from 'stores/dtos/Base.dto';
import Role from 'stores/dtos/Role';

export class UserDto implements BaseDto {
  email = '';

  firstName = '';

  lastName = '';

  roles: Array<Role> = [];

  check = false;

  createdAt = '';

  id = '';

  status = '';

  updatedAt = '';
}
