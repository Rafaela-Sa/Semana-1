package br.com.meli.anuncios.repositories;

import br.com.meli.anuncios.entitites.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
