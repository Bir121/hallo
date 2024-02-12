package com.sunknowledge.dme.rcm.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * A PurchaseOrderItemsReceivedAuditLog.
 */
@Entity
@Table(name = "t_purchase_order_items_received_audit_log")
public class PurchaseOrderItemsReceivedAuditLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "po_itm_recived_id")
    private Long poItmRecivedId;

    @Column(name = "old_row_data")
    private String oldRowData;

    @Column(name = "new_row_data")
    private String newRowData;

    @Column(name = "dml_type")
    private String dmlType;

    @Column(name = "dml_timestamp")
    private LocalDate dmlTimestamp;

    @Column(name = "dml_created_by")
    private String dmlCreatedBy;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getPoItmRecivedId() {
        return this.poItmRecivedId;
    }

    public PurchaseOrderItemsReceivedAuditLog poItmRecivedId(Long poItmRecivedId) {
        this.setPoItmRecivedId(poItmRecivedId);
        return this;
    }

    public void setPoItmRecivedId(Long poItmRecivedId) {
        this.poItmRecivedId = poItmRecivedId;
    }

    public String getOldRowData() {
        return this.oldRowData;
    }

    public PurchaseOrderItemsReceivedAuditLog oldRowData(String oldRowData) {
        this.setOldRowData(oldRowData);
        return this;
    }

    public void setOldRowData(String oldRowData) {
        this.oldRowData = oldRowData;
    }

    public String getNewRowData() {
        return this.newRowData;
    }

    public PurchaseOrderItemsReceivedAuditLog newRowData(String newRowData) {
        this.setNewRowData(newRowData);
        return this;
    }

    public void setNewRowData(String newRowData) {
        this.newRowData = newRowData;
    }

    public String getDmlType() {
        return this.dmlType;
    }

    public PurchaseOrderItemsReceivedAuditLog dmlType(String dmlType) {
        this.setDmlType(dmlType);
        return this;
    }

    public void setDmlType(String dmlType) {
        this.dmlType = dmlType;
    }

    public LocalDate getDmlTimestamp() {
        return this.dmlTimestamp;
    }

    public PurchaseOrderItemsReceivedAuditLog dmlTimestamp(LocalDate dmlTimestamp) {
        this.setDmlTimestamp(dmlTimestamp);
        return this;
    }

    public void setDmlTimestamp(LocalDate dmlTimestamp) {
        this.dmlTimestamp = dmlTimestamp;
    }

    public String getDmlCreatedBy() {
        return this.dmlCreatedBy;
    }

    public PurchaseOrderItemsReceivedAuditLog dmlCreatedBy(String dmlCreatedBy) {
        this.setDmlCreatedBy(dmlCreatedBy);
        return this;
    }

    public void setDmlCreatedBy(String dmlCreatedBy) {
        this.dmlCreatedBy = dmlCreatedBy;
    }

    public Long getId() {
        return this.id;
    }

    public PurchaseOrderItemsReceivedAuditLog id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PurchaseOrderItemsReceivedAuditLog)) {
            return false;
        }
        return id != null && id.equals(((PurchaseOrderItemsReceivedAuditLog) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PurchaseOrderItemsReceivedAuditLog{" +
            "id=" + getId() +
            ", poItmRecivedId=" + getPoItmRecivedId() +
            ", oldRowData='" + getOldRowData() + "'" +
            ", newRowData='" + getNewRowData() + "'" +
            ", dmlType='" + getDmlType() + "'" +
            ", dmlTimestamp='" + getDmlTimestamp() + "'" +
            ", dmlCreatedBy='" + getDmlCreatedBy() + "'" +
            "}";
    }
}
