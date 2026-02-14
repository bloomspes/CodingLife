import { useMemo, useState } from "react";

const WEEKDAY_LABELS = ["일", "월", "화", "수", "목", "금", "토"];

type CalendarDay = {
  date: Date;
  inCurrentMonth: boolean;
  isToday: boolean;
};

function isSameDay(left: Date, right: Date) {
  return (
    left.getFullYear() === right.getFullYear() &&
    left.getMonth() === right.getMonth() &&
    left.getDate() === right.getDate()
  );
}

function addMonths(date: Date, amount: number) {
  return new Date(date.getFullYear(), date.getMonth() + amount, 1);
}

function buildMonthMatrix(baseDate: Date, today: Date) {
  const monthStart = new Date(baseDate.getFullYear(), baseDate.getMonth(), 1);
  const monthEnd = new Date(baseDate.getFullYear(), baseDate.getMonth() + 1, 0);

  const gridStart = new Date(
    baseDate.getFullYear(),
    baseDate.getMonth(),
    1 - monthStart.getDay(),
  );
  const gridEnd = new Date(
    baseDate.getFullYear(),
    baseDate.getMonth() + 1,
    0 + (6 - monthEnd.getDay()),
  );

  const days: CalendarDay[] = [];
  let cursor = gridStart;

  while (cursor <= gridEnd) {
    days.push({
      date: cursor,
      inCurrentMonth: cursor.getMonth() === baseDate.getMonth(),
      isToday: isSameDay(cursor, today),
    });

    cursor = new Date(
      cursor.getFullYear(),
      cursor.getMonth(),
      cursor.getDate() + 1,
    );
  }

  const weeks: CalendarDay[][] = [];
  for (let index = 0; index < days.length; index += 7) {
    weeks.push(days.slice(index, index + 7));
  }

  return weeks;
}

export default function MonthCalendar() {
  const today = useMemo(() => new Date(), []);
  const [baseDate, setBaseDate] = useState(
    new Date(today.getFullYear(), today.getMonth(), 1),
  );

  const monthLabel = `${baseDate.getFullYear()}년 ${baseDate.getMonth() + 1}월`;
  const weeks = useMemo(
    () => buildMonthMatrix(baseDate, today),
    [baseDate, today],
  );

  return (
    <section
      className="mx-auto flex min-h-screen w-full max-w-5xl flex-col px-6 py-10"
      data-testid="month-calendar"
    >
      <header className="mb-8 flex flex-wrap items-center justify-between gap-4">
        <div>
          <p className="text-xs font-semibold uppercase tracking-[0.3em] text-slate-400">
            Calendar
          </p>
          <h1 className="mt-2 text-3xl font-semibold text-slate-900">
            {monthLabel}
          </h1>
        </div>
        <div className="flex flex-wrap items-center gap-2">
          <button
            className="rounded-full border border-slate-200 bg-white px-3 py-2 text-sm text-slate-600"
            type="button"
            onClick={() => setBaseDate((prev) => addMonths(prev, -1))}
          >
            이전
          </button>
          <button
            className="rounded-full border border-slate-200 bg-white px-3 py-2 text-sm text-slate-600"
            type="button"
            onClick={() => setBaseDate((prev) => addMonths(prev, 1))}
          >
            다음
          </button>
          <button
            className="rounded-full bg-slate-900 px-4 py-2 text-sm font-semibold text-white"
            type="button"
            onClick={() =>
              setBaseDate(new Date(today.getFullYear(), today.getMonth(), 1))
            }
          >
            오늘
          </button>
        </div>
      </header>
      <div className="flex-1 rounded-3xl border border-slate-200 bg-white/80 p-6 shadow-[0_20px_60px_-45px_rgba(15,23,42,0.4)]">
        <div className="grid gap-4" role="grid">
          <div
            className="grid grid-cols-7 text-xs font-semibold text-slate-400"
            role="row"
            aria-label="weekday-row"
          >
            {WEEKDAY_LABELS.map((label) => (
              <div key={label} className="text-center" role="columnheader">
                {label}
              </div>
            ))}
          </div>
          <div className="grid gap-2">
            {weeks.map((week, weekIndex) => (
              <div
                key={`week-${weekIndex}`}
                className="grid grid-cols-7 gap-2"
                role="row"
              >
                {week.map((day) => (
                  <div
                    key={day.date.toISOString()}
                    className={`flex h-12 items-center justify-center rounded-2xl text-sm ${
                      day.isToday
                        ? "bg-slate-900 text-white"
                        : day.inCurrentMonth
                          ? "bg-slate-50 text-slate-900"
                          : "text-slate-300"
                    }`}
                    role="gridcell"
                    aria-current={day.isToday ? "date" : undefined}
                  >
                    {day.date.getDate()}
                  </div>
                ))}
              </div>
            ))}
          </div>
        </div>
      </div>
    </section>
  );
}
