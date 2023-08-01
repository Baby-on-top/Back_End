package baby.lignin.workspace.support.converter;

import baby.lignin.workspace.entity.WorkspaceEntitiy;
import baby.lignin.workspace.entity.WorkspaceMemberEntity;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkspaceConverter {
    public static WorkspaceEntitiy to(WorkspaceResponse workspaceResponse, String image) {
        return WorkspaceEntitiy.builder()
                .id(workspaceResponse.getWorkspaceId())
                .workspaceName(workspaceResponse.getWorkspaceImage())
                .createId(workspaceResponse.getCreateId())
                .workspaceImage(image)
                .build();
    }

    public static WorkspaceEntitiy to(WorkspaceCreateRequest workspaceResponse, String image) {
        return WorkspaceEntitiy.builder()
                .workspaceName(workspaceResponse.getWorkspaceName())
                .createId(workspaceResponse.getCreateId())
                .workspaceImage(image)
                .build();
    }

    public static WorkspaceResponse from(WorkspaceEntitiy workspaceEntitiy) {
        return WorkspaceResponse.builder()
                .workspaceId(workspaceEntitiy.getId())
                .createId(workspaceEntitiy.getCreateId())
                .workspaceName(workspaceEntitiy.getWorkspaceName())
                .workspaceImage(workspaceEntitiy.getWorkspaceImage())
                .build();
    }

    public static WorkspaceMemberResponse from(WorkspaceMemberEntity workspaceMemberEntity) {
        return WorkspaceMemberResponse.builder()
                .workspaceId(workspaceMemberEntity.getWorkspaceId())
                .memberId(workspaceMemberEntity.getMemberId())
                .workspaceMemberId(workspaceMemberEntity.getWorkspaceId())
                .build();
    }

}
