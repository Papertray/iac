package com.iac.webshop.repositories;

import com.iac.webshop.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFileRepository extends JpaRepository<File, Long> {

}

