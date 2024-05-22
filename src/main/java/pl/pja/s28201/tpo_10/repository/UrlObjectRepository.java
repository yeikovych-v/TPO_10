package pl.pja.s28201.tpo_10.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import pl.pja.s28201.tpo_10.model.UrlObject;

import java.util.List;
import java.util.Optional;

public interface UrlObjectRepository extends CrudRepository<UrlObject, String> {

    @NonNull
    List<UrlObject> findAll();
    @NonNull
    Optional<UrlObject> findById(@NonNull String id);
}
