package br.com.meli.anuncios.services;

import br.com.meli.anuncios.dto.AnuncioImput;
import br.com.meli.anuncios.dto.AnuncioOut;

import java.util.List;

public interface AnuncioService {
    AnuncioOut create(AnuncioImput anuncioDto);

    AnuncioOut findById(Long id);

    List<AnuncioOut> findAll();

    AnuncioOut update(AnuncioImput anuncioDto, Long id);

    void delete(Long id);
}
