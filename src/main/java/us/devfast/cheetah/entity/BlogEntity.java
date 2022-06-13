package us.devfast.cheetah.entity;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "blog_entity")
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer category;

    @NotEmpty
    private String title;

    @NotEmpty
    private String des;

    @NotEmpty
    private String detail;

    @Column(name = "public")
    @ColumnDefault("true")
    private Boolean isPublic;

    @NotEmpty
    private Integer[] position;

    @Column(name = "data_public")
    @PastOrPresent
    @NotNull
    private LocalDate dataPublic;

    public BlogEntity() {

    }

    public BlogEntity(String title, int category, boolean isPublic) {
        this.title = title;
        this.category = category;
        this.isPublic = isPublic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Integer[] getPosition() {
        return position;
    }

    public void setPosition(Integer[] position) {
        this.position = position;
    }

    public LocalDate getDataPublic() {
        return dataPublic;
    }

    public void setDataPublic(LocalDate dataPublic) {
        this.dataPublic = dataPublic;
    }
}