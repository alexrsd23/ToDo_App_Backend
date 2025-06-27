package developer.rosendo.todolist.controller;

import developer.rosendo.todolist.domain.profile.Profile;
import developer.rosendo.todolist.domain.profile.ProfileDTO;
import developer.rosendo.todolist.domain.profile.ShowProfileDTO;
import developer.rosendo.todolist.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProfileController {

    private final ProfileService service;

    @PostMapping
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto) {
        ProfileDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }


    @GetMapping
    public ResponseEntity<List<ShowProfileDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowProfileDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable UUID id, @RequestBody ProfileDTO dto) {
        ProfileDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
