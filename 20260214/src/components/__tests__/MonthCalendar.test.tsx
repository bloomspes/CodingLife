import { render, screen, within } from "@testing-library/react";
import { afterEach, beforeEach, describe, expect, it, vi } from "vitest";
import MonthCalendar from "../MonthCalendar";

describe("MonthCalendar", () => {
  beforeEach(() => {
    vi.useFakeTimers();
    vi.setSystemTime(new Date(2026, 1, 14));
  });

  afterEach(() => {
    vi.useRealTimers();
  });

  it("renders the current month label and today action", () => {
    render(<MonthCalendar />);

    expect(
      screen.getByRole("heading", { name: "2026년 2월" }),
    ).toBeInTheDocument();
    expect(screen.getByRole("button", { name: "오늘" })).toBeInTheDocument();
  });

  it("highlights today in the grid", () => {
    render(<MonthCalendar />);

    const todayCell = screen.getByRole("gridcell", { name: "14" });

    expect(todayCell).toHaveAttribute("aria-current", "date");
  });

  it("renders all weekday headers starting on Sunday", () => {
    render(<MonthCalendar />);

    const weekdayRow = screen.getByRole("row", { name: "weekday-row" });
    const labels = within(weekdayRow)
      .getAllByRole("columnheader")
      .map((node) => node.textContent);

    expect(labels).toEqual(["일", "월", "화", "수", "목", "금", "토"]);
  });
});
