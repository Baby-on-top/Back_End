package baby.lignin.workspace.service;


import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.request.WorkspaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkspaceService {

    public List<WorkspaceResponse> findAllList();

    public List<WorkspaceResponse> findMyList(String access_Token) throws Exception;

    public WorkspaceResponse create(String token, MultipartFile multipartFile, WorkspaceCreateRequest request);

    public WorkspaceResponse delete(Long workspaceId) throws Exception;

    public WorkspaceResponse update(WorkspaceUpdateRequest request, MultipartFile multipartFile) throws Exception;

    public WorkspaceMemberResponse invite(String token, Long roomId);

    public WorkspaceMemberResponse unlink(String token, WorkspaceDeleteRequest request);


    public List<MemberResponse> memberList(Long workspaceId) throws Exception;

}
