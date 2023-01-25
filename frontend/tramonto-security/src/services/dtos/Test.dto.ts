import { Checklist } from 'stores/dtos/Checklist.dto';
import { StrategyDto } from 'src/services/dtos/StrategyInput.dto';
import { ToolDto } from 'src/services/dtos/Tool.dto';
import { TestObjective } from 'stores/dtos/TestObjective.dto';
import { TestVector } from 'components/test/Test';

export default interface TestDto {
  id: string;
  title: string;
  description: string;
  initialDate: string;
  finalDate: string;
  estimatedTime: string;
  retest: boolean;
  retestDate: string;
  type: string;
  approach: string;
  aggression: string;
  generalObservation: string;
  checklists: Array<Checklist>;
  strategies: Array<StrategyDto>;
  tools: Array<ToolDto>;
  vectors: Array<TestVector>;
  objectives: Array<TestObjective>;

  formatInitialAndFinalDate(): string;
};
