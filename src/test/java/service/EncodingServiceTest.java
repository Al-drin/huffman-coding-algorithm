package service;

import org.example.szymongarbien.huffmancoding.domain.HuffMessage;
import org.example.szymongarbien.huffmancoding.service.EncodingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EncodingServiceTest {

    private EncodingService service;

    @BeforeEach
    void setUp() {
        service = new EncodingService();
    }

    @Test
    void encodeAndDecode_stringsEqual() {
        //given
        String toEncode = "TO BE OR NOT TO BE";
        //when
        HuffMessage message = service.encode(toEncode);
        String decoded = service.decode(message);
        //then
        assertEquals(toEncode, decoded);
    }
}
