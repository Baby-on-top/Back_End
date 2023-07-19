package baby.lignin.workspace.service;


import baby.lignin.auth.entity.MemberEntity;
import baby.lignin.auth.service.MemberService;
import baby.lignin.auth.util.converter.MemberConverter;
import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import baby.lignin.workspace.repository.WorkspaceRepository;
import baby.lignin.workspace.support.converter.WorkspaceConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkspaceServiceImpl implements WorkspaceService {


    private final WorkspaceRepository workspaceRepository;

    @Override
    public List<WorkSpaceResponse> findList(Long memberId) {
        List<WorkSpaceEntitiy> list = workspaceRepository.findByMemberId(memberId).stream().collect(Collectors.toList());

        List<WorkSpaceResponse> responses = new ArrayList<>();
        for(WorkSpaceEntitiy entity : list){
            System.out.println("name: "+entity.getName());
            responses.add(WorkspaceConverter.from(entity));
        }
        return responses;
    }

    @Override
    public void create(WorkSpaceCreateRequest request) {
        WorkSpaceEntitiy workSpaceEntitiy = workspaceRepository.save(WorkspaceConverter.to(request));
        Long workspaceId = workSpaceEntitiy.getId();
        System.out.println("workspaceId = " + workspaceId);
    }
}
