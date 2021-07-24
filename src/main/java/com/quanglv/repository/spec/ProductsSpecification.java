package com.quanglv.repository.spec;

import com.quanglv.domain.first.Products;
import com.quanglv.type.OperationTypes;
import lombok.SneakyThrows;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductsSpecification implements Specification<Products> {

    private SearchCriteria criteria;

    public ProductsSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @SneakyThrows
    @Override
    public Predicate toPredicate(Root<Products> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase(OperationTypes.GREATER_THAN.getCode())) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue());

        } else if (criteria.getOperation().equalsIgnoreCase(OperationTypes.LESS_THAN.getCode())) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue());

        } else
            if (criteria.getOperation().equalsIgnoreCase(OperationTypes.GREATER_THAN_DATE.getCode())) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), toDate(criteria.getValue()));

        } else if (criteria.getOperation().equalsIgnoreCase(OperationTypes.LESS_THAN_DATE.getCode())) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), toDate(criteria.getValue()));
        }

        else if (criteria.getOperation().equalsIgnoreCase(OperationTypes.LIKE.getCode())) {
            return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");

        } else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }

    private Date toDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }
}
