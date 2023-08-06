package baby.lignin.widget.entity;


import baby.lignin.workspace.model.request.WorkspaceUpdateRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "widget")
public class WidgetEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "widget_id")
    private Long id;

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String widgetTitle;

    @Column(nullable = false)
    private String widgetType;
    @Column
    private String widgetImage;

    @Column
    private double x;

    @Column
    private double y;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void changeXYInfo(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public void changeTitleInfo(String widgetTitle) {
        this.widgetTitle = widgetTitle;
    }

    public void changeImageInfo(String widgetImage) {
        this.widgetImage = widgetImage;

    }


}
