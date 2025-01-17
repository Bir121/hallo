package com.sunknowledge.dme.rcm.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.sunknowledge.dme.rcm.domain.SoLcdDocRefTransaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SoLcdDocRefTransactionDTO implements Serializable {

    @NotNull(message = "must not be null")
    private Long soLcdDocRefId;

    private Long soId;

    private Long checklistId;

    private String checklistName;

    private Long docRefId;

    private String docRefName;

    private String value;

    private String status;

    private LocalDate createdDate;

    private Long createdById;

    private String createdByName;

    private LocalDate updatedDate;

    private Long updatedById;

    private String updatedByName;

    private UUID soLcdDocRefTransactionUuid;

    private Long itemGroupId;

    private String itemGroupName;

    private Long coverageCriteriaId;

    public Long getSoLcdDocRefId() {
        return soLcdDocRefId;
    }

    public void setSoLcdDocRefId(Long soLcdDocRefId) {
        this.soLcdDocRefId = soLcdDocRefId;
    }

    public Long getSoId() {
        return soId;
    }

    public void setSoId(Long soId) {
        this.soId = soId;
    }

    public Long getChecklistId() {
        return checklistId;
    }

    public void setChecklistId(Long checklistId) {
        this.checklistId = checklistId;
    }

    public String getChecklistName() {
        return checklistName;
    }

    public void setChecklistName(String checklistName) {
        this.checklistName = checklistName;
    }

    public Long getDocRefId() {
        return docRefId;
    }

    public void setDocRefId(Long docRefId) {
        this.docRefId = docRefId;
    }

    public String getDocRefName() {
        return docRefName;
    }

    public void setDocRefName(String docRefName) {
        this.docRefName = docRefName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getUpdatedByName() {
        return updatedByName;
    }

    public void setUpdatedByName(String updatedByName) {
        this.updatedByName = updatedByName;
    }

    public UUID getSoLcdDocRefTransactionUuid() {
        return soLcdDocRefTransactionUuid;
    }

    public void setSoLcdDocRefTransactionUuid(UUID soLcdDocRefTransactionUuid) {
        this.soLcdDocRefTransactionUuid = soLcdDocRefTransactionUuid;
    }

    public Long getItemGroupId() {
        return itemGroupId;
    }

    public void setItemGroupId(Long itemGroupId) {
        this.itemGroupId = itemGroupId;
    }

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public Long getCoverageCriteriaId() {
        return coverageCriteriaId;
    }

    public void setCoverageCriteriaId(Long coverageCriteriaId) {
        this.coverageCriteriaId = coverageCriteriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SoLcdDocRefTransactionDTO)) {
            return false;
        }

        SoLcdDocRefTransactionDTO soLcdDocRefTransactionDTO = (SoLcdDocRefTransactionDTO) o;
        if (this.soLcdDocRefId == null) {
            return false;
        }
        return Objects.equals(this.soLcdDocRefId, soLcdDocRefTransactionDTO.soLcdDocRefId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.soLcdDocRefId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SoLcdDocRefTransactionDTO{" +
            "soLcdDocRefId=" + getSoLcdDocRefId() +
            ", soId=" + getSoId() +
            ", checklistId=" + getChecklistId() +
            ", checklistName='" + getChecklistName() + "'" +
            ", docRefId=" + getDocRefId() +
            ", docRefName='" + getDocRefName() + "'" +
            ", value='" + getValue() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdById=" + getCreatedById() +
            ", createdByName='" + getCreatedByName() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", updatedById=" + getUpdatedById() +
            ", updatedByName='" + getUpdatedByName() + "'" +
            ", soLcdDocRefTransactionUuid='" + getSoLcdDocRefTransactionUuid() + "'" +
            ", itemGroupId=" + getItemGroupId() +
            ", itemGroupName='" + getItemGroupName() + "'" +
            ", coverageCriteriaId=" + getCoverageCriteriaId() +
            "}";
    }
}
