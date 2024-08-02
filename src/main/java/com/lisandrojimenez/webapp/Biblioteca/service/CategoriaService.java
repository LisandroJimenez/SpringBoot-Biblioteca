package com.lisandrojimenez.webapp.Biblioteca.service;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;
import com.lisandrojimenez.webapp.Biblioteca.repository.CategoriaRepository;

@Service
public class CategoriaService implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRespository;

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRespository.findAll();
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        return categoriaRespository.findById(id).orElse(null);
    }

    @Override
    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRespository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoriaRespository.delete(categoria);
    }
}
