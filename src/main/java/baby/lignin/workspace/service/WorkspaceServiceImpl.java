package baby.lignin.workspace.service;


import baby.lignin.auth.config.TokenResolver;
import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.auth.repository.MemberRepository;
import baby.lignin.auth.util.converter.MemberConverter;
import baby.lignin.image.service.AwsS3Service;
import baby.lignin.support.ApiResponse;
import baby.lignin.workspace.entity.WorkSpaceEntitiy;
import baby.lignin.workspace.entity.WorkSpaceMemberEntity;
import baby.lignin.workspace.model.request.WorkSpaceCreateRequest;
import baby.lignin.workspace.model.request.WorkSpaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkSpaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkSpaceMemberResponse;
import baby.lignin.workspace.model.response.WorkSpaceResponse;
import baby.lignin.workspace.repository.WorkspaceMemberRepository;
import baby.lignin.workspace.repository.WorkspaceRepository;
import baby.lignin.workspace.support.converter.WorkspaceConverter;
import baby.lignin.workspace.support.converter.WorkspaceCreateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkspaceServiceImpl implements WorkspaceService {


    private final WorkspaceRepository workspaceRepository;
    private final WorkspaceMemberRepository workspaceMemberRepository;
    private final MemberRepository memberRepository;
    private final TokenResolver tokenResolver;
    private final AwsS3Service awsS3Service;

    @Override
    public List<WorkSpaceResponse> findAllList() {
        List<WorkSpaceEntitiy> list = workspaceRepository.findAll();

        List<WorkSpaceResponse> responses = new ArrayList<>();
        for (WorkSpaceEntitiy entity : list) {
            responses.add(WorkspaceConverter.from(entity));
        }
        return responses;
    }

    @Override
    public List<WorkSpaceResponse> findMyList(String access_Token) throws Exception {
        Optional<Long> memberIdRe = tokenResolver.resolveToken(access_Token);
        Long memberId = memberIdRe.get();
        List<WorkSpaceMemberEntity> list = workspaceMemberRepository.findByMemberId(memberId).stream().collect(Collectors.toList());
        List<WorkSpaceResponse> response = new ArrayList<>();
        for (WorkSpaceMemberEntity entity : list) {
            WorkSpaceResponse respone = WorkspaceConverter.from(workspaceRepository.findById(entity.getWorkspaceId()).orElseThrow(() -> new Exception("에러입니다.")));
            response.add(respone);
        }

        return response;
    }

    @Override
    public WorkSpaceResponse create(String token, MultipartFile multipartFile, WorkSpaceCreateRequest request) {
        String spaceImage = awsS3Service.uploadImage(multipartFile);
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();
        request.setCreateId(memberId);
        WorkSpaceEntitiy workSpaceEntitiy = workspaceRepository.save(WorkspaceConverter.to(request, spaceImage));
        workspaceMemberRepository.save(WorkspaceCreateConverter.to(workSpaceEntitiy));
        return WorkspaceConverter.from(workSpaceEntitiy);
    }

    @Override
    public WorkSpaceResponse delete(Long workspaceId) throws Exception {
        WorkSpaceEntitiy workSpaceEntitiy = workspaceRepository.findById(workspaceId).orElseThrow(() -> new Exception("찾는 워크 스페이스가 없습니다.!"));
        workspaceRepository.delete(workSpaceEntitiy);
        return WorkspaceConverter.from(workSpaceEntitiy);
    }

    @Override
    public WorkSpaceResponse update(WorkSpaceUpdateRequest request, MultipartFile multipartFile) throws Exception {
        String spaceImage = awsS3Service.uploadImage(multipartFile);
        WorkSpaceEntitiy workSpaceEntitiy = workspaceRepository.findById(request.getWorkspaceId()).orElseThrow(() -> new Exception("찾는 워크 스페이스가 없습니다.!"));
        workSpaceEntitiy.changeWorkSpaceInfo(request, spaceImage);

        workspaceRepository.save(workSpaceEntitiy);
        return WorkspaceConverter.from(workSpaceEntitiy);
    }

    @Override
    public WorkSpaceMemberResponse invite(String token, Long workspaceId) {
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();
        WorkSpaceMemberEntity exist = workspaceMemberRepository.findByMemberIdAndWorkspaceId(memberId, workspaceId);
        if (exist != null) {
            return null;
        }
        WorkSpaceMemberEntity response = workspaceMemberRepository.save(WorkSpaceMemberEntity.builder()
                .workspaceId(workspaceId)
                .memberId(memberId)
                .build());
        return WorkspaceConverter.from(response);
    }

    @Override
    public WorkSpaceMemberResponse unlink(String token, WorkSpaceDeleteRequest request) {
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();
        WorkSpaceMemberEntity workSpaceMemberEntity = workspaceMemberRepository.findByMemberIdAndWorkspaceId(memberId, request.getWorkspaceId());
        workspaceMemberRepository.delete(workSpaceMemberEntity);
        return WorkspaceConverter.from(workSpaceMemberEntity);
    }

    @Override
    public List<MemberResponse> memberList(Long workspaceId) throws Exception {
        List<WorkSpaceMemberEntity> memberList = workspaceMemberRepository.findByWorkspaceId(workspaceId).stream().collect(Collectors.toList());

        List<MemberResponse> list = new ArrayList<>();
        for (WorkSpaceMemberEntity member : memberList) {
            MemberResponse res = MemberConverter.from(memberRepository.findById(member.getMemberId()).orElseThrow(() -> new Exception("new")));
            list.add(res);
        }

        return list;
    }
}
