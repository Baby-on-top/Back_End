package baby.lignin.board.service;

import baby.lignin.auth.config.TokenResolver;
import baby.lignin.board.entity.BoardMemberEntity;
import baby.lignin.board.model.request.BoardMemberDeleteRequest;
import baby.lignin.board.model.request.BoardMemberInviteRequest;
import baby.lignin.board.model.response.BoardMemberResponse;
import baby.lignin.board.repository.BoardMemberRepository;
import baby.lignin.board.support.converter.BoardMemberConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardMemberService {

    private final BoardMemberRepository boardMemberRepository;

    private final TokenResolver tokenResolver;

    public BoardMemberResponse inviteBoardMember(BoardMemberInviteRequest request) {
        BoardMemberEntity boardMemberEntity = boardMemberRepository.save(BoardMemberConverter.to(request));

        return BoardMemberConverter.from(boardMemberEntity);
    }

    public void deleteBoardMember(String token, BoardMemberDeleteRequest request) {
        Optional<Long> memberIdRe = tokenResolver.resolveToken(token);
        Long memberId = memberIdRe.get();

        boardMemberRepository.deleteByBoardIdAndMemberId(request.getBoardId(),memberId);
    }
}


