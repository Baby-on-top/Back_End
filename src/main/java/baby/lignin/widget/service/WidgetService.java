package baby.lignin.widget.service;


import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.widget.model.request.WidgetAddRequest;
import baby.lignin.widget.model.request.WidgetImageRequest;
import baby.lignin.widget.model.request.WidgetTitleRequest;
import baby.lignin.widget.model.request.WidgetXYRequest;
import baby.lignin.widget.model.response.WidgetResponse;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.request.WorkspaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface WidgetService {

    public List<WidgetResponse> findWidegetList(Long roomId);

    public WidgetResponse addWidget(WidgetAddRequest request);

    public WidgetResponse updateXyWidget(WidgetXYRequest request) throws Exception;

    public WidgetResponse updateTitleWidget(WidgetTitleRequest request) throws Exception;

    public WidgetResponse updateImageWidget(WidgetImageRequest request) throws Exception;

}
