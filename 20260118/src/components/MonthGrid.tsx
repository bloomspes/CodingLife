import React from "react";

type MonthGridProps = {
  days: number[];
  startWeekday: number;
  today: number | null;
};

function getWeekdayLabels() {
  return ["일", "월", "화", "수", "목", "금", "토"];
}

export function MonthGrid({ days, startWeekday, today }: MonthGridProps) {
  const weekLabels = getWeekdayLabels();
  const leading = Array.from({ length: startWeekday });

  return (
    <div className="w-full max-w-xl">
      <div className="grid grid-cols-7 gap-2 text-center text-xs font-semibold text-slate-400">
        {weekLabels.map((label) => (
          <div key={label}>{label}</div>
        ))}
      </div>
      <div className="mt-3 grid grid-cols-7 gap-2 text-center">
        {leading.map((_, index) => (
          <div key={`empty-${index}`} />
        ))}
        {days.map((day) => {
          const isToday = today === day;
          return (
            <div
              key={day}
              className={[
                "rounded-xl border border-slate-800 px-2 py-3 text-sm",
                isToday
                  ? "border-sky-400 bg-sky-500/20 text-sky-100"
                  : "bg-slate-900/50 text-slate-200",
              ].join(" ")}
            >
              {day}
            </div>
          );
        })}
      </div>
    </div>
  );
}
