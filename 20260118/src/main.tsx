import React from "react";
import ReactDOM from "react-dom/client";
import "./styles.css";

const App: React.FC = () => {
  return (
    <div className="min-h-screen flex items-center justify-center">
      <p className="text-slate-100 text-xl">초기 셋업 완료 전 상태</p>
    </div>
  );
};

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

