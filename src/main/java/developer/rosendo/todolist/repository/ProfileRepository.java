package developer.rosendo.todolist.repository;

import developer.rosendo.todolist.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, UUID> {
}
