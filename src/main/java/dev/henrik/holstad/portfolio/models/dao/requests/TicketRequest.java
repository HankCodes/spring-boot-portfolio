package dev.henrik.holstad.portfolio.models.dao.requests;

import java.util.Objects;

public record TicketRequest(String title, String description) {
    public TicketRequest {
        Objects.requireNonNull(title);
    }
}
