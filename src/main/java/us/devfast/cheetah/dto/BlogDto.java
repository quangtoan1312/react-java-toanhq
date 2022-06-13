package us.devfast.cheetah.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class BlogDto {
    private Integer id;
    private Integer category;
    private String title;
    private String des;
    private String detail;
    private Boolean isPublic;
    private Integer[] position;
    private LocalDate dataPublic;
}
