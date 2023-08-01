package baby.lignin.workspace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceMemberResponse {
    private Long workspaceMemberId;
    private Long workspaceId;
    private Long memberId;

}
