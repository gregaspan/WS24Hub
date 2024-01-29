package si.um.ws24.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "services", schema = "public", catalog = "postgres")
public class ServicesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "serviceid")
    private int serviceid;
    @Basic
    @Column(name = "servicename")
    private String servicename;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_services",
            joinColumns = @JoinColumn(name = "serviceid"),
            inverseJoinColumns = @JoinColumn(name = "userid")
    )

    private List<UsersEntity> users = new ArrayList<>();

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicesEntity that = (ServicesEntity) o;
        return serviceid == that.serviceid && Objects.equals(servicename, that.servicename) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceid, servicename, description);
    }
}
