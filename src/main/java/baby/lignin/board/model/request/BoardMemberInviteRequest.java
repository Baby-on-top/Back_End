package baby.lignin.board.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardMemberInviteRequest {
    private Long boardId;
    private Long memberId;
}
