import { fireEvent, render, screen, within } from "@testing-library/react";
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

  it("moves to the previous and next month from header controls", () => {
    render(<MonthCalendar />);

    fireEvent.click(screen.getByRole("button", { name: "이전" }));
    expect(
      screen.getByRole("heading", { name: "2026년 1월" }),
    ).toBeInTheDocument();

    fireEvent.click(screen.getByRole("button", { name: "다음" }));
    expect(
      screen.getByRole("heading", { name: "2026년 2월" }),
    ).toBeInTheDocument();
  });

  it("returns to the current month when clicking today", () => {
    render(<MonthCalendar />);

    fireEvent.click(screen.getByRole("button", { name: "다음" }));
    expect(
      screen.getByRole("heading", { name: "2026년 3월" }),
    ).toBeInTheDocument();

    fireEvent.click(screen.getByRole("button", { name: "오늘" }));
    expect(
      screen.getByRole("heading", { name: "2026년 2월" }),
    ).toBeInTheDocument();
  });
});
