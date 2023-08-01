package baby.lignin.workspace.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "url")
public class UrlEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_id_seq")
    @GenericGenerator(
            name = "url_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "url_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "10000000"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "10")
            }
    )
    private long id;

    @Column(nullable = false)
    private String longUrl;

    @Column(nullable = false)
    private Date createdDate;

    private Date expiresDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(Date expireDate) {
        this.expiresDate = expireDate;
    }

}