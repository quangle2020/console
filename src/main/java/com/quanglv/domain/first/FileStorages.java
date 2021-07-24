package com.quanglv.domain.first;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "file_storages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileStorages extends AbstractAuditingEntity {
    @Id
//    @SequenceGenerator(name = "file_storage_seq", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_storage_seq")
    @Column(name = "id", nullable = false)
    @NotNull
    private String id;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "storage_days")
    private Integer storageDays;

    @Column(name = "status")
    private String status;
}
