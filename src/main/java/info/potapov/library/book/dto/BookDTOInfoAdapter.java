package info.potapov.library.book.dto;

import info.potapov.library.book.entity.BookInfo;

public class BookDTOInfoAdapter implements BookInfo {

    private final BookDTO dto;

    public BookDTOInfoAdapter(BookDTO bookDTO) {
        dto = bookDTO;
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public String getAuthor() {
        return dto.getAuthor();
    }

    @Override
    public String getCode() {
        return dto.getCode();
    }

    @Override
    public String getName() {
        return dto.getName();
    }
}
