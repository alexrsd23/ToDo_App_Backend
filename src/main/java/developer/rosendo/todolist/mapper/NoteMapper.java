package developer.rosendo.todolist.mapper;

import developer.rosendo.todolist.domain.note.Note;
import developer.rosendo.todolist.domain.note.NoteDTO;

public class NoteMapper {

    public static NoteDTO toDTO(Note note) {
        if (note == null) return null;

        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .description(note.getDescription())
                .startDate(note.getStartDate())
                .endDate(note.getEndDate())
                .status(note.getStatus())
                .build();
    }

    public static Note toEntity(NoteDTO dto) {
        if (dto == null) return null;

        return Note.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .status(dto.getStatus())
                .build();
    }
}