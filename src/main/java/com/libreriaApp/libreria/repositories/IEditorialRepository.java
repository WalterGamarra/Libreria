package com.libreriaApp.libreria.repositories;

import com.libreriaApp.libreria.models.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEditorialRepository extends JpaRepository <Editorial, Long> {

}
