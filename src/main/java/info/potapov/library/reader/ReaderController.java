package info.potapov.library.reader;

import info.potapov.library.book.view.BookView;
import info.potapov.library.common.dto.SectionQueryDTO;
import info.potapov.library.reader.dto.ReaderDTO;
import info.potapov.library.reader.dto.ReaderDTOInfoAdapter;
import info.potapov.library.reader.entity.Reader;
import info.potapov.library.reader.service.ReaderService;
import info.potapov.library.view.ReaderView;
import info.potapov.library.view.ReaderViewMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService service;

    private final ReaderViewMapper viewMapper;

    public ReaderController(ReaderService readerService, ReaderViewMapper viewMapper) {
        this.service = readerService;
        this.viewMapper = viewMapper;
    }

    @PostMapping("/add")
    public void addUser(@RequestBody @Validated ReaderDTO readerDTO) {
        service.addUser(new ReaderDTOInfoAdapter(readerDTO));
    }

    @GetMapping("/count")
    public int count() {
        return service.getReadersCount();
    }

    @GetMapping("/all")
    public List<ReaderView> getBooks(@Validated SectionQueryDTO dto) {
        List<Reader> readers = service.getReadersOfRange(dto.getFrom(), dto.getLimit());
        return readers.stream()
                .map(viewMapper::toView)
                .collect(Collectors.toList());
    }

}

