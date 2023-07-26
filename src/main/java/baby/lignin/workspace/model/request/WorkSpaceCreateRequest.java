package baby.lignin.workspace.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceCreateRequest {
    @JsonIgnore
    private Long createId;
    private String workSpaceName;
    @JsonIgnore
    private String workSpaceImage;

}
