package baby.lignin.widget.model.response;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WidgetResponse {
    private Long id;
    private Long roomId;
    private String widgetTitle;
    private String widgetType;
    private String widgetImage;
    private double x;
    private double y;
}

