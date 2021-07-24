package com.quanglv.repository;

import com.quanglv.domain.second.Transactions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TransactionsRepositoryImpl {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    EntityManager entityManager;

    public List<Transactions> getTransactions() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transactions> criteriaQuery = criteriaBuilder.createQuery(Transactions.class);
        Root<Transactions> studentRoot = criteriaQuery.from(Transactions.class);
        criteriaQuery.select(studentRoot);
        TypedQuery<Transactions> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Transactions> studentList = typedQuery.getResultList();
        return studentList;
    }
}
