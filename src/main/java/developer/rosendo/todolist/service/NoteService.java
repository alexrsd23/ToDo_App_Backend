package developer.rosendo.todolist.service;

import developer.rosendo.todolist.domain.note.Note;
import developer.rosendo.todolist.domain.note.NoteDTO;
import developer.rosendo.todolist.domain.note.NoteStatus;
import developer.rosendo.todolist.domain.profile.Profile;
import developer.rosendo.todolist.mapper.NoteMapper;
import developer.rosendo.todolist.repository.NoteRepository;
import developer.rosendo.todolist.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final ProfileRepository profileRepository;

    public NoteDTO createNote(UUID profileId, NoteDTO noteDTO) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        Note note = NoteMapper.toEntity(noteDTO);
        note.setProfile(profile);
        note.setStatus(NoteStatus.TO_DO); // status default

        Note saved = noteRepository.save(note);
        return NoteMapper.toDTO(saved);
    }

    public List<NoteDTO> getNotesByProfile(UUID profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return noteRepository.findByProfile(profile).stream()
                .map(NoteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(UUID noteId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        return NoteMapper.toDTO(note);
    }

    @Transactional
    public NoteDTO updateNote(UUID id, NoteDTO noteDTO) {
        Note existing = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // Atualiza apenas se ainda não for DONE
        if (existing.getStatus() == NoteStatus.DONE) {
            throw new IllegalStateException("Cannot update a completed note");
        }

        existing.setTitle(noteDTO.getTitle());
        existing.setDescription(noteDTO.getDescription());
        existing.setStartDate(noteDTO.getStartDate());
        existing.setEndDate(noteDTO.getEndDate());

        // (Status será ignorado aqui para manter a lógica atual)
        Note saved = noteRepository.save(existing);
        return NoteMapper.toDTO(saved);
    }


    @Transactional
    public NoteDTO updateNoteStatus(UUID noteId, NoteStatus newStatus) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (note.getStatus() == NoteStatus.DONE) {
            throw new IllegalStateException("Cannot update a completed note");
        }

        if (note.getStatus() == NoteStatus.TO_DO && newStatus == NoteStatus.IN_PROGRESS) {
            note.setStatus(NoteStatus.IN_PROGRESS);
        } else if (note.getStatus() == NoteStatus.IN_PROGRESS && newStatus == NoteStatus.DONE) {
            note.setStatus(NoteStatus.DONE);
        } else {
            throw new IllegalStateException("Invalid status transition");
        }

        Note updated = noteRepository.save(note);
        return NoteMapper.toDTO(updated);
    }

    public void deleteNote(UUID noteId) {
        noteRepository.deleteById(noteId);
    }
}
