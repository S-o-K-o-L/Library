package dao;

import entities.Chapter;

import java.util.Optional;
import java.util.Set;

public interface ChapterDAO {
    void add(Chapter chapter, Long idBook);
    Optional<Chapter> findById(Long id);
    Optional<Chapter> findByNumber(Integer number, Long idBook);
    Set<Chapter> findAllForIdBook(Long idBook);
    void update(Chapter chapter);
    void remove(Chapter chapter);
}
