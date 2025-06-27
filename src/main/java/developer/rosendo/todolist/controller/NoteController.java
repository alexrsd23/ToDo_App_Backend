package developer.rosendo.todolist.controller;

import developer.rosendo.todolist.domain.note.NoteDTO;
import developer.rosendo.todolist.domain.note.NoteStatus;
import developer.rosendo.todolist.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin("*")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/profiles/{profileId}/notes")
    public ResponseEntity<NoteDTO> createNote(@PathVariable UUID profileId, @RequestBody NoteDTO noteDTO) {
        NoteDTO createdNote = noteService.createNote(profileId, noteDTO);
        return ResponseEntity.ok(createdNote);
    }

    @GetMapping("/profiles/{profileId}/notes")
    public ResponseEntity<List<NoteDTO>> getNotesByProfile(@PathVariable UUID profileId) {
        List<NoteDTO> notes = noteService.getNotesByProfile(profileId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable UUID id) {
        NoteDTO note = noteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }

    @PutMapping("/notes/{id}/status")
    public ResponseEntity<NoteDTO> updateNoteStatus(@PathVariable UUID id, @RequestParam NoteStatus status) {
        NoteDTO updatedNote = noteService.updateNoteStatus(id, status);
        return ResponseEntity.ok(updatedNote);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteDTO> updateNote(@PathVariable UUID id, @RequestBody NoteDTO noteDTO) {
        NoteDTO updatedNote = noteService.updateNote(id, noteDTO);
        return ResponseEntity.ok(updatedNote);
    }


    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable UUID id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }
}
