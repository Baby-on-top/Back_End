package baby.lignin.workspace.service;


import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.response.WorkSpaceResponse;

import java.util.List;

public interface WorkspaceService {

    public List<WorkSpaceResponse> findList(Long memberId);

    public void create(WorkSpaceCreateRequest request);
}
