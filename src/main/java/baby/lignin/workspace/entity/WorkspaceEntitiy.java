package baby.lignin.workspace.entity;


import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "workspace")
public class WorkspaceEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workspace_id")
    private Long id;

    @Column(nullable = false)
    private Long createId;

    @Column(nullable = false)
    private String workspaceName;

    @Column
    private String workspaceImage;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void changeWorkSpaceInfo(WorkspaceUpdateRequest request, String image) {
        this.workspaceName = request.getWorkspaceName();
        this.workspaceImage = image;

    }


}
