package baby.lignin.workspace.support.converter;

import baby.lignin.workspace.entity.WorkspaceEntitiy;
import baby.lignin.workspace.entity.WorkspaceMemberEntity;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkspaceCreateConverter {
    public static WorkspaceMemberEntity to(WorkspaceEntitiy workspaceResponse) {
        return WorkspaceMemberEntity.builder()
                .workspaceId(workspaceResponse.getId())
                .memberId(workspaceResponse.getCreateId())
                .build();
    }

    public static WorkspaceMemberResponse from(WorkspaceMemberEntity workspaceMemberEntity) {
        return WorkspaceMemberResponse.builder()
                .workspaceId(workspaceMemberEntity.getWorkspaceId())
                .memberId(workspaceMemberEntity.getMemberId())
                .build();
    }
}
