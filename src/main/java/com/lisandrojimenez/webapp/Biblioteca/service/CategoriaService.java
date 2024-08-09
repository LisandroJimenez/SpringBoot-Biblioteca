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
    public Boolean guardarCategoria(Categoria categoria) {
        if (!verificarCategoriaDuplicada(categoria)) {
            categoriaRespository.save(categoria);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoriaRespository.delete(categoria);
    }

    @Override
    public Boolean verificarCategoriaDuplicada(Categoria categoriaNueva) {
        List<Categoria> categorias = listarCategorias();
        Boolean flag = false;
        for (Categoria categoria2 : categorias) {
            if (categoriaNueva.getNombreCategoria().equalsIgnoreCase(categoria2.getNombreCategoria().trim()) && categoriaNueva.getId().equals(categoria2.getId())) {
                return true;
            }
        }
        return flag;
    }
}
