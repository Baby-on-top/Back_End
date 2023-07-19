package baby.lignin.board.service;

import baby.lignin.board.entity.BoardMemberEntity;
import baby.lignin.board.model.request.BoardInviteRequest;
import baby.lignin.board.model.response.BoardMemberResponse;
import baby.lignin.board.repository.BoardMemberRepository;
import baby.lignin.board.support.converter.BoardMemberConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoardMemberService {

    private final BoardMemberRepository boardMemberRepository;

    public BoardMemberResponse inviteBoard(BoardInviteRequest request) {
        BoardMemberEntity boardMemberEntity = boardMemberRepository.save(BoardMemberConverter.to(request));

        return BoardMemberConverter.from(boardMemberEntity);
    }
}


