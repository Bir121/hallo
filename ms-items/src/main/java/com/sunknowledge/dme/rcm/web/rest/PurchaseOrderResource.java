package com.sunknowledge.dme.rcm.web.rest;

import com.sunknowledge.dme.rcm.repository.PurchaseOrderRepository;
import com.sunknowledge.dme.rcm.service.PurchaseOrderService;
import com.sunknowledge.dme.rcm.service.dto.PurchaseOrderDTO;
import com.sunknowledge.dme.rcm.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.sunknowledge.dme.rcm.domain.PurchaseOrder}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);

    private static final String ENTITY_NAME = "itemsPurchaseOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderService purchaseOrderService;

    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderResource(PurchaseOrderService purchaseOrderService, PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderService = purchaseOrderService;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    /**
     * {@code POST  /purchase-orders} : Create a new purchaseOrder.
     *
     * @param purchaseOrderDTO the purchaseOrderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrderDTO, or with status {@code 400 (Bad Request)} if the purchaseOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-orders")
    public ResponseEntity<PurchaseOrderDTO> createPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrder : {}", purchaseOrderDTO);
        if (purchaseOrderDTO.getPoId() != null) {
            throw new BadRequestAlertException("A new purchaseOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrderDTO result = purchaseOrderService.save(purchaseOrderDTO);
        return ResponseEntity
            .created(new URI("/api/purchase-orders/" + result.getPoId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPoId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-orders/:poId} : Updates an existing purchaseOrder.
     *
     * @param poId the id of the purchaseOrderDTO to save.
     * @param purchaseOrderDTO the purchaseOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-orders/{poId}")
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(
        @PathVariable(value = "poId", required = false) final Long poId,
        @RequestBody PurchaseOrderDTO purchaseOrderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder : {}, {}", poId, purchaseOrderDTO);
        if (purchaseOrderDTO.getPoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(poId, purchaseOrderDTO.getPoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderRepository.existsById(poId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PurchaseOrderDTO result = purchaseOrderService.update(purchaseOrderDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderDTO.getPoId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /purchase-orders/:poId} : Partial updates given fields of an existing purchaseOrder, field will ignore if it is null
     *
     * @param poId the id of the purchaseOrderDTO to save.
     * @param purchaseOrderDTO the purchaseOrderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrderDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseOrderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseOrderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/purchase-orders/{poId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseOrderDTO> partialUpdatePurchaseOrder(
        @PathVariable(value = "poId", required = false) final Long poId,
        @RequestBody PurchaseOrderDTO purchaseOrderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseOrder partially : {}, {}", poId, purchaseOrderDTO);
        if (purchaseOrderDTO.getPoId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(poId, purchaseOrderDTO.getPoId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseOrderRepository.existsById(poId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseOrderDTO> result = purchaseOrderService.partialUpdate(purchaseOrderDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrderDTO.getPoId().toString())
        );
    }

    /**
     * {@code GET  /purchase-orders} : get all the purchaseOrders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrders in body.
     */
    @GetMapping("/purchase-orders")
    public ResponseEntity<List<PurchaseOrderDTO>> getAllPurchaseOrders(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of PurchaseOrders");
        Page<PurchaseOrderDTO> page = purchaseOrderService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /purchase-orders/:id} : get the "id" purchaseOrder.
     *
     * @param id the id of the purchaseOrderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-orders/{id}")
    public ResponseEntity<PurchaseOrderDTO> getPurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrder : {}", id);
        Optional<PurchaseOrderDTO> purchaseOrderDTO = purchaseOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrderDTO);
    }

    /**
     * {@code DELETE  /purchase-orders/:id} : delete the "id" purchaseOrder.
     *
     * @param id the id of the purchaseOrderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-orders/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrder : {}", id);
        purchaseOrderService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
