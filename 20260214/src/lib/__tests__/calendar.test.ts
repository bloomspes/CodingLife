import { describe, expect, it } from "vitest";
import { buildMonthMatrix, getMonthLabel } from "../calendar";

describe("calendar utilities", () => {
  it("builds a matrix starting on Sunday and ending on Saturday", () => {
    const baseDate = new Date(2026, 1, 1);
    const today = new Date(2026, 1, 14);

    const weeks = buildMonthMatrix(baseDate, today);

    expect(weeks.length).toBeGreaterThanOrEqual(4);
    expect(weeks[0][0].date.getDay()).toBe(0);
    expect(weeks[weeks.length - 1][6].date.getDay()).toBe(6);
  });

  it("marks today within the grid", () => {
    const baseDate = new Date(2026, 1, 1);
    const today = new Date(2026, 1, 14);

    const weeks = buildMonthMatrix(baseDate, today);

    const todayCell = weeks
      .flat()
      .find((day) => day.date.getDate() === 14 && day.inCurrentMonth);

    expect(todayCell?.isToday).toBe(true);
  });

  it("formats the month label", () => {
    const label = getMonthLabel(new Date(2026, 1, 1));

    expect(label).toBe("2026년 2월");
  });
});
