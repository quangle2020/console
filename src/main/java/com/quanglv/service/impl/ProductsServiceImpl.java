package com.quanglv.service.impl;

import com.quanglv.domain.first.Products;
import com.quanglv.repository.first.ProductsRepository;
import com.quanglv.repository.spec.ProductsSpecification;
import com.quanglv.repository.spec.SearchCriteria;
import com.quanglv.service.ProductsService;
import com.quanglv.service.dto.ProductsDTO;
import com.quanglv.service.dto.ProductsSearchRequestDTO;
import com.quanglv.service.dto.ProductsSearchResponseDTO;
import com.quanglv.service.mapper.ProductsMapper;
import com.quanglv.service.mapper.ProductsMapperManual;
import com.quanglv.type.OperationTypes;
import com.quanglv.type.StatusTypes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductsRepository repository;

    @Autowired
    private ProductsMapper mapper;

    private ProductsMapperManual mapperManual = new ProductsMapperManual();

    @Override
    @Transactional(readOnly = true)
    public List<ProductsDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    @Transactional
    public ProductsDTO save(ProductsDTO request) {
        request.setCreatedUser(1L);
        request.setUpdatedUser(1L);
        request.setStatus(StatusTypes.ACTIVE.getCode());
        request.setCreatedDate(new Date());
        request.setUpdatedDate(new Date());
        request.setPrice(request.getPrice());
        return mapper.toDto(repository.save(mapper.toEntity(request)));
    }

    @Override
    public ProductsSearchResponseDTO search(ProductsSearchRequestDTO req) {

        if (req.getPage() == null)
            req.setPage(0);
        if (req.getSize() == null)
            req.setSize(10);
        if (StringUtils.isBlank(req.getOrder()))
            req.setOrder("DESC");
        if (StringUtils.isBlank(req.getSortName()))
            req.setSortName("id");

        Sort sort;
        if (req.getOrder().equalsIgnoreCase("DESC"))
            sort = Sort.by(req.getSortName()).descending();
        else sort = Sort.by(req.getSortName()).ascending();

        ProductsSearchResponseDTO response = new ProductsSearchResponseDTO();

        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);

        ProductsSpecification specName = null;
        ProductsSpecification specCode = null;
        ProductsSpecification specPriceFrom = null;
        ProductsSpecification specPriceTo = null;
        ProductsSpecification specPromo = null;
        ProductsSpecification specCreUser = null;
        ProductsSpecification specDateFrom = null;
        ProductsSpecification specDateTo = null;

        if (!StringUtils.isBlank(req.getName()))
            specName = buildSpec("name", OperationTypes.LIKE.getCode(), req.getName());
        if (!StringUtils.isBlank(req.getCode()))
            specCode = buildSpec("code", OperationTypes.EQUAL.getCode(), req.getCode());
        if(req.getPriceFrom() != null)
            specPriceFrom = buildSpec("price", OperationTypes.GREATER_THAN.getCode(), req.getPriceFrom().toString());
        if(req.getPriceTo() != null)
            specPriceTo = buildSpec("price", OperationTypes.LESS_THAN.getCode(), req.getPriceTo().toString());
        if(req.getPromotion() != null)
            specPromo = buildSpec("promotion", OperationTypes.EQUAL.getCode(), req.getPromotion().toString());
        if(req.getCreatedUser() != null)
            specCreUser = buildSpec("createdUser", OperationTypes.EQUAL.getCode(), req.getCreatedUser().toString());
        if (!StringUtils.isBlank(req.getDateFrom()))
            specDateFrom = buildSpec("createdDate", OperationTypes.GREATER_THAN_DATE.getCode(), req.getDateFrom());
        if (!StringUtils.isBlank(req.getDateTo()))
            specDateTo = buildSpec("createdDate", OperationTypes.LESS_THAN_DATE.getCode(), req.getDateTo());

        Page<Products> pageProducts = repository.findAll(Specification.where(specName).and(specCode)
                .and(specPriceFrom).and(specPriceTo).and(specPromo).and(specCreUser)
                .and(specDateFrom).and(specDateTo), pageable);

        response.setItem(pageProducts.getContent().stream().map(mapperManual::toSearch).collect(Collectors.toList()));
        response.setTotal(pageProducts.getTotalElements());
        return response;
    }

    private ProductsSpecification buildSpec(String key, String operator, String value) {
        return new ProductsSpecification(new SearchCriteria(key, operator, value));
    }
}