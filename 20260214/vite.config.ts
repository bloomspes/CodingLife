import { defineConfig } from "vitest/config";
import react from "@vitejs/plugin-react";
import tailwindcss from "@tailwindcss/vite";

// https://vite.dev/config/
export default defineConfig({
  plugins: [tailwindcss(), react()],
  test: {
    environment: "jsdom",
    globals: true,
    setupFiles: "./src/test/setup.ts",
    include: ["src/**/*.test.{ts,tsx}", "tests/units/**/*.test.{ts,tsx}"],
    exclude: ["tests/e2e/**"],
  },
  server: {
    host: "127.0.0.1",
    port: 4173,
    strictPort: true,
  },
});
