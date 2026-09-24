package com.edusupport.dto;
import jakarta.validation.constraints.NotBlank;
public record AddCommentRequest(@NotBlank String message, @NotBlank String authorEmail) {}
