package baby.lignin.workspace.repository;

import baby.lignin.board.entity.BoardEntity;
import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<WorkSpaceEntitiy,Long> {

    List<WorkSpaceEntitiy> findByMemberId(Long memberId);
}
