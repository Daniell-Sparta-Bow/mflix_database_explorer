package org.sparta.tech259.finalproject.model.exception.users;


public class UsersErrorResponse {
    private String message;
    private String url;
    private int statusCode;

    public UsersErrorResponse(String message, int statusCode, String url){
        this.message = message;
        this.statusCode = statusCode;
        this.url = url;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
