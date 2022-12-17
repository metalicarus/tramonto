import { date } from 'quasar';
import StrategyInputDto from 'src/services/dtos/StrategyInput.dto';
import { ToolDto } from 'src/services/dtos/Tool.dto';
import TestDto from 'src/services/dtos/Test.dto';
import { TestObjective } from 'stores/dtos/TestObjective.dto';
import { TestChecklist } from 'stores/dtos/TestChecklist.dto';

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
  id = '';

  title = '';

  description = '';

  objectives: Array<TestObjective> = [];

  initialDate = '';

  finalDate = '';

  initialAndFinalDate = { to: '', from: '' };

  estimatedTime = '';

  retest = false;

  retestDate = '';

  type = '';

  approach = '';

  aggression = '';

  generalObservation = '';

  checklists: Array<TestChecklist> = [];

  strategies: Array<StrategyInputDto> = [];

  tools: Array<ToolDto> = [];

  vectors: Array<TestVector> = [];

  constructor() {
    this.initialAndFinalDate.from = date.formatDate(Date.now(), 'YYYY-MM-DD');
    this.addObjective();
    this.addVector();
  }

  formatInitialAndFinalDate(): string {
    return `From: ${this.initialAndFinalDate.from} To: ${this.initialAndFinalDate.to}`;
  }

  updateDates(): void {
    this.initialDate = date.formatDate(this.initialAndFinalDate.from, 'YYYY-MM-DDTHH:mm:ss');
    this.finalDate = date.formatDate(this.initialAndFinalDate.to, 'YYYY-MM-DDTHH:mm:ss');
  }

  addObjective(): void {
    this.objectives.push(new TestObjective());
  }

  removeObjective(index: number): void {
    this.objectives = this.objectives.filter((x, y) => index !== y);
  }

  addVector(): void {
    this.vectors.push(new TestVector());
  }

  removeVector(index: number): void {
    this.vectors = this.vectors.filter((x, y) => index !== y);
  }
}
export default new Test();
