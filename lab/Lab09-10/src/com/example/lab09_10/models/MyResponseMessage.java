package com.example.lab09_10.models;

import lombok.*;

import java.util.Optional;

@Setter
@Getter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class MyResponseMessage<T> {
    @NonNull
    private Integer status;
    @NonNull
    private String message;
    private Optional<T> data;
}
