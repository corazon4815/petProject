package learning.petProject.repository;

import learning.petProject.entity.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //해당 엔티티가 사용할 Repository. Generic타입으로 해당 엔티티, 엔티티의 pk의 자료형
public interface ContentRepository extends JpaRepository<Content, Long> {
}
