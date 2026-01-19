import { createRouter } from "@tanstack/react-router";
import { rootRoute } from "./__root";
import { homeRoute } from "./index";

export const router = createRouter({
  routeTree: rootRoute.addChildren([homeRoute]),
});
