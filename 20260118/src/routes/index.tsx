import React, { useMemo, useState } from "react";
import { createRoute } from "@tanstack/react-router";
import { rootRoute } from "./__root";
import { formatKoreanDate, getTodayDate } from "../lib/getToday";
import { MonthGrid } from "../components/MonthGrid";
import { getMonthDays, getMonthStartWeekday } from "../lib/getMonthDays";

function HomePage() {
  const todayDate = getTodayDate();
  const todayText = formatKoreanDate(todayDate);
  const [currentYear, setCurrentYear] = useState(todayDate.getFullYear());
  const [currentMonth, setCurrentMonth] = useState(todayDate.getMonth() + 1);
  const { days, startWeekday } = useMemo(() => {
    return {
      days: getMonthDays(currentYear, currentMonth),
      startWeekday: getMonthStartWeekday(currentYear, currentMonth),
    };
  }, [currentYear, currentMonth]);
  const todayInCurrentMonth =
    currentYear === todayDate.getFullYear() &&
    currentMonth === todayDate.getMonth() + 1
      ? todayDate.getDate()
      : null;
  const handlePrevMonth = () => {
    if (currentMonth === 1) {
      setCurrentYear((value) => value - 1);
      setCurrentMonth(12);
      return;
    }
    setCurrentMonth((value) => value - 1);
  };
  const handleNextMonth = () => {
    if (currentMonth === 12) {
      setCurrentYear((value) => value + 1);
      setCurrentMonth(1);
      return;
    }
    setCurrentMonth((value) => value + 1);
  };

  return (
    <main className="flex min-h-screen items-center justify-center bg-slate-950 text-slate-100">
      <div className="flex w-full max-w-2xl flex-col gap-6 rounded-3xl bg-slate-900/40 px-8 py-6 text-center shadow-lg shadow-slate-900/80">
        <header>
          <h1 className="text-3xl font-semibold text-white">오늘 날짜 캘린더</h1>
          <div className="mt-3 flex items-center justify-center gap-3">
            <button
              type="button"
              className="rounded-full border border-slate-700 px-3 py-1.5 text-xs text-slate-200"
              onClick={handlePrevMonth}
            >
              이전 달
            </button>
            <p className="text-base font-semibold text-slate-200">
              {currentYear}년 {currentMonth}월
            </p>
            <button
              type="button"
              className="rounded-full border border-slate-700 px-3 py-1.5 text-xs text-slate-200"
              onClick={handleNextMonth}
            >
              다음 달
            </button>
          </div>
        </header>
        <div className="flex items-center justify-center">
          <div className="rounded-2xl border border-slate-700 bg-slate-950/60 px-6 py-4">
            <p className="text-sm font-semibold text-slate-400">오늘 날짜</p>
            <p className="mt-1 text-xl font-semibold text-white">{todayText}</p>
          </div>
        </div>
        <MonthGrid
          days={days}
          startWeekday={startWeekday}
          today={todayInCurrentMonth}
        />
      </div>
    </main>
  );
}

export const homeRoute = createRoute({
  getParentRoute: () => rootRoute,
  path: "/",
  component: HomePage,
});
