package br.com.meli.anuncios.repositories;

import br.com.meli.anuncios.entitites.Visualizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisualizacaoRepository extends JpaRepository<Visualizacao, Long> {


}
