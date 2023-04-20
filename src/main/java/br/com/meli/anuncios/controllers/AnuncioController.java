package br.com.meli.anuncios.controllers;

import br.com.meli.anuncios.dto.AnuncioImput;
import br.com.meli.anuncios.dto.AnuncioOut;
import br.com.meli.anuncios.exceptions.InvalidWordException;
import br.com.meli.anuncios.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnuncioImput anuncioDto){
        try{
            return new ResponseEntity<>(anuncioService.create(anuncioDto), HttpStatus.CREATED);
        } catch (Exception error){
            return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(anuncioService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AnuncioOut> dtos = anuncioService.findAll();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AnuncioImput anuncioDto) {
        try{
            AnuncioOut returnDtoOut = anuncioService.update(anuncioDto, id);
            return ResponseEntity.ok(returnDtoOut);
        } catch (Exception error){
            return ResponseEntity.badRequest().body(error.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        anuncioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
