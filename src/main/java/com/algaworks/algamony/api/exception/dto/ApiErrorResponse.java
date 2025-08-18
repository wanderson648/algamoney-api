package com.algaworks.algamony.api.exception.dto;

import java.time.Instant;

public record ApiErrorResponse(
        int status,
        String type,
        String title,
        Instant instant
) {
}
