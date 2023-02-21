import { date } from 'quasar';
import { StrategyDto } from 'src/services/dtos/StrategyInput.dto';
import { ToolDto } from 'src/services/dtos/Tool.dto';
import TestDto from 'src/services/dtos/Test.dto';
import { TestObjective } from 'stores/dtos/TestObjective.dto';
import { Checklist } from 'stores/dtos/Checklist.dto';
import { UserDto } from 'stores/dtos/UserDto';
import BaseDto from 'stores/dtos/Base.dto';

export class VectorCateogry {
  id = '';

  vectorCategory = '';

  description = '';

  status = '';
}

export class TestVector implements BaseDto {
  id = '';

  title = '';

  description = '';

  expectedResults = '';

  resultsObtained = '';

  category = new VectorCateogry();

  reproducibility = 0;

  impact = 0;

  testId = '';

  approval = '';

  observationApproval = '';

  createdAt = '';

  updatedAt = '';

  status = 'ACTIVE';
}

class Test implements TestDto {
  // eslint-disable-next-line no-unreachable
  id = '';

  title = '';

  description = '';

  initialDate = '';

  finalDate = '';

  initialAndFinalDate = {
    to: '',
    from: '',
  };

  estimatedTime = '';

  retest = false;

  retestDate = '';

  type = '';

  approach = '';

  aggression = '';

  generalObservation = '';

  belongsToCurrentUser = true;

  checklists: Array<Checklist> = [];

  strategies: Array<StrategyDto> = [];

  tools: Array<ToolDto> = [];

  vectors: Array<TestVector> = [];

  objectives: Array<TestObjective> = [];

  testers: Array<UserDto> = [];

  constructor() {
    this.initialAndFinalDate.from = date.formatDate(Date.now(), 'YYYY-MM-DD');
    this.objectives.push(new TestObjective());
    this.vectors.push(new TestVector());
  }

  formatInitialAndFinalDate(): string {
    // eslint-disable-next-line no-constant-condition
    if (this.id !== null || true) {
      return `From: ${this.initialDate} To: ${this.finalDate}`;
    }
    return `From: ${this.initialAndFinalDate.from} To: ${this.initialAndFinalDate.to}`;
  }

  updateDates(): void {
    this.initialDate = date.formatDate(this.initialAndFinalDate.from, 'YYYY-MM-DDTHH:mm:ss');
    this.finalDate = date.formatDate(this.initialAndFinalDate.to, 'YYYY-MM-DDTHH:mm:ss');
  }

  addVector(): void {
    this.vectors.push(new TestVector());
  }

  removeVector(index: number): void {
    this.vectors = this.vectors.filter((x, y) => index !== y);
  }
}

export default new Test();
