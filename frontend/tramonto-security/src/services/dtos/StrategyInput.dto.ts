import BaseDto from 'stores/dtos/Base.dto';

export class StrategyDto implements BaseDto {
  id = '';

  strategy = '';

  description = '';

  status = '';

  createdAt = '';

  updatedAt = '';

  check = false;
}
