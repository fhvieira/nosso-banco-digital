package br.com.zup.nossobancodigital.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class Problem {

    private Integer status;
    private String title;
    private String type;
    private String detail;

    private String userMessage;
    private OffsetDateTime timestamp;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {
        private String name;
        private String userMessage;
    }
}
