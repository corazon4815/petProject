package learning.petProject.entity.admin;

import learning.petProject.entity.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity //db테이블과 링크될 클래스
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Data
public class Admin extends BaseEntity {

    @Id //PK
    @GeneratedValue //auto increment같은
    @Column(name = "admin_id")
    Long id;

    //이하 컬럼들 (varchar 255가 기본값)
    String login_id;
    String password;
    String name;

    @Enumerated(EnumType.STRING)
    AdminLevel level;

    @Enumerated(EnumType.STRING)
    AdminStatus status;

    @Builder
    public Admin(Long id, String login_id, String password, String name, AdminLevel level, AdminStatus status) {
        this.login_id = login_id;
        this.password = password;
        this.name = name;
    }
}
