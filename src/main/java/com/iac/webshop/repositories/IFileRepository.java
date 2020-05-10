package com.iac.webshop.repositories;

import com.iac.webshop.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Table;

@Table(schema = "public", name = "file")
public interface IFileRepository extends JpaRepository<File, Long> {

}

