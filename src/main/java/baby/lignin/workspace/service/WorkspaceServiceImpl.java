package baby.lignin.workspace.service;


import baby.lignin.auth.config.TokenResolver;
import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.auth.repository.MemberRepository;
import baby.lignin.auth.util.converter.MemberConverter;
import baby.lignin.board.entity.BoardEntity;
import baby.lignin.board.repository.BoardRepository;
import baby.lignin.image.service.AwsS3Service;
import baby.lignin.workspace.entity.WorkspaceEntitiy;
import baby.lignin.workspace.entity.WorkspaceMemberEntity;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.request.WorkspaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import baby.lignin.workspace.repository.WorkspaceMemberRepository;
import baby.lignin.workspace.repository.WorkspaceRepository;
import baby.lignin.workspace.support.converter.WorkspaceConverter;
import baby.lignin.workspace.support.converter.WorkspaceCreateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final BoardRepository boardRepository;

    @Override
    public List<WorkspaceResponse> findAllList() {
        List<WorkspaceEntitiy> list = workspaceRepository.findAll();

        List<WorkspaceResponse> responses = new ArrayList<>();
        for (WorkspaceEntitiy entity : list) {
            responses.add(WorkspaceConverter.from(entity));
        }
        return responses;
    }

    @Override
    public List<WorkspaceResponse> findMyList(String access_Token) throws Exception {
        Optional<Long> memberIdRe = tokenResolver.resolveToken(access_Token);
        Long memberId = memberIdRe.get();
        List<WorkspaceMemberEntity> list = workspaceMemberRepository.findByMemberId(memberId).stream().collect(Collectors.toList());
        List<WorkspaceResponse> response = new ArrayList<>();
        for (WorkspaceMemberEntity entity : list) {
            WorkspaceResponse respone = WorkspaceConverter.from(workspaceRepository.findById(entity.getWorkspaceId()).orElseThrow(() -> new Exception("해당하는 워크스페이스가 없습니다.")));
            response.add(respone);
        }


        return response;
    }

    @Override
    public WorkspaceResponse create(String token, MultipartFile multipartFile, WorkspaceCreateRequest request) {
        String spaceImage = awsS3Service.uploadImage(multipartFile);
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();
        request.setCreateId(memberId);
        WorkspaceEntitiy workspaceEntitiy = workspaceRepository.save(WorkspaceConverter.to(request, spaceImage));
        workspaceMemberRepository.save(WorkspaceCreateConverter.to(workspaceEntitiy));
        return WorkspaceConverter.from(workspaceEntitiy);
    }

    @Override
    public WorkspaceResponse delete(Long workspaceId) throws Exception {
        WorkspaceEntitiy workspaceEntitiy = workspaceRepository.findById(workspaceId).orElseThrow(() -> new Exception("찾는 워크 스페이스가 없습니다.!"));
        List<WorkspaceMemberEntity> workspaceMemberEntity = workspaceMemberRepository.findByWorkspaceId(workspaceId).stream().collect(Collectors.toList());
        workspaceMemberRepository.deleteAll(workspaceMemberEntity);
        workspaceRepository.delete(workspaceEntitiy);

        return WorkspaceConverter.from(workspaceEntitiy);
    }

    @Override
    public WorkspaceResponse update(WorkspaceUpdateRequest request, MultipartFile multipartFile) throws Exception {
        String spaceImage = awsS3Service.uploadImage(multipartFile);
        WorkspaceEntitiy workspaceEntitiy = workspaceRepository.findById(request.getWorkspaceId()).orElseThrow(() -> new Exception("찾는 워크 스페이스가 없습니다.!"));
        workspaceEntitiy.changeWorkSpaceInfo(request, spaceImage);
        List<BoardEntity> boardEntities = boardRepository.findByWorkspaceId(request.getWorkspaceId());

        for (BoardEntity boardEntity : boardEntities) {
            boardEntity.changeBoardWorkspaceName(request.getWorkspaceName());
        }

        workspaceRepository.save(workspaceEntitiy);
        return WorkspaceConverter.from(workspaceEntitiy);
    }

    @Override
    public WorkspaceMemberResponse invite(String token, Long workspaceId) {
        System.out.println("🚨token = " + token);
        System.out.println("🚨workspaceId = " + workspaceId);
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();

        WorkspaceMemberEntity exist = workspaceMemberRepository.findByMemberIdAndWorkspaceId(memberId, workspaceId);
        System.out.println("🚨exist = " + exist);
        if (exist != null) {
            return null;
        }
        WorkspaceMemberEntity response = workspaceMemberRepository.save(WorkspaceMemberEntity.builder()
                .workspaceId(workspaceId)
                .memberId(memberId)
                .build());
        System.out.println("🚨response = " + response);
        return WorkspaceConverter.from(response);
    }

    @Override
    public WorkspaceMemberResponse unlink(String token, WorkspaceDeleteRequest request) {
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();
        WorkspaceMemberEntity workspaceMemberEntity = workspaceMemberRepository.findByMemberIdAndWorkspaceId(memberId, request.getWorkspaceId());
        workspaceMemberRepository.delete(workspaceMemberEntity);
        return WorkspaceConverter.from(workspaceMemberEntity);
    }

    @Override
    public List<MemberResponse> memberList(Long workspaceId) throws Exception {
        List<WorkspaceMemberEntity> memberList = workspaceMemberRepository.findByWorkspaceId(workspaceId).stream().collect(Collectors.toList());

        List<MemberResponse> list = new ArrayList<>();
        for (WorkspaceMemberEntity member : memberList) {
            MemberResponse res = MemberConverter.from(memberRepository.findById(member.getMemberId()).orElseThrow(() -> new Exception("new")));
            list.add(res);
        }

        return list;
    }
}
