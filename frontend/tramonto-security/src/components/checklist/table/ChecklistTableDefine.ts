export const RowKey = 'checklist';
export const Columns = [
  {
    name: 'id',
    label: 'ID',
    field: 'id',
    align: 'left',
  },
  {
    name: 'checklist',
    label: 'Checklist',
    field: 'checklist',
    align: 'left',
  },
  {
    name: 'status',
    label: 'Status',
    field: 'status',
    align: 'left',
  },
  {
    name: 'createdAt',
    label: 'Created At',
    field: 'createdAt',
    align: 'left',
    format: (val: string) => `${val.split('T')[0].replace(/(\d{4})-(\d{2})-(\d{2}).*/, '$3/$2/$1')} ${val.split('T')[1]}`,
  },
  {
    name: 'updatedAt',
    label: 'Updated At',
    field: 'updatedAt',
    align: 'left',
    format: (val: string) => `${val.split('T')[0].replace(/(\d{4})-(\d{2})-(\d{2}).*/, '$3/$2/$1')} ${val.split('T')[1]}`,
  },
  {
    name: 'actions',
    label: 'Actions',
    field: '',
    align: 'left',
  },
];
