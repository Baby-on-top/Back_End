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
    public static WorkSpaceEntitiy to(WorkSpaceResponse workSpaceResponse, String image) {
        return WorkSpaceEntitiy.builder()
                .id(workSpaceResponse.getWorkspaceId())
                .workSpaceName(workSpaceResponse.getWorkSpaceImage())
                .createId(workSpaceResponse.getCreateId())
                .workSpaceImage(image)
                .build();
    }

    public static WorkSpaceEntitiy to(WorkSpaceCreateRequest workSpaceResponse, String image) {
        return WorkSpaceEntitiy.builder()
                .workSpaceName(workSpaceResponse.getWorkSpaceName())
                .createId(workSpaceResponse.getCreateId())
                .workSpaceImage(image)
                .build();
    }

    public static WorkSpaceResponse from(WorkSpaceEntitiy workSpaceEntitiy) {
        return WorkSpaceResponse.builder()
                .workspaceId(workSpaceEntitiy.getId())
                .createId(workSpaceEntitiy.getCreateId())
                .workSpaceName(workSpaceEntitiy.getWorkSpaceName())
                .workSpaceImage(workSpaceEntitiy.getWorkSpaceImage())
                .build();
    }

    public static WorkSpaceMemberResponse from(WorkSpaceMemberEntity workSpaceMemberEntity) {
        return WorkSpaceMemberResponse.builder()
                .workspaceId(workSpaceMemberEntity.getWorkspaceId())
                .memberId(workSpaceMemberEntity.getMemberId())
                .workspaceMemberId(workSpaceMemberEntity.getWorkspaceId())
                .build();
    }

}
