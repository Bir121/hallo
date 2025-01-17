package com.sunknowledge.dme.rcm.service;

import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrder}.
 */
public interface PurchaseOrderService {
    /**
     * Save a purchaseOrder.
     *
     * @param purchaseOrderDTO the entity to save.
     * @return the persisted entity.
     */
    PurchaseOrderDTO save(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * Updates a purchaseOrder.
     *
     * @param purchaseOrderDTO the entity to update.
     * @return the persisted entity.
     */
    PurchaseOrderDTO update(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * Partially updates a purchaseOrder.
     *
     * @param purchaseOrderDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PurchaseOrderDTO> partialUpdate(PurchaseOrderDTO purchaseOrderDTO);

    /**
     * Get all the purchaseOrders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PurchaseOrderDTO> findAll(Pageable pageable);

    /**
     * Get the "id" purchaseOrder.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PurchaseOrderDTO> findOne(Long id);

    /**
     * Delete the "id" purchaseOrder.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
