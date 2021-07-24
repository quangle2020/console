package com.quanglv.repository.first;

import com.quanglv.domain.first.FileStorages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesStoragesRepository extends JpaRepository<FileStorages, String> {
}
