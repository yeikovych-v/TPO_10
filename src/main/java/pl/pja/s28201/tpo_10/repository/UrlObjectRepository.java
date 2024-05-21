package pl.pja.s28201.tpo_10.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import pl.pja.s28201.tpo_10.model.UrlObject;

import java.util.List;

public interface UrlObjectRepository extends CrudRepository<UrlObject, Long> {

    @NonNull
    List<UrlObject> findAll();
}
