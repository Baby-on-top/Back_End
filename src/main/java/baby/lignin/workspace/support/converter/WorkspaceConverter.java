package baby.lignin.workspace.support.converter;

import baby.lignin.auth.entity.MemberEntity;
import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.entity.WorkSpaceMemberEntity;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceRequest;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkspaceConverter {
    public static WorkSpaceEntitiy to(WorkSpaceResponse workSpaceResponse) {
        return WorkSpaceEntitiy.builder()
                .id(workSpaceResponse.getWorkspaceId())
                .name(workSpaceResponse.getName())
                .createId(workSpaceResponse.getCreateId())
                .image(workSpaceResponse.getImage())
                .build();
    }

    public static WorkSpaceEntitiy to(WorkSpaceCreateRequest workSpaceResponse) {
        return WorkSpaceEntitiy.builder()
                .name(workSpaceResponse.getName())
                .createId(workSpaceResponse.getCreateId())
                .image(workSpaceResponse.getImage())
                .build();
    }

    public static WorkSpaceResponse from(WorkSpaceEntitiy workSpaceEntitiy) {
        return WorkSpaceResponse.builder()
                .workspaceId(workSpaceEntitiy.getId())
                .createId(workSpaceEntitiy.getCreateId())
                .name(workSpaceEntitiy.getName())
                .image(workSpaceEntitiy.getImage())
                .build();
    }

    public static WorkSpaceMemberResponse from(WorkSpaceMemberEntity workSpaceMemberEntity){
        return WorkSpaceMemberResponse.builder()
                .workspaceId(workSpaceMemberEntity.getWorkspaceId())
                .memberId(workSpaceMemberEntity.getMemberId())
                .workspaceMemberId(workSpaceMemberEntity.getWorkspaceId())
                .build();
    }

}
