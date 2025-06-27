package developer.rosendo.todolist.domain.profile;

import developer.rosendo.todolist.domain.note.NoteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowProfileDTO {

    private UUID id;
    private String name;
    private List<NoteDTO> notes;
}
