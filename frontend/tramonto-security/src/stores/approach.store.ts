import { defineStore } from 'pinia';

export const useApproachStore = defineStore('approaches', () => {
  // state
  const approaches = [
    {
      label: 'Covert',
      value: 'COVERT',
    },
    {
      label: 'Overt',
      value: 'OVERT',
    },
  ];
  return {
    approaches,
  };
});
