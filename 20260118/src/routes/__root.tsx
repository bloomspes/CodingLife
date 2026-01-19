import React from "react";
import { Outlet, createRootRoute } from "@tanstack/react-router";

function RootLayout() {
  return (
    <div className="min-h-screen bg-slate-950 text-slate-100">
      <Outlet />
    </div>
  );
}

export const rootRoute = createRootRoute({
  component: RootLayout,
});
