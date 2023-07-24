package baby.lignin.workspace.support.converter;

import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.entity.WorkSpaceMemberEntity;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkspaceCreateConverter {
    public static WorkSpaceMemberEntity to(WorkSpaceEntitiy workSpaceResponse) {
        return WorkSpaceMemberEntity.builder()
                .workspaceId(workSpaceResponse.getId())
                .memberId(workSpaceResponse.getCreateId())
                .build();
    }

    public static WorkSpaceMemberResponse from(WorkSpaceMemberEntity workSpaceMemberEntity){
        return WorkSpaceMemberResponse.builder()
                .workspaceId(workSpaceMemberEntity.getWorkspaceId())
                .memberId(workSpaceMemberEntity.getMemberId())
                .build();
    }
}
