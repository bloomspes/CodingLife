package org.example.programmers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskSchedulerTest {
    @Test
    @DisplayName("순차적으로 과제를 진행하는 케이스")
    void solution() {
        TaskScheduler taskScheduler = new TaskScheduler();

        String[][] plans = {
                {"korean", "11:40", "30"},
                {"english", "12:10", "20"},
                {"math", "12:30", "40"},
        };

        assertThat(taskScheduler.solution(plans))
                .isEqualTo(new String[]{"korean", "english", "math"});
    }

    @Test
    @DisplayName("중간에 과제를 진행하다가 멈추고 다음 과제 시작하는 케이스")
    void existsPausedTask() {
        TaskScheduler taskScheduler = new TaskScheduler();

        String[][] plans = {
                {"science", "12:40", "50"},
                {"music", "12:20", "40"},
                {"history", "14:30", "30"},
                {"computer", "12:30", "100"}
        };

        assertThat(taskScheduler.solution(plans))
                .isEqualTo(new String[]{"science", "history", "computer", "music"});
    }
}
