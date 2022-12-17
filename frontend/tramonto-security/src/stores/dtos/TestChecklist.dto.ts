import { Checklist } from 'stores/dtos/Checklist.dto';

export class TestChecklistPk {
  checklist: Checklist = new Checklist();
}
export class TestChecklist {
  pk:TestChecklistPk = new TestChecklistPk();
}
