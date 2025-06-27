package developer.rosendo.todolist.repository;

import developer.rosendo.todolist.domain.note.Note;
import developer.rosendo.todolist.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    List<Note> findByProfile(Profile profile);
}