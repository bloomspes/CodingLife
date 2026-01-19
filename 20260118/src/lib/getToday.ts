const dayNames = ["일", "월", "화", "수", "목", "금", "토"] as const;

export const getTodayDate = (): Date => new Date();

export const formatKoreanDate = (date: Date): string => {
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const dayName = dayNames[date.getDay()];

  return `${year}년 ${month}월 ${day}일 (${dayName})`;
};
