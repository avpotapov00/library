package info.potapov.library.book;

import info.potapov.library.book.dto.BookDTO;
import info.potapov.library.book.dto.BookDTOInfoAdapter;
import info.potapov.library.book.dto.ChangeCodeDTO;
import info.potapov.library.book.entity.Book;
import info.potapov.library.book.service.BookService;
import info.potapov.library.book.view.BookView;
import info.potapov.library.book.view.BookViewMapper;
import info.potapov.library.common.dto.SectionQueryDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService service;

    private final BookViewMapper viewMapper;

    public BookController(BookService service, BookViewMapper viewMapper) {
        this.service = service;
        this.viewMapper = viewMapper;
    }

    @GetMapping("/{code}")
    public BookView getBook(@PathVariable String code) {
        Book book = service.getBook(code);
        return viewMapper.toView(book);
    }

    @GetMapping("/user/{userId}")
    public List<BookView> getBooks(@PathVariable Long userId) {
        List<Book> book = service.getBooksByReader(userId);
        return book.stream()
                .map(viewMapper::toView)
                .collect(Collectors.toList());
    }

    @PutMapping("/add")
    public void addBook(@RequestBody @Validated BookDTO bookDTO) {
        service.create(new BookDTOInfoAdapter(bookDTO));
    }

    @DeleteMapping("/{code}")
    public void deleteBook(@PathVariable String code) {
        service.deleteBook(code);
    }

    @PostMapping("/change")
    public void changeCode(@RequestBody @Validated ChangeCodeDTO dto) {
        service.changeCode(dto.getOldCode(), dto.getNewCode());
    }

    @GetMapping("/count")
    public int getBooksCount() {
        return service.getBooksCount();
    }

    @GetMapping("/given")
    public int getBooksWithOwnerCount() {
        return service.getBooksWithOwnerCount();
    }

    @GetMapping("/all")
    public List<BookView> getBooks(@Validated SectionQueryDTO dto) {
        List<Book> books = service.getBooksOfRange(dto.getFrom(), dto.getLimit());
        return books.stream()
                .map(viewMapper::toView)
                .collect(Collectors.toList());
    }

}
