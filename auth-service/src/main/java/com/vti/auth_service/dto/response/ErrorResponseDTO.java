package com.vti.auth_service.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponseDTO {
    @NotNull(message = "status can not be null")
    private int status;

    @NotNull(message = "message can not be null")
    private String message;
}
