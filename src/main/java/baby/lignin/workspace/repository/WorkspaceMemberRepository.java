package baby.lignin.workspace.repository;

import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.entity.WorkSpaceMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceMemberRepository extends JpaRepository<WorkSpaceMemberEntity,Long> {
    List<WorkSpaceMemberEntity> findByMemberId(Long memberId);

    WorkSpaceMemberEntity findByMemberIdAndWorkspaceId(Long memberId, Long WorkSpaceId);
    //WorkSpaceEntitiy findById(Long workspaceId);
}
