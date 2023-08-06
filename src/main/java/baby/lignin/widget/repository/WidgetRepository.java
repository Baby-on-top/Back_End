package baby.lignin.widget.repository;

import baby.lignin.widget.entity.WidgetEntitiy;
import baby.lignin.workspace.entity.WorkspaceEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface WidgetRepository extends JpaRepository<WidgetEntitiy, Long> {

    List<WidgetEntitiy> findByRoomId(Long roomId);
}
