package baby.lignin.board.repository;

import baby.lignin.board.entity.BoardMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMemberRepository extends JpaRepository<BoardMemberEntity, Long> {
    List<BoardMemberEntity> findByMemberId(Long memberId);

    void deleteByBoardIdAndMemberId(Long boardId, Long memberId);
}
