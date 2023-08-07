package baby.lignin.widget.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WidgetAddRequest {
    private Long roomId;
    private String widgetTitle;
    private String widgetType;
    private String widgetImage;
    private double x;
    private double y;
}
