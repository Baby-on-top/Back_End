package baby.lignin.workspace.service;


import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkSpaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WorkspaceService {

    public List<WorkSpaceResponse> findAllList();

    public List<WorkSpaceResponse> findMyList(String access_Token) throws Exception;

    public WorkSpaceResponse create(String token, MultipartFile multipartFile, WorkSpaceCreateRequest request);

    public WorkSpaceResponse delete(Long workspaceId) throws Exception;

    public WorkSpaceResponse update(WorkSpaceUpdateRequest request, MultipartFile multipartFile) throws Exception;

    public WorkSpaceMemberResponse invite(String token, Long roomId);

    public WorkSpaceMemberResponse unlink(String token, WorkSpaceDeleteRequest request);


    public List<MemberResponse> memberList(Long workspaceId) throws Exception;

}
