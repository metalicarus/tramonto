import { defineStore } from 'pinia';

export const useAggressionStore = defineStore('aggressions', () => {
  // state
  const aggressions = [
    {
      label: 'High',
      value: 'HIGH',
    },
    {
      label: 'Medium',
      value: 'MEDIUM',
    },
    {
      label: 'Low',
      value: 'LOW',
    },
  ];
  return {
    aggressions,
  };
});
