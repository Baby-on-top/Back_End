package baby.lignin.workspace.support.converter;

import baby.lignin.auth.entity.MemberEntity;
import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceRequest;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkspaceConverter {
    public static WorkSpaceEntitiy to(WorkSpaceResponse workSpaceResponse) {
        return WorkSpaceEntitiy.builder()
                .id(workSpaceResponse.getWorkspaceId())
                .name(workSpaceResponse.getName())
                .memberId(workSpaceResponse.getMemberId())
                .image(workSpaceResponse.getImage())
                .build();
    }

    public static WorkSpaceEntitiy to(WorkSpaceCreateRequest workSpaceResponse) {
        return WorkSpaceEntitiy.builder()
                .name(workSpaceResponse.getName())
                .memberId(workSpaceResponse.getMemberId())
                .image(workSpaceResponse.getImage())
                .build();
    }

    public static WorkSpaceResponse from(WorkSpaceEntitiy workSpaceEntitiy) {
        return WorkSpaceResponse.builder()
                .workspaceId(workSpaceEntitiy.getId())
                .memberId(workSpaceEntitiy.getMemberId())
                .name(workSpaceEntitiy.getName())
                .image(workSpaceEntitiy.getImage())
                .build();
    }

}
