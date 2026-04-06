package com.libreriaApp.libreria.repositories;

import com.libreriaApp.libreria.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILibrosRepository extends JpaRepository<Libro, Long> {

    //Aqui creo los metodos customizados, si los necesito.
    //Los metodos CRUD clasico ya extienden de JPARepository


}
