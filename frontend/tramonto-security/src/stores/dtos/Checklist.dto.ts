import BaseDto from 'stores/dtos/Base.dto';

export class Checklist implements BaseDto {
  id = '';

  checklist = '';

  description = '';

  status = '';

  createdAt = '';

  updatedAt = '';

  check = false;
}
