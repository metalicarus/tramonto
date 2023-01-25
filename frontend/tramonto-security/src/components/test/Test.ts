import { date } from 'quasar';
import { StrategyDto } from 'src/services/dtos/StrategyInput.dto';
import { ToolDto } from 'src/services/dtos/Tool.dto';
import TestDto from 'src/services/dtos/Test.dto';
import { TestObjective } from 'stores/dtos/TestObjective.dto';
import { Checklist } from 'stores/dtos/Checklist.dto';

export class VectorCateogry {
  id = '';

  vectorCategory = '';

  description = '';

  status = '';
}

export class TestVector {
  id = '';

  title = '';

  description = '';

  expectedResults = '';

  resultsObtained = '';

  vectorCategory = new VectorCateogry();

  reproducibility = 0;

  impact = 0;

  testId = '';

  createdAt = '';

  updatedAt = '';
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

  checklists: Array<Checklist> = [];

  strategies: Array<StrategyDto> = [];

  tools: Array<ToolDto> = [];

  vectors: Array<TestVector> = [];

  objectives: Array<TestObjective> = [];

  constructor() {
    this.initialAndFinalDate.from = date.formatDate(Date.now(), 'YYYY-MM-DD');
    this.objectives.push(new TestObjective());
    this.addVector();
  }

  formatInitialAndFinalDate(): string {
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
