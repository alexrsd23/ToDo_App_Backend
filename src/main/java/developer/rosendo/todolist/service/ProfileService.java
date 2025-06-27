package developer.rosendo.todolist.service;

import developer.rosendo.todolist.domain.profile.Profile;
import developer.rosendo.todolist.domain.profile.ProfileDTO;
import developer.rosendo.todolist.domain.profile.ShowProfileDTO;
import developer.rosendo.todolist.mapper.ProfileMapper;
import developer.rosendo.todolist.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static developer.rosendo.todolist.mapper.ProfileMapper.*;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileDTO create(ProfileDTO dto) {
        // Cria um Profile apenas com o nome
        Profile profile = Profile.builder()
                .name(dto.getName())
                .build();

        Profile saved = repository.save(profile);
        return ProfileMapper.toDTO(saved);
    }


    // Buscar perfil por ID com notas associadas (ShowProfileDTO)
    public ShowProfileDTO findById(UUID id) {
        Profile profile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return toShowDTO(profile);
    }

    public ProfileDTO update(UUID id, ProfileDTO dto) {
        Profile existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existing.setName(dto.getName()); // atualiza o nome

        Profile updated = repository.save(existing);
        return ProfileMapper.toDTO(updated);
    }


    // Listar todos os perfis (sem notas)
    public List<ShowProfileDTO> findAll() {
        return repository.findAll().stream()
                .map(ProfileMapper::toShowDTO)
                .toList();
    }

    // Deletar
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
