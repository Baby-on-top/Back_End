package baby.lignin.board.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardInviteRequest {
    private Long boardId;
    private Long memberId;
}
