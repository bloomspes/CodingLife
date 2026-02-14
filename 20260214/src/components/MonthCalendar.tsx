export default function MonthCalendar() {
  return (
    <section
      className="mx-auto flex min-h-screen w-full max-w-5xl flex-col px-6 py-10"
      data-testid="month-calendar"
    >
      <header className="mb-8">
        <p className="text-sm uppercase tracking-[0.2em] text-slate-500">
          Calendar
        </p>
        <h1 className="mt-2 text-3xl font-semibold text-slate-900">
          Month View
        </h1>
      </header>
      <div className="flex-1 rounded-3xl border border-slate-200 bg-white/80 p-8 shadow-[0_20px_60px_-45px_rgba(15,23,42,0.4)]">
        <p className="text-base text-slate-600">달력 화면을 준비하고 있어요.</p>
      </div>
    </section>
  );
}
