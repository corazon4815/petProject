package learning.petProject.Service;

import learning.petProject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

public class MemberService {

    @Autowired //Spring의 IOC가 Bean의 의존성을 알아서 주입할수 있게함
    private MemberRepository memberRepository;

//    @Transactional
//    public Long savePost(BoardDto boardDto){
//        return boardRepository.save(boardDto.toEntity()).getId();
//    }
}
