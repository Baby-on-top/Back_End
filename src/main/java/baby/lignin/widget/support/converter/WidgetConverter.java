package baby.lignin.widget.support.converter;

import baby.lignin.widget.entity.WidgetEntitiy;
import baby.lignin.widget.model.request.WidgetAddRequest;
import baby.lignin.widget.model.response.WidgetResponse;
import baby.lignin.workspace.entity.WorkspaceEntitiy;
import baby.lignin.workspace.entity.WorkspaceMemberEntity;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WidgetConverter {

    public static WidgetEntitiy to(WidgetAddRequest widgetAddRequest) {
        return WidgetEntitiy.builder()
                .widgetTitle(widgetAddRequest.getWidgetTitle())
                .widgetType(widgetAddRequest.getWidgetType())
                .x(widgetAddRequest.getX())
                .y(widgetAddRequest.getY())
                .roomId(widgetAddRequest.getRoomId())
                .widgetImage(widgetAddRequest.getWidgetImage())
                .build();
    }

    public static WidgetResponse from(WidgetEntitiy widgetEntitiy) {
        return WidgetResponse.builder()
                .id(widgetEntitiy.getId())
                .roomId(widgetEntitiy.getRoomId())
                .widgetTitle(widgetEntitiy.getWidgetTitle())
                .y(widgetEntitiy.getY())
                .x(widgetEntitiy.getX())
                .widgetImage(widgetEntitiy.getWidgetImage())
                .widgetType(widgetEntitiy.getWidgetType())
                .build();
    }

//    public static WorkspaceMemberResponse from(WorkspaceMemberEntity workspaceMemberEntity) {
//        return WorkspaceMemberResponse.builder()
//                .workspaceId(workspaceMemberEntity.getWorkspaceId())
//                .memberId(workspaceMemberEntity.getMemberId())
//                .workspaceMemberId(workspaceMemberEntity.getWorkspaceId())
//                .build();
//    }

}
