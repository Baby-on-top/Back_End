package baby.lignin.widget.service;


import baby.lignin.auth.config.TokenResolver;
import baby.lignin.auth.model.response.MemberResponse;
import baby.lignin.auth.repository.MemberRepository;
import baby.lignin.auth.util.converter.MemberConverter;
import baby.lignin.image.service.AwsS3Service;
import baby.lignin.widget.entity.WidgetEntitiy;
import baby.lignin.widget.model.request.WidgetAddRequest;
import baby.lignin.widget.model.request.WidgetImageRequest;
import baby.lignin.widget.model.request.WidgetTitleRequest;
import baby.lignin.widget.model.request.WidgetXYRequest;
import baby.lignin.widget.model.response.WidgetResponse;
import baby.lignin.widget.repository.WidgetRepository;
import baby.lignin.widget.support.converter.WidgetConverter;
import baby.lignin.workspace.entity.WorkspaceEntitiy;
import baby.lignin.workspace.entity.WorkspaceMemberEntity;
import baby.lignin.workspace.model.request.WorkspaceCreateRequest;
import baby.lignin.workspace.model.request.WorkspaceDeleteRequest;
import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import baby.lignin.workspace.model.response.WorkspaceMemberResponse;
import baby.lignin.workspace.model.response.WorkspaceResponse;
import baby.lignin.workspace.repository.WorkspaceMemberRepository;
import baby.lignin.workspace.repository.WorkspaceRepository;
import baby.lignin.workspace.service.WorkspaceService;
import baby.lignin.workspace.support.converter.WorkspaceConverter;
import baby.lignin.workspace.support.converter.WorkspaceCreateConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WidgetServiceImpl implements WidgetService {

    private final WidgetRepository widgetRepository;

    @Override
    public List<WidgetResponse> findWidegetList(Long roomId) {
        List<WidgetEntitiy> list = widgetRepository.findByRoomId(roomId);

        List<WidgetResponse> responses = new ArrayList<>();
        for (WidgetEntitiy entity : list) {
            responses.add(WidgetConverter.from(entity));
        }
        return responses;
    }

    @Override
    public WidgetResponse addWidget(WidgetAddRequest request) {
        System.out.println(request.getX());
        System.out.println(request.getY());
        System.out.println(request.getWidgetTitle());
        System.out.println(request.toString());
        WidgetEntitiy widgetEntitiy = widgetRepository.save(WidgetConverter.to(request));

        return WidgetConverter.from(widgetEntitiy);
    }

    @Override
    public WidgetResponse updateXyWidget(WidgetXYRequest request) throws Exception {
        System.out.println("request = " + request.getId());
        WidgetEntitiy widgetEntitiy = widgetRepository.findById(request.getId()).orElseThrow(() -> new Exception("찾는 위젯이 없습니다."));
        widgetEntitiy.changeXYInfo(request.getX(), request.getY());
        widgetRepository.save(widgetEntitiy);
        return WidgetConverter.from(widgetEntitiy);
    }

    @Override
    public WidgetResponse updateTitleWidget(WidgetTitleRequest request) throws Exception {
        WidgetEntitiy widgetEntitiy = widgetRepository.findById(request.getId()).orElseThrow(() -> new Exception("찾는 위젯이 없습니다."));
        widgetEntitiy.changeTitleInfo(request.getWidgetTitle());
        widgetRepository.save(widgetEntitiy);

        return WidgetConverter.from(widgetEntitiy);
    }

    @Override
    public WidgetResponse updateImageWidget(WidgetImageRequest request) throws Exception {

        WidgetEntitiy widgetEntitiy = widgetRepository.findById(request.getId()).orElseThrow(() -> new Exception("찾는 위젯이 없습니다."));
        widgetEntitiy.changeImageInfo(request.getWidgetImage());
        widgetRepository.save(widgetEntitiy);

        return WidgetConverter.from(widgetEntitiy);
    }
}
