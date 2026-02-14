import {
  addDays,
  addMonths,
  endOfMonth,
  isSameDay,
  startOfMonth,
} from "./date";

export const WEEKDAY_LABELS = ["일", "월", "화", "수", "목", "금", "토"];

export type CalendarDay = {
  date: Date;
  inCurrentMonth: boolean;
  isToday: boolean;
};

export function getMonthLabel(date: Date) {
  return `${date.getFullYear()}년 ${date.getMonth() + 1}월`;
}

export function getMonthBase(date: Date) {
  return startOfMonth(date);
}

export function goPrevMonth(date: Date) {
  return addMonths(date, -1);
}

export function goNextMonth(date: Date) {
  return addMonths(date, 1);
}

export function buildMonthMatrix(baseDate: Date, today: Date) {
  const monthStart = startOfMonth(baseDate);
  const monthEnd = endOfMonth(baseDate);

  const gridStart = addDays(monthStart, -monthStart.getDay());
  const gridEnd = addDays(monthEnd, 6 - monthEnd.getDay());

  const days: CalendarDay[] = [];
  let cursor = gridStart;

  while (cursor <= gridEnd) {
    days.push({
      date: cursor,
      inCurrentMonth: cursor.getMonth() === baseDate.getMonth(),
      isToday: isSameDay(cursor, today),
    });

    cursor = addDays(cursor, 1);
  }

  const weeks: CalendarDay[][] = [];
  for (let index = 0; index < days.length; index += 7) {
    weeks.push(days.slice(index, index + 7));
  }

  return weeks;
}
