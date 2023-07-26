package baby.lignin.workspace.entity;


import baby.lignin.board.model.request.BoardEditRequest;
import baby.lignin.workspace.model.request.WorkSpaceUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "workspace")
public class WorkSpaceEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workspace_id")
    private Long id;

    @Column(nullable = false)
    private Long createId;

    @Column(nullable = false)
    private String name;

    @Column
    private String image;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void changeWorkSpaceInfo(WorkSpaceUpdateRequest request, String image) {
        this.name = request.getName();
        this.image = image;

    }


}
