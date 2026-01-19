export const getMonthDays = (year: number, month: number): number[] => {
  const totalDays = new Date(year, month, 0).getDate();
  return Array.from({ length: totalDays }, (_, index) => index + 1);
};
