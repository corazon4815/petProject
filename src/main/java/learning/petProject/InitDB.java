package learning.petProject;

import learning.petProject.entity.content.*;
import learning.petProject.entity.member.Member;
import learning.petProject.entity.member.MemberStatus;
import learning.petProject.entity.member.MemberType;
import learning.petProject.entity.reply.Reply;
import learning.petProject.entity.reply.ReplyStatus;
import learning.petProject.repository.ContentRepository;
import learning.petProject.repository.MemberRepository;
import learning.petProject.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        //initService.dbInitMember();
        //initService.dbInitContent();
        //initService.dbInitParentReply();
        //initService.dbInitChildReply();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        private final MemberRepository memberRepository;
        private final ContentRepository contentRepository;
        private final ReplyRepository replyRepository;

        public void dbInitMember() {
            Member member1 = createMember("userA", "1234", "AA", MemberStatus.ACTIVE, MemberType.REGULAR);
            Member member2 = createMember("userB", "2345", "BB", MemberStatus.DELETED, MemberType.TEMPORARY);
            Member member3 = createMember("userC", "3456", "CC", MemberStatus.DORMANT, MemberType.REGULAR);
            Member member4 = createMember("userD", "4567", "DD", MemberStatus.SUSPENDED, MemberType.TEMPORARY);
            Member member5 = createMember("userE", "5678", "EE", MemberStatus.ACTIVE, MemberType.REGULAR);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);

        }

        public void dbInitContent() {
            List<Member> allMembers = memberRepository.findAll();
            Random random = new Random();
            for (Member member : allMembers) {
                for (int i = 0; i < 2; i++) {
                    Content free1 = createFreeContent("free title " + i, "free content " + i, List.of(ContentStatus.values()).get(i % 2), member);
                    Content photo1 = createPhotoContent("photo title " + i, "photo content " + i, List.of(ContentStatus.values()).get(i % 2), member);
                    Content question1 = createQuestionContent("question title " + i, "question content " + i, List.of(ContentStatus.values()).get(i % 2), member);
                    Content walking1 = createWalkingContent("walking title " + i, "walking content " + i, List.of(ContentStatus.values()).get(i % 2), member);
                    Content photo2 = createPhotoContent("photo title " + i, "photo content " + i, List.of(ContentStatus.values()).get(i % 2), member);
                    em.persist(free1);
                    em.persist(photo1);
                    em.persist(question1);
                    em.persist(walking1);
                    em.persist(photo2);
                }
            }
        }

        public void dbInitParentReply() {
            List<Content> allContents = contentRepository.findAll();
            List<Member> allMembers = memberRepository.findAll();
            int memberSize = allMembers.size();
            Random random = new Random();
            for (Content content : allContents) {
                for (int i = 0; i < 2; i++) {
                    Member randomMember = allMembers.get(i);
                    ReplyStatus randomStatus = List.of(ReplyStatus.values()).get(i);
                    Reply reply = createParentReply(randomMember.getName() + i, content, randomMember, randomStatus);
                    em.persist(reply);
                }
            }
        }

        public void dbInitChildReply() {
            List<Reply> allReplies = replyRepository.findAll();
            List<Member> allMembers = memberRepository.findAll();
            for (Reply reply : allReplies) {
                for(int i = 0; i < 2; i++){
                    Reply childReply = createChildReply("child " + i, reply, allMembers.get(i), List.of(ReplyStatus.values()).get(i));
                    em.persist(childReply);
                }
            }
        }


        private Member createMember(String id, String password, String name, MemberStatus status, MemberType type) {
            Member member = new Member();
            member.setLogin_id(id);
            member.setPassword(password);
            member.setName(name);
            member.setStatus(status);
            member.setType(type);
            return member;
        }

        private Content createFreeContent(String title, String content, ContentStatus status, Member member) {
            Content created = new Free();
            created.setTitle(title);
            created.setContent(content);
            created.setStatus(status);
            created.setMember(member);
            return created;
        }

        private Content createPhotoContent(String title, String content, ContentStatus status, Member member) {
            Content created = new Photo();
            created.setTitle(title);
            created.setContent(content);
            created.setStatus(status);
            created.setMember(member);
            return created;
        }

        private Content createQuestionContent(String title, String content, ContentStatus status, Member member) {
            Question created = new Question();
            created.setTitle(title);
            created.setContent(content);
            created.setStatus(status);
            created.setMember(member);
            created.setQuestionType(Arrays.stream(QuestionType.values()).findAny().get());
            return created;
        }

        private Content createWalkingContent(String title, String content, ContentStatus status, Member member) {
            Content created = new Walking();
            created.setTitle(title);
            created.setContent(content);
            created.setStatus(status);
            created.setMember(member);
            return created;
        }

        private Reply createParentReply(String reply, Content content, Member member, ReplyStatus status) {
            Reply created = new Reply();
            created.setReply(reply);
            created.setContent(content);
            created.setMember(member);
            created.setStatus(status);

            return created;
        }

        private Reply createChildReply(String reply, Reply parent, Member member, ReplyStatus status) {
            Reply created = new Reply();
            created.setParent(parent);
            created.setContent(parent.getContent());
            created.setReply(reply);
            created.setMember(member);
            created.setStatus(status);
            parent.getChild().add(created);

            return created;
        }
    }
}
