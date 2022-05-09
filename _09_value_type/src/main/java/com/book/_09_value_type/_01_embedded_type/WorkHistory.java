package com.book._09_value_type._01_embedded_type;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public final class WorkHistory {

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public WorkHistory() {
    }

    public WorkHistory(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
