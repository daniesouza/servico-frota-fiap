package br.com.frota.api.service;

import br.com.frota.FrotaApplication;
import br.com.frota.model.Viagem;
import br.com.frota.service.impl.ViagemServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class )
@SpringBootTest(classes = FrotaApplication.class)
public class ViagemServiceTest {


	@Mock
	private ViagemServiceImpl lancamentoContabilService;

	@Before
	public void setUpMock() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(lancamentoContabilService.save(any(Viagem.class)))
				.thenReturn(new Viagem());
	}


	@Test
	public void lancamentoContabilServiceTest() {
		Assert.assertNotNull(lancamentoContabilService.save(new Viagem()));
	}

}
