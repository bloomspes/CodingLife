import { useMemo, useState } from "react";
import CalendarGrid from "./CalendarGrid";
import {
  WEEKDAY_LABELS,
  buildMonthMatrix,
  getMonthBase,
  getMonthLabel,
  goNextMonth,
  goPrevMonth,
} from "../lib/calendar";

export default function MonthCalendar() {
  const today = useMemo(() => new Date(), []);
  const [baseDate, setBaseDate] = useState(getMonthBase(today));

  const monthLabel = getMonthLabel(baseDate);
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
            onClick={() => setBaseDate((prev) => goPrevMonth(prev))}
          >
            이전
          </button>
          <button
            className="rounded-full border border-slate-200 bg-white px-3 py-2 text-sm text-slate-600"
            type="button"
            onClick={() => setBaseDate((prev) => goNextMonth(prev))}
          >
            다음
          </button>
          <button
            className="rounded-full bg-slate-900 px-4 py-2 text-sm font-semibold text-white"
            type="button"
            onClick={() => setBaseDate(getMonthBase(today))}
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
          <CalendarGrid weeks={weeks} />
        </div>
      </div>
    </section>
  );
}
