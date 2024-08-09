package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;

public interface ICategoriaService {
    public List<Categoria> listarCategorias();

    public Categoria buscarCategoriaPorId(Long id);

    public Boolean guardarCategoria(Categoria categoria);

    public void eliminarCategoria(Categoria categoria);

    public Boolean verificarCategoriaDuplicada(Categoria categoria);
}
