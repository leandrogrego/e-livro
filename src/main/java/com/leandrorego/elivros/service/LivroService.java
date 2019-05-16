package com.leandrorego.elivros.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leandrorego.elivros.model.Livro;
import com.leandrorego.elivros.repository.LivroRepository;
import java.awt.Image;
import javax.swing.ImageIcon;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro save(Livro livro) {
            return repository.saveAndFlush(livro);
    }

    public List<Livro> findAll() {
            return repository.findAll();
    }

    public Livro findOne(Long id) {
            return repository.getOne(id);
    }

    public void delete(Long id) {
            repository.deleteById(id);
    }

    public Image capa(Long id) {
        byte[] img = repository.getOne(id).getCapa();
        //InputStream is = new ByteArrayInputStream(img);
        Image image = new ImageIcon(img).getImage();
        return image;
    }

}
