package br.com.meli.anuncios.services;

import br.com.meli.anuncios.dto.VisualizacaoDtoImput;
import br.com.meli.anuncios.dto.VisualizacaoDtoOut;

public interface VisualizacaoService {

    VisualizacaoDtoOut create(VisualizacaoDtoImput visualizacaoDto);
}
