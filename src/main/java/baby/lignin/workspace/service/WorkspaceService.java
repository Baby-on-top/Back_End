package baby.lignin.workspace.service;


import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkSpaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;

import java.util.List;

public interface WorkspaceService {

    public List<WorkSpaceResponse> findAllList();

    public List<WorkSpaceResponse> findMyList(String access_Token) throws Exception;

    public WorkSpaceResponse create(String token,WorkSpaceCreateRequest request);

    public WorkSpaceResponse delete(Long workspaceId) throws Exception;

    public WorkSpaceResponse update(WorkSpaceUpdateRequest request) throws Exception;

    public WorkSpaceMemberResponse unlink(String token, WorkSpaceDeleteRequest request);

}
