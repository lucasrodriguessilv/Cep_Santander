import com.desafio.Cep_Santander.CepSantanderApplication;
import com.desafio.Cep_Santander.client.CepClient;
import com.desafio.Cep_Santander.dto.CepResponseDTO;
import com.desafio.Cep_Santander.service.CepService;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = CepSantanderApplication.class)
public class CepServiceWireMockTest {

    private static WireMockServer wireMockServer;

    @Autowired
    private CepService cepService;

    @MockBean
    private CepClient cepClient;

    @BeforeAll
    static void setupServer() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor("localhost", 8089);
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @BeforeEach
    void setup() {

        stubFor(get(urlEqualTo("/ws/01001000/json/"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                        {
                          "cep": "01001-000",
                          "logradouro": "Praça da Sé",
                          "bairro": "Sé",
                          "localidade": "São Paulo",
                          "uf": "SP"
                        }
                        """)));

        CepResponseDTO mock = new CepResponseDTO();
        mock.setCep("01001-000");
        mock.setLogradouro("Praça da Sé");
        mock.setBairro("Sé");
        mock.setLocalidade("São Paulo");
        mock.setUf("SP");

        when(cepClient.buscarCep("01001000")).thenReturn(mock);
    }

    @Test
    void deveBuscarCepComWireMock() {

        var response = cepService.buscarCep("01001000");

        Assertions.assertNotNull(response);
        Assertions.assertEquals("01001-000", response.getCep());
    }
}