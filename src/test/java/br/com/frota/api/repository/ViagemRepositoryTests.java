package br.com.frota.api.repository;

import br.com.frota.FrotaApplication;
import br.com.frota.dao.ViagemRepository;
import br.com.frota.model.Viagem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FrotaApplication.class)
public class ViagemRepositoryTests {


	@Autowired
	private ViagemRepository viagemRepository;

	@Before
	public void setup() {

		Viagem viagem = new Viagem();
		viagem.setId("637e8d6c-435f-4759-ae4a-a87d945e85c6");
		viagem.setValor(BigDecimal.TEN);
		viagem.setCliente("ADAFSAFAS ASDSADSADSA");
		viagem.setCepDestino("05678=345");

		viagemRepository.save(viagem);
	}

	@Test
	public void shouldNotReturnLancamentoContabil() {


		Optional<Viagem> lancamentoContabil = viagemRepository.findById("22323");

		Assert.isTrue( !lancamentoContabil.isPresent(), "Should not return viagem!");
	}

	@Test
	public void shouldReturnLancamentoContabil() {


		Optional<Viagem> lancamentoContabil = viagemRepository.findById("637e8d6c-435f-4759-ae4a-a87d945e85c6");

		Assert.isTrue( lancamentoContabil.isPresent(), "Should return viagem!");
	}
}
