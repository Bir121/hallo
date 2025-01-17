package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SalesOrderDocumentsAuditLog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Data R2DBC repository for the SalesOrderDocumentsAuditLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesOrderDocumentsAuditLogRepository
    extends ReactiveCrudRepository<SalesOrderDocumentsAuditLog, Long>, SalesOrderDocumentsAuditLogRepositoryInternal {
    Flux<SalesOrderDocumentsAuditLog> findAllBy(Pageable pageable);

    @Override
    <S extends SalesOrderDocumentsAuditLog> Mono<S> save(S entity);

    @Override
    Flux<SalesOrderDocumentsAuditLog> findAll();

    @Override
    Mono<SalesOrderDocumentsAuditLog> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface SalesOrderDocumentsAuditLogRepositoryInternal {
    <S extends SalesOrderDocumentsAuditLog> Mono<S> save(S entity);

    Flux<SalesOrderDocumentsAuditLog> findAllBy(Pageable pageable);

    Flux<SalesOrderDocumentsAuditLog> findAll();

    Mono<SalesOrderDocumentsAuditLog> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<SalesOrderDocumentsAuditLog> findAllBy(Pageable pageable, Criteria criteria);

}
