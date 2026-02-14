type CalendarDay = {
  date: Date;
  inCurrentMonth: boolean;
  isToday: boolean;
};

type CalendarGridProps = {
  weeks: CalendarDay[][];
};

export default function CalendarGrid({ weeks }: CalendarGridProps) {
  return (
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
  );
}
