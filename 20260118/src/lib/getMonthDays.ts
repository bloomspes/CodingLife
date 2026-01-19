export const getMonthDays = (year: number, month: number): number[] => {
  const totalDays = new Date(year, month, 0).getDate();
  return Array.from({ length: totalDays }, (_, index) => index + 1);
};

export const getMonthStartWeekday = (year: number, month: number): number => {
  return new Date(year, month - 1, 1).getDay();
};
