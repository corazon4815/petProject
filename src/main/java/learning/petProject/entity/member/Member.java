package learning.petProject.entity.member;

import learning.petProject.entity.BaseEntity;
import learning.petProject.entity.content.Content;
import learning.petProject.entity.reply.Reply;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    Long id;

    @OneToMany(mappedBy = "member")
    List<Content> contents = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    List<Reply> replies = new ArrayList<>();

    @NotEmpty
    String login_id;
    @NotEmpty
    String password;

    String name;
    String address;
    String phoneNumber;

    @Enumerated(EnumType.STRING)
    MemberStatus status;

    @Enumerated(EnumType.STRING)
    MemberType type;




}
