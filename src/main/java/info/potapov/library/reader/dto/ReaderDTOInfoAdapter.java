package info.potapov.library.reader.dto;

import info.potapov.library.reader.entity.ReaderInfo;

public class ReaderDTOInfoAdapter implements ReaderInfo {

    private final ReaderDTO dto;

    public ReaderDTOInfoAdapter(ReaderDTO readerDTO) {
        dto = readerDTO;
    }

    @Override
    public String getReaderName() {
        return dto.getReaderName();
    }

}
