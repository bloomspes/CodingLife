package org.example.programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskScheduler {
    private static class Task {
        String name;

        int start;
        int duration;

        public Task(String name, int start, int duration) {
            this.name = name;
            this.start = start;
            this.duration = duration;
        }
    }

    private int convertToMinutes(String time) {
        String[] splitTimes = time.split(":");

        int hours = Integer.parseInt(splitTimes[0]);
        int minutes = Integer.parseInt(splitTimes[1]);

        return hours * 60 + minutes;
    }

    public String[] solution(String[][] plans) {
        List<Task> stack = new ArrayList<>();

        List<Task> sortedPlans = new ArrayList<>();

        for (String[] plan : plans) {
            String subject = plan[0];

            int time = convertToMinutes(plan[1]);
            int count = Integer.parseInt(plan[2]);

            sortedPlans.add(new Task(subject, time, count));
        }

        sortedPlans.sort(Comparator.comparingInt((Task t) -> t.start).reversed());

        while (!sortedPlans.isEmpty()) {
            Task currentPlan = sortedPlans.remove(sortedPlans.size() - 1);

            for (Task task : stack) {
                if (currentPlan.start < task.start) {
                    task.start += currentPlan.duration;
                }
            }

            stack.add(new Task(currentPlan.name, currentPlan.start + currentPlan.duration, 0));
        }

        stack.sort(Comparator.comparingInt((Task t) -> t.start));

        List<String> names = new ArrayList<>();

        for (Task task : stack) {
            names.add(task.name);
        }

        return names.toArray(new String[0]);
    }
}
