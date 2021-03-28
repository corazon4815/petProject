package learning.petProject.entity.content;

import learning.petProject.entity.BaseEntity;
import learning.petProject.entity.member.Member;
import learning.petProject.entity.reply.Reply;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Inheritance
@DiscriminatorColumn(name = "contentType") //Free, photo, Question...
//DiscriminatorColumn : 어떤 컬럼을 가지고 어떤 자식 엔티티를 판별할 것인가에 대한 힌트
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public abstract class Content extends BaseEntity {

    @GeneratedValue
    @Id
    @Column(name = "content_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    @OneToMany(mappedBy = "content")
    List<Reply> replies = new ArrayList<>();

    String title;
    String content;
    int views;
    int likes;
    String picture;

    @Enumerated(EnumType.STRING)
    ContentStatus status;
}
