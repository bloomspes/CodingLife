import { render, screen } from "@testing-library/react";
import App from "./App";

describe("App", () => {
  it("renders the month calendar view", () => {
    render(<App />);

    expect(screen.getByTestId("month-calendar")).toBeInTheDocument();
  });
});
