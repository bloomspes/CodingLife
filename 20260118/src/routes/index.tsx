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
          <p className="mt-2 text-base text-slate-300">
            {currentYear}년 {currentMonth}월
          </p>
          <p className="mt-2 text-base text-slate-100">{todayText}</p>
          <p className="mt-3 text-sm text-slate-300">
            오늘 날짜를 보여주는 캘린더
          </p>
        </header>
        <div className="flex flex-wrap items-center justify-center gap-3">
          <button
            type="button"
            className="rounded-full border border-slate-700 px-4 py-2 text-sm text-slate-200"
            onClick={handlePrevMonth}
          >
            이전 달
          </button>
          <button
            type="button"
            className="rounded-full border border-slate-700 px-4 py-2 text-sm text-slate-200"
            onClick={handleNextMonth}
          >
            다음 달
          </button>
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
