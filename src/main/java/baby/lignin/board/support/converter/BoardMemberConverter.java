package baby.lignin.board.support.converter;

import baby.lignin.board.entity.BoardMemberEntity;
import baby.lignin.board.model.request.BoardMemberInviteRequest;
import baby.lignin.board.model.response.BoardMemberResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BoardMemberConverter {
    public static BoardMemberResponse from(BoardMemberEntity boardMemberEntity) {
        if (boardMemberEntity == null) {
            return null;
        }

        return BoardMemberResponse.builder()
                .boardId(boardMemberEntity.getBoardId())
                .memberId(boardMemberEntity.getMemberId())
                .build();
    }
    public BoardMemberEntity to(BoardMemberInviteRequest request) {
        return BoardMemberEntity.builder()
                .boardId(request.getBoardId())
                .memberId(request.getMemberId())
                .build();
    }
}