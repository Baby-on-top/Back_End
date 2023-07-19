package baby.lignin.board.repository;

import baby.lignin.board.entity.BoardMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMemberEntity, Long> {

}
