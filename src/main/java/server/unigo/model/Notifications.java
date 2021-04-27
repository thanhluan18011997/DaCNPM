package server.unigo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notifications extends BaseEntity {
    @Column(columnDefinition="text")
    private String title;
    @Column(columnDefinition="text")
    private String content;
}
