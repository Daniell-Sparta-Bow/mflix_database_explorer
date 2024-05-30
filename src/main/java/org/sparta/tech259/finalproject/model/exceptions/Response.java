package org.sparta.tech259.finalproject.model.exceptions;

public record Response(int statusCode, String url, String message) {
}
