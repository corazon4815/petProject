package learning.petProject.entity.reply;

import learning.petProject.entity.BaseEntity;
import learning.petProject.entity.content.Content;
import learning.petProject.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Reply extends BaseEntity {

    @GeneratedValue
    @Id
    @Column(name = "reply_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //댓글을 다는 멤버 아이디
    Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id") //컨텐츠 고유 번호
    Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")  //parent대신 parent_id라는 이름을쓰겠다 //없는경우 null 있을경우 id(댓글의 고유번호)
    Reply parent;

    @OneToMany(mappedBy = "parent") //child에서 parent를 매핑
    List<Reply> child = new ArrayList<>();

    String reply;

    @Enumerated(EnumType.STRING)
    ReplyStatus status;
}
