package service.impl;

import dao.ChapterDAO;
import dao.impl.ChapterDAOImpl;
import entities.Chapter;
import service.ChapterService;

import java.util.Optional;
import java.util.Set;

public class ChapterServiceImpl implements ChapterService {
    private final ChapterDAO chapterDAO = new ChapterDAOImpl();
    @Override
    public void add(Chapter chapter, Long idBook) {
        chapterDAO.add(chapter,idBook);
    }

    @Override
    public Chapter findById(Long id) {
        return chapterDAO.findById(id).orElse(null);
    }

    @Override
    public Chapter findByNumber(Integer number, Long idBook) {
        return chapterDAO.findByNumber(number,idBook).orElse(null);
    }

    @Override
    public Set<Chapter> findAllForIdBook(Long idBook) {
        return chapterDAO.findAllForIdBook(idBook);
    }

    @Override
    public void update(Chapter chapter) {
        chapterDAO.update(chapter);
    }

    @Override
    public void remove(Chapter chapter) {
        chapterDAO.remove(chapter);
    }
}
