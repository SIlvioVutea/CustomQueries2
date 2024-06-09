package com.example.demo.models;

public enum Status {
    ONTIME,
    DELAYED,
    CANCELLED;

    public static Status convert(String request){
        String check = request.toUpperCase().trim();
        return switch (check) {
            case "ONTIME" -> Status.ONTIME;
            case "DELAYED" -> Status.DELAYED;
            case "CANCELLED" -> Status.CANCELLED;
            default -> throw new IllegalStateException("Unexpected value: " + check);
        };
    }
}
