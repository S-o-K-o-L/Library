package service;

import entities.Chapter;

import java.util.Optional;
import java.util.Set;

public interface ChapterService {
    void add(Chapter chapter, Long idBook);
    Chapter findById(Long id);
    Chapter findByNumber(Integer number, Long idBook);
    Set<Chapter> findAllForIdBook(Long idBook);
    void update(Chapter chapter);
    void remove(Chapter chapter);
}
