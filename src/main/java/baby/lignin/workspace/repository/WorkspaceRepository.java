package baby.lignin.workspace.repository;

import baby.lignin.workspace.entity.WorkspaceEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<WorkspaceEntitiy, Long> {

    List<WorkspaceEntitiy> findByCreateId(Long createId);
    //WorkSpaceEntitiy findById(Long workspaceId);
}
