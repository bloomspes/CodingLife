import React from "react";
import { createRoute } from "@tanstack/react-router";
import { rootRoute } from "./__root";

function HomePage() {
  return (
    <main className="flex min-h-screen items-center justify-center bg-slate-950 text-slate-100">
      <div className="rounded-3xl bg-slate-900/40 px-8 py-6 text-center shadow-lg shadow-slate-900/80">
        <h1 className="text-3xl font-semibold text-white">오늘 날짜 캘린더</h1>
        <p className="mt-2 text-slate-300">TanStack Router 기본 진입점</p>
      </div>
    </main>
  );
}

export const homeRoute = createRoute({
  getParentRoute: () => rootRoute,
  path: "/",
  component: HomePage,
});
