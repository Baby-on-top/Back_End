package baby.lignin.widget.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WidgetXYRequest {
    private Long id;
    private double x;
    private double y;
}
