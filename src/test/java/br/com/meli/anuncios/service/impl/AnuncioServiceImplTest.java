package br.com.meli.anuncios.service.impl;

import br.com.meli.anuncios.dto.AnuncioImput;
import br.com.meli.anuncios.dto.AnuncioOut;
import br.com.meli.anuncios.entitites.Anuncio;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import br.com.meli.anuncios.services.impl.AnuncioServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DisplayName("Anuncio service")
@ExtendWith(MockitoExtension.class)
public class AnuncioServiceImplTest {

    //Instancia um objeto e define o construtor, que estará vazio
    //Sem o construtor a classe não é instanciada
    @InjectMocks
    private AnuncioServiceImpl anuncioService; //AnuncioServiceImpl será o construtor

    //Mock é uma classe que simula os comportamentos de outra classe
    //Utilizado qnd se quer testar a lógica de uma classe que tem dependência de outra classe, mas de forma isolada
    //Instancia a classe repository real, mas ao invés de ficar gravada no BD, ficará gravado no Mockito
    //Será criado uma repository FAKE para definir como parâmetro do construtor, já que este está vazio
    //Controla o comportamento dos métodos, porém não é um método real **
    @Mock
    private AnuncioRepository anuncioRepository; //AnuncioRepository (FAKE) será o parâmetro do construtor AnuncioServiceImpl

    @Test
    @DisplayName("Quando create for chamado, deverá retornar um anuncioDto válido")
    void creatTest() {
        //Preparar os dados
        AnuncioImput anuncioImput = new AnuncioImput("name");

        Anuncio anuncio = new Anuncio();
        anuncio.setName("name");

        Mockito.when(anuncioRepository.save(anuncio)).thenReturn(anuncio); //Substitui o anúncio do método save, que está na camada AnuncioServiceImpl, pelo retorno de um novo anúncio

        //Chamar o método
        AnuncioOut result = anuncioService.create(anuncioImput);

        //Verificar se o método foi bem sucedido
        Assertions.assertEquals(result.getName(), anuncioImput.getName());
        Mockito.verify(anuncioRepository, Mockito.times(1)).save(anuncio);
    }

    @Test
    @DisplayName("Quando findById for chamado, deverá retornar um anuncioDto valido")
    void findByIdTest () {
        //Preparar os dados
        AnuncioOut anuncioDtoOut = new AnuncioOut();

        Anuncio anuncio = new Anuncio();
        anuncio.setId(1L);
        anuncio.setName("Cassio");

        //Chamar o método
        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio)); //Substitui o id do método findById, que está na camada AnuncioServiceImpl, pelo retorno de um novo anúncio

        //Verificar se o método foi bem sucedido
        AnuncioOut resultDto = anuncioService.findById(1L);
        Assertions.assertEquals(resultDto.getId(), 1L);
        Mockito.verify(anuncioRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    @DisplayName("Quando findById for chamado, deverá retornar um error")
    void findByIdErrorTest () {
        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> anuncioService.findById(1L));
    }

    @Test
    @DisplayName("Quando findAll for chamado, deverá retornar um anuncioDto válido")
    void findAllTest() {
        //Preparar os dados
        List<AnuncioOut> anuncioDtoOutput = new ArrayList<AnuncioOut>();

        Anuncio anuncio = new Anuncio();
        anuncio.setName("name");
        anuncio.setId(1L);

        Mockito.when(anuncioRepository.findAll()).thenReturn(Arrays.asList(anuncio)); //Substitui o método findAll, que está na camada AnuncioServiceImpl, pelo retorno de uma lista de anúncio

        //Chamar o método
        List<AnuncioOut> result = anuncioService.findAll();

        //Verificar se o método foi bem sucedido
        Assertions.assertEquals(result.size(), 1);
        Assertions.assertEquals(result.get(0).getName(),"name");
        Assertions.assertEquals(result.get(0).getId(),1L);
        Mockito.verify(anuncioRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Quando findAll for chamado, deverá retornar um anuncioDto válido")
    void findAllErrorTest() {
        Mockito.when(anuncioRepository.findAll()).thenThrow(new Error());

        Assertions.assertThrows(Error.class, ()->anuncioService.findAll());
    }

    @Test
    @DisplayName("Quando update for chamado, deverá retornar um anuncioDto válido")
    void updateTest() {
        //Preparar os dados
        AnuncioImput anuncioImput = new AnuncioImput("name");

        Anuncio anuncio = new Anuncio();
        anuncio.setName("name");
        anuncio.setId(1L);

        //No método update, da camada AnuncioServiceImpl, o objeto entity foi instanciado com dois construtores: findById e save
        //De forma que deverá ter um Mockito para cada construtor
        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio)); //Substitui o id do método findById, que está na camada AnuncioServiceImpl, pelo retorno de um anúncio não nulo (optinal)
        Mockito.when(anuncioRepository.save(anuncio)).thenReturn(anuncio); //Substitui o anúncio do método save, que está na camada AnuncioServiceImpl, pelo retorno de um novo anúncio

        //Chamar o método
        AnuncioOut result = anuncioService.update(anuncioImput, anuncio.getId());

        //Verificar se o teste foi bem sucedido
        Assertions.assertEquals(result.getName(), anuncioImput.getName());
        Mockito.verify(anuncioRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(anuncioRepository, Mockito.times(1)).save(anuncio);
    }

    @Test
    @DisplayName("Quando delete for chamado, deverá retornar um anuncioDto vazio")
    void deleteTest() {
        //Preparar os dados
        Anuncio anuncio = new Anuncio();

        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.of(anuncio)); //Substitui o id do método findById, que está na camada AnuncioServiceImpl, pelo retorno de um novo anúncio

        //Chamar o método
        anuncioService.delete(1L);

        //Verificar se o teste foi bem sucedido
        Mockito.verify(anuncioRepository, Mockito.times(1)).delete(anuncio);

    }

    @Test
    @DisplayName("Quando delete for chamado, deverá retornar um anuncioDto vazio")
    void deleteErrorTest() {
        Mockito.when(anuncioRepository.findById(1L)).thenReturn(Optional.empty());


        Assertions.assertThrows(ResourceNotFoundException.class,()->anuncioService.delete(1L));
    }

}
