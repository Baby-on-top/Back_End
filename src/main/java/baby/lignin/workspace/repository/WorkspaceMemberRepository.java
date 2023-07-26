package baby.lignin.workspace.repository;

import baby.lignin.workspace.entity.WorkspaceMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceMemberRepository extends JpaRepository<WorkspaceMemberEntity, Long> {
    List<WorkspaceMemberEntity> findByMemberId(Long memberId);

    WorkspaceMemberEntity findByMemberIdAndWorkspaceId(Long memberId, Long WorkSpaceId);

    List<WorkspaceMemberEntity> findByWorkspaceId(Long workspaceId);
    //WorkSpaceEntitiy findById(Long workspaceId);
}
