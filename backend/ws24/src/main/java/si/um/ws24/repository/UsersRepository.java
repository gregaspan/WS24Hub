package si.um.ws24.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.ws24.model.UsersEntity;
import si.um.ws24.model.UsersEntity$;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
@Transactional
public class UsersRepository implements PanacheRepository<UsersEntity> {
    @Inject
    JPAStreamer jpaStreamer;

    public Optional<UsersEntity> getUserById(int id) {
        return jpaStreamer.stream(UsersEntity.class)
                .filter(UsersEntity$.userid.equal(id))
                .findFirst();
    }

    public Stream<UsersEntity> getAllUsers() {
        return jpaStreamer.stream(UsersEntity.class);
    }

    public Stream<UsersEntity> getAllUsersServices() {
        StreamConfiguration<UsersEntity> sc = StreamConfiguration.of(UsersEntity.class)
                .joining(UsersEntity$.services);
        return jpaStreamer.stream(sc);
    }



}
