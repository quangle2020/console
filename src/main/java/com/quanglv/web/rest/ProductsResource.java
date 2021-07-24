package com.quanglv.web.rest;

import com.quanglv.service.ProductsService;
import com.quanglv.service.dto.ProductsDTO;
import com.quanglv.service.dto.ProductsSearchRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductsResource {

    @Autowired
    private ProductsService productsService;

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<ProductsDTO>> getAll(){
        return ResponseEntity.ok(productsService.getAll());
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductsDTO> save(@RequestBody ProductsDTO request){
        return ResponseEntity.ok(productsService.save(request));
    }
}
