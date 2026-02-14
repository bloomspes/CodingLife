import { expect, test } from "@playwright/test";

test.beforeEach(async ({ page }) => {
  const fixed = new Date(2026, 1, 14, 12).getTime();

  await page.addInitScript((timestamp) => {
    const fixedTime = timestamp;
    const OriginalDate = Date;

    class FixedDate extends OriginalDate {
      constructor(...args: ConstructorParameters<typeof Date>) {
        if (args.length === 0) {
          super(fixedTime);
        } else {
          super(...args);
        }
      }

      static now() {
        return fixedTime;
      }
    }

    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    (window as any).Date = FixedDate;
  }, fixed);
});

test("initial render shows current month and today highlight", async ({
  page,
}) => {
  await page.goto("/");

  await expect(page.getByRole("heading", { name: "2026년 2월" })).toBeVisible();
  await expect(page.getByRole("button", { name: "오늘" })).toBeVisible();
  await expect(page.getByRole("gridcell", { name: "14" })).toHaveAttribute(
    "aria-current",
    "date",
  );
});

test("navigates previous/next month and returns to today", async ({ page }) => {
  await page.goto("/");

  await page.getByRole("button", { name: "이전" }).click();
  await expect(page.getByRole("heading", { name: "2026년 1월" })).toBeVisible();

  await page.getByRole("button", { name: "다음" }).click();
  await expect(page.getByRole("heading", { name: "2026년 2월" })).toBeVisible();

  await page.getByRole("button", { name: "다음" }).click();
  await expect(page.getByRole("heading", { name: "2026년 3월" })).toBeVisible();

  await page.getByRole("button", { name: "오늘" }).click();
  await expect(page.getByRole("heading", { name: "2026년 2월" })).toBeVisible();
});

test("handles year boundary navigation", async ({ page }) => {
  await page.goto("/");

  await page.getByRole("button", { name: "이전" }).click();
  await page.getByRole("button", { name: "이전" }).click();

  await expect(
    page.getByRole("heading", { name: "2025년 12월" }),
  ).toBeVisible();

  await page.getByRole("button", { name: "다음" }).click();
  await expect(page.getByRole("heading", { name: "2026년 1월" })).toBeVisible();
});
