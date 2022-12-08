import { date } from 'quasar';

export class TestChecklist {
  uuidChecklist = '';
}
export class TestObjective {
  uuid = '';

  objective = '';
}

class Test {
  uuid = '';

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

  constructor() {
    this.initialAndFinalDate.from = date.formatDate(Date.now(), 'YYYY-MM-DD');
    this.addObjective();
  }

  formatInitialAndFinalDate(): string {
    return `From: ${this.initialAndFinalDate.from} To: ${this.initialAndFinalDate.to}`;
  }

  addObjective(): void {
    this.objectives.push(new TestObjective());
  }

  removeObjective(index: number): void {
    this.objectives = this.objectives.filter((x, y) => index !== y);
  }
}
export default new Test();
