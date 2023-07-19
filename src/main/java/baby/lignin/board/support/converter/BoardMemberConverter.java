package baby.lignin.board.support.converter;

import baby.lignin.board.entity.BoardMemberEntity;
import baby.lignin.board.model.request.BoardInviteRequest;
import baby.lignin.board.model.response.BoardMemberResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoardMemberConverter {
    public static BoardMemberResponse from(BoardMemberEntity boardMemberEntity) {
        if (boardMemberEntity == null) {
            return null;
        }

        return BoardMemberResponse.builder()
                .boardId(boardMemberEntity.getBoard_id())
                .memberId(boardMemberEntity.getMember_id())
                .build();
    }
    public BoardMemberEntity to(BoardInviteRequest request) {
        return BoardMemberEntity.builder()
                .board_id(request.getBoardId())
                .member_id(request.getMemberId())
                .build();
    }
}