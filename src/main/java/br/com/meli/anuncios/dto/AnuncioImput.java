package br.com.meli.anuncios.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnuncioImput {

    private String name;
    private double precoBase;
    private double precoPromocional;
}
