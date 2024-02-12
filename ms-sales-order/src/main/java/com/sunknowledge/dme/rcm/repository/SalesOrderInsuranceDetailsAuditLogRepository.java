package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetailsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SalesOrderInsuranceDetailsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderInsuranceDetailsAuditLogRepository
    extends ReactiveCrudRepository<SalesOrderInsuranceDetailsAuditLog, Long>, SalesOrderInsuranceDetailsAuditLogRepositoryInternal {
    Flux<SalesOrderInsuranceDetailsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderInsuranceDetailsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderInsuranceDetailsAuditLog> findAll();

    @Override
    Mono<SalesOrderInsuranceDetailsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderInsuranceDetailsAuditLogRepositoryInternal {
    <S extends SalesOrderInsuranceDetailsAuditLog> Mono<S> save(S entity);

    Flux<SalesOrderInsuranceDetailsAuditLog> findAllBy(Pageable pageable);

    Flux<SalesOrderInsuranceDetailsAuditLog> findAll();

    Mono<SalesOrderInsuranceDetailsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderInsuranceDetailsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
