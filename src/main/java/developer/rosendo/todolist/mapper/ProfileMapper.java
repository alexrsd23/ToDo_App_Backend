package developer.rosendo.todolist.mapper;

import developer.rosendo.todolist.domain.note.NoteDTO;
import developer.rosendo.todolist.domain.profile.Profile;
import developer.rosendo.todolist.domain.profile.ProfileDTO;
import developer.rosendo.todolist.domain.profile.ShowProfileDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ProfileMapper {

    // Usado na criação (Profile -> ProfileDTO de resposta com id)
    public static ProfileDTO toDTO(Profile profile) {
        if (profile == null) return null;

        return ProfileDTO.builder()
                .name(profile.getName()) // não inclui o id pois o DTO não tem
                .build();
    }

    // Usado quando exibimos um perfil completo (com notas)
    public static ShowProfileDTO toShowDTO(Profile profile) {
        if (profile == null) return null;

        List<NoteDTO> notes = null;
        if (profile.getNotes() != null) {
            notes = profile.getNotes().stream()
                    .map(note -> NoteDTO.builder()
                            .id(note.getId())
                            .title(note.getTitle())
                            .description(note.getDescription())
                            .startDate(note.getStartDate())
                            .endDate(note.getEndDate())
                            .status(note.getStatus())
                            .build())
                    .collect(Collectors.toList());
        }

        return ShowProfileDTO.builder()
                .id(profile.getId())
                .name(profile.getName())
                .notes(notes)
                .build();
    }

    // Cria a entidade a partir do DTO (ProfileDTO não tem id)
    public static Profile toEntity(ProfileDTO dto) {
        if (dto == null) return null;

        return Profile.builder()
                .name(dto.getName())
                .build();
    }
}
