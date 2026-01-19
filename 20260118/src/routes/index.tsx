import React from "react";
import { createRoute } from "@tanstack/react-router";
import { rootRoute } from "./__root";
import { formatKoreanDate, getTodayDate } from "../lib/getToday";

function HomePage() {
  const todayText = formatKoreanDate(getTodayDate());

  return (
    <main className="flex min-h-screen items-center justify-center bg-slate-950 text-slate-100">
      <div className="rounded-3xl bg-slate-900/40 px-8 py-6 text-center shadow-lg shadow-slate-900/80">
        <h1 className="text-3xl font-semibold text-white">오늘 날짜 캘린더</h1>
        <p className="mt-2 text-lg text-slate-100">{todayText}</p>
        <p className="mt-3 text-sm text-slate-300">
          오늘 날짜를 보여주는 캘린더
        </p>
      </div>
    </main>
  );
}

export const homeRoute = createRoute({
  getParentRoute: () => rootRoute,
  path: "/",
  component: HomePage,
});
