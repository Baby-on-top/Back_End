package baby.lignin.board.service;

import baby.lignin.board.entity.BoardEntity;
import baby.lignin.board.entity.BoardMemberEntity;
import baby.lignin.board.model.request.BoardAddRequest;
import baby.lignin.board.model.request.BoardBrowseRequest;
import baby.lignin.board.model.request.BoardDeleteRequest;
import baby.lignin.board.model.request.BoardEditRequest;
import baby.lignin.board.model.response.BoardResponse;
import baby.lignin.board.repository.BoardMemberRepository;
import baby.lignin.board.repository.BoardRepository;
import baby.lignin.board.support.converter.BoardConverter;
import baby.lignin.image.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import baby.lignin.auth.config.TokenResolver;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Service는 컨트롤러에서 이용함.
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardService {
    // new 안써도 됨. 알아서 생성, 주입을 해줌.
    private final BoardRepository boardRepository;

    public String makeRedisKey(String token, BoardBrowseRequest request) {
        return token + ":" + request.getWorkspaceId() + ":" + request.getSearchKeyword();
    }


    private final TokenResolver tokenResolver;

    private final BoardMemberRepository boardMemberRepository;

    private final AwsS3Service awsS3Service;

    @Cacheable(cacheNames = "boards", key = "{#root.target.makeRedisKey(#token, #request)}")
    public List<BoardResponse> getBoards(String token, BoardBrowseRequest request){

        List<BoardEntity> boardEntities = null;
        List<BoardMemberEntity> boardMemberEntities;

        // 현재 로그인한 맴버의 id들을 가져왔음.
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();

        // 맴버 ID에 해당하는 member들을 모두 가져왔음.
        boardMemberEntities = boardMemberRepository.findByMemberId(memberId)
                .stream()
                .collect(Collectors.toList());

        List<BoardResponse> boardMember_List = new ArrayList<>();

        // 맴버 Entity에 해당하는
        for (BoardMemberEntity boardMemberEntity : boardMemberEntities) {
            if (request.getSearchKeyword() == null) {
                boardEntities = boardRepository.findByWorkspaceIdAndId(request.getWorkspaceId(), boardMemberEntity.getBoardId())
                        .stream()
                        .collect(Collectors.toList());
            } else {
                boardEntities = boardRepository.findByWorkspaceIdAndBoardNameContainingAndId(request.getWorkspaceId(), request.getSearchKeyword(), boardMemberEntity.getBoardId())
                        .stream()
                        .collect(Collectors.toList());
            }
            for (BoardEntity boardEntity : boardEntities){
                boardMember_List.add(BoardConverter.from(boardEntity));
            }
        }

        List<BoardResponse> responses = new ArrayList<>();

        for (BoardResponse boardresponse : boardMember_List){
            responses.add(boardresponse);
        }
        return responses;
    }

    public BoardResponse generateBoard(String token, MultipartFile multipartFile,BoardAddRequest request) {
        request.setBoardImage(awsS3Service.uploadImage(multipartFile));

        BoardEntity boardEntity = boardRepository.save(BoardConverter.to(request));

        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();

        boardMemberRepository.save(new BoardMemberEntity(boardEntity.getId(), memberId));

        return BoardConverter.from(boardEntity);
    }

    public BoardResponse changeBoardInfo(MultipartFile multipartFile, BoardEditRequest request) {
        BoardEntity boardEntity = boardRepository.findById(request.getBoardId()).orElseThrow();

        request.setBoardImage(awsS3Service.uploadImage(multipartFile));

        boardEntity.changeBoardInfo(request);
        return BoardConverter.from(boardEntity);
    }

    public void deleteBoard(BoardDeleteRequest request) {
        boardRepository.deleteById(request.getBoardId());
    }
}