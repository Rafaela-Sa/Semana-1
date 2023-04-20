package br.com.meli.anuncios.services.impl;

import br.com.meli.anuncios.dto.AnuncioImput;
import br.com.meli.anuncios.dto.AnuncioOut;
import br.com.meli.anuncios.entitites.Anuncio;
import br.com.meli.anuncios.exceptions.BlankNameException;
import br.com.meli.anuncios.exceptions.InvalidWordException;
import br.com.meli.anuncios.exceptions.ResourceNotFoundException;
import br.com.meli.anuncios.exceptions.UniqueViolatedException;
import br.com.meli.anuncios.repositories.AnuncioRepository;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnuncioServiceImpl implements AnuncioService {
    @Autowired
    private AnuncioRepository repository;

    //Método - Tratamento de Exceções
    //Não pode haver anúncio com expressões inválidas no nome
    private void throwError(String message) {

        //Tratamento de Exceções 1 - Utilizando Lista
        List<String> blockedList = new ArrayList<>(Arrays.asList("Amazon", "Magalu", "Americanas"));
        blockedList.forEach((item) -> { //Percorrendo a lista //O item contém cada parâmetro
            if (item.toLowerCase().equals(message.toLowerCase())) {
                throw new InvalidWordException(String.format("%s Não é válido", message));
            }
        });

        //Tratamento de Exceções 2 - Utilizando switch case

//                switch (message.toLowerCase()) {
//            case "amazon":
//                throw new InvalidWordException("Amazon não é válida!");
//            case "magalu":
//                throw new InvalidWordException("Magalu não é válida!");
//            case "americanas":
//                throw new InvalidWordException("Americanas não é válida!");
//        }
    }

    private void checkIsNameBlank(AnuncioImput anuncioDto) {
        if (Objects.isNull(anuncioDto.getName()) || anuncioDto.getName().isBlank()) {
            throw new BlankNameException("Cheguei!");
        }
    }

    private void checkName(AnuncioImput anuncioDto) {

        Anuncio entity = new Anuncio();
        entity.setName(anuncioDto.getName());

        if(entity.getName().equals(repository.getClass().getName())) {
            throw new UniqueViolatedException ("Deu bom!");
        }

//        Set<AnuncioRepository> blockedList = new HashSet<>();
//        blockedList.toString();

//        blockedList.forEach((item) -> {
//                    if(entity.getName().equals(blockedList.getClass().getName())) {
//                        anuncioDto.toString();
//                        throw new UniqueViolatedException ("Deu bom!");
//                    }

    }

    private void checkInvalidPrice(AnuncioImput anuncioDto) {

        if(Objects.isNull(anuncioDto.getPrecoBase()) || anuncioDto.getPrecoBase())

    }

    @Override
    public AnuncioOut create(AnuncioImput anuncioDto) {

        checkName(anuncioDto);

        checkIsNameBlank(anuncioDto);

        throwError(anuncioDto.getName()); //Método - Tratamento de Exceções

        Anuncio entity = new Anuncio();
        entity.setName(anuncioDto.getName()); //Está pegando o atributo Nome que está na na classe dto e colocando na classe entity

        Anuncio savedEntity = repository.save(entity); //Salvando o atributo Nome, que foi colocado na classe entity, no repository, onde está o BD

        AnuncioOut returnDto = new AnuncioOut();
        returnDto.setId(savedEntity.getId());
        returnDto.setName(savedEntity.getName());

        return returnDto;
    }

    @Override
    public AnuncioOut findById(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio nao encontrado"));

        AnuncioOut returnDto = new AnuncioOut(entity.getId(), entity.getName());

        return returnDto;
    }

    @Override
    public List<AnuncioOut> findAll() {
        List<Anuncio> entities = repository.findAll();

        return entities.stream().map(e -> new AnuncioOut(e.getId(), e.getName())).collect(Collectors.toList());
    }


    @Override
    public AnuncioOut update(AnuncioImput anuncioDto, Long id) {
        checkIsNameBlank(anuncioDto);

        throwError(anuncioDto.getName());

        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio não encontrado"));

        entity.setName(anuncioDto.getName());

        entity = repository.save(entity);

        return new AnuncioOut(entity.getId(), entity.getName());

        //O objeto entity foi instanciado com dois construtores: findById e save
    }


    @Override
    public void delete(Long id) {
        Anuncio entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anuncio não encontrado")); //orElseThrow é uma condição que substitui o If

        repository.delete(entity);
    }
}
