package com.sunknowledge.dme.rcm.service.impl.others;

import com.sunknowledge.dme.rcm.domain.StopReasonMaster;
import com.sunknowledge.dme.rcm.domain.TaxZone;
import com.sunknowledge.dme.rcm.repository.others.TaxZoneRepositoryExtended;
import com.sunknowledge.dme.rcm.service.dto.TaxZoneDTO;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.TaxZoneExtendedResponseDto;
import com.sunknowledge.dme.rcm.service.dto.others.TaxZoneParameterDTO;
import com.sunknowledge.dme.rcm.service.mapper.TaxZoneMapper;
import com.sunknowledge.dme.rcm.service.others.TaxZoneServiceExtended;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Primary
@Service
public class TaxZoneServiceExtendedImpl implements TaxZoneServiceExtended {
    private final Logger log = LoggerFactory.getLogger(TaxZoneServiceExtendedImpl.class);

    @Autowired
    TaxZoneRepositoryExtended taxZoneRepositoryExtended;
    @Autowired
    TaxZoneMapper taxZoneMapper;

    @Override
    public TaxZoneDTO save(TaxZoneDTO taxZoneDTO) {
        return null;
    }

    @Override
    public TaxZoneDTO update(TaxZoneDTO taxZoneDTO) {
        return null;
    }

    @Override
    public Optional<TaxZoneDTO> partialUpdate(TaxZoneDTO taxZoneDTO) {
        return Optional.empty();
    }

    @Override
    public Page<TaxZoneDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<TaxZoneDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ResponseDTO getTaxZoneWiseRateByParam(String param, String opType) {
        List<TaxZoneDTO> dataList = null;
        TaxZoneDTO data = null;
        switch (opType) {
            case "StateName":
                dataList = param.trim() != "" ? taxZoneMapper.toDto(taxZoneRepositoryExtended.
                    findByStateNameLikeIgnoreCaseAndStatusIgnoreCase("%"+param.trim()+"%", "active")) : new ArrayList<>();
                return (new ResponseDTO(dataList.size() > 0 ? true : false, dataList.size() > 0 ? "" : "Data Not Found.", dataList,200));
            case "StateCodeId":
                data = taxZoneMapper.toDto(taxZoneRepositoryExtended.
                    findByStateCodeIdAndStatusIgnoreCase(Long.valueOf(param), "active"));
                return (new ResponseDTO(data!=null ? true : false, data!=null ? "" : "Data Not Found.", data,200));
            case "StateCode":
                data = taxZoneMapper.toDto(taxZoneRepositoryExtended.
                    findByStateCodeIgnoreCaseAndStatusIgnoreCase(param, "active"));
                return (new ResponseDTO(data!=null ? true : false, data!=null ? "" : "Data Not Found.", data,200));
            default:
                return (new ResponseDTO(false, "Operation Type not valid.", null,200));
        }
    }

    @Override
    public ResponseDTO saveTaxZone(TaxZoneParameterDTO taxZoneParameterDTO) {
        /*TaxZone dataStateCodeId = taxZoneRepositoryExtended.findByStateCodeIdAndStatusIgnoreCase(
            taxZoneParameterDTO.getStateCodeId(), "active");*/
        TaxZone dataStateCode = taxZoneRepositoryExtended.findByStateCodeIgnoreCaseAndStatusIgnoreCase(
            taxZoneParameterDTO.getStateCode(), "active");
        List<TaxZone> dataListStateName = taxZoneRepositoryExtended.findByStateNameIgnoreCaseAndStatusIgnoreCase(
            taxZoneParameterDTO.getStateName(), "active");
        Set uniqueStateCodeSet = taxZoneRepositoryExtended.findAll().stream().map(x -> x.getStateCode()).collect(Collectors.toSet());
        Set uniqueTaxZoneIdSet = taxZoneRepositoryExtended.findAll().stream().map(x -> x.getTaxZoneId()).collect(Collectors.toSet());
        if (taxZoneParameterDTO.getStateName() == null) {
            return new ResponseDTO(false, "Invalid Attribute (State_Name)", null,200);
            //throw new InvalidAttributeValueException("Invalid Attribute (State_Name)");
        } else if (taxZoneParameterDTO.getStateName().trim() == "") {
            return new ResponseDTO(false, "State_Name must be provided", null,200);
            //throw new InputMismatchException("State_Name must be provided");
        } else if (taxZoneParameterDTO.getStateCode().trim() == "") {
            return new ResponseDTO(false, "State_Code must be provided", null,200);
            //throw new InputMismatchException("State_Code must be provided");
        } /*else if (taxZoneParameterDTO.getStateCodeId() == null) {
            throw new InputMismatchException("State_Code_Id must be provided");
        } else if ((taxZoneParameterDTO.getTaxZoneId() == null || taxZoneParameterDTO.getTaxZoneId() == 0) && dataStateCodeId!=null) {
            throw new InputMismatchException("(" + taxZoneParameterDTO.getStateCodeId() + ") " + "State_Code_Id already exist");*/
        //}
        else if ((taxZoneParameterDTO.getTaxZoneId() == null || taxZoneParameterDTO.getTaxZoneId() == 0) && dataStateCode!=null) {
            return new ResponseDTO(false, "(" + taxZoneParameterDTO.getStateCode() + ") " + "State_Code already exist", null,200);
           // throw new InputMismatchException("(" + taxZoneParameterDTO.getStateCode() + ") " + "State_Code already exist");
        } else if ((taxZoneParameterDTO.getTaxZoneId() == null || taxZoneParameterDTO.getTaxZoneId() == 0) && dataListStateName.size() > 0) {
            return new ResponseDTO(false, "(" + taxZoneParameterDTO.getStateName() + ") " + "State_Name already exist", null,200);
            //throw new InputMismatchException("(" + taxZoneParameterDTO.getStateName() + ") " + "State_Name already exist");
        }else if(!uniqueTaxZoneIdSet.contains(taxZoneParameterDTO.getTaxZoneId()) &&
            uniqueStateCodeSet.contains(taxZoneParameterDTO.getStateCode())){
            return new ResponseDTO(false, "("+ taxZoneParameterDTO.getStateCode() +") "+"State_Code already exist", null,200);
            //throw new InputMismatchException("("+ taxZoneParameterDTO.getStateCode() +") "+"State_Code already exist");
        }

        TaxZoneDTO taxZoneDTO = (taxZoneParameterDTO.getTaxZoneId() == null ||
            taxZoneParameterDTO.getTaxZoneId() == 0) ? new TaxZoneDTO() :
            (taxZoneRepositoryExtended.findById(taxZoneParameterDTO.getTaxZoneId()).isPresent() ?
                taxZoneMapper.toDto(taxZoneRepositoryExtended.findById(taxZoneParameterDTO.getTaxZoneId()).get()) :
                new TaxZoneDTO());

        BeanUtils.copyProperties(taxZoneParameterDTO, taxZoneDTO);
        taxZoneDTO.setStatus("active");
        taxZoneDTO.setStateCode(taxZoneDTO.getStateCode().toUpperCase());
        if (taxZoneDTO.getTaxZoneId() == null || taxZoneDTO.getTaxZoneId() == 0) {
            taxZoneDTO.setTaxZoneId(null);
            taxZoneDTO.setCreatedDate(LocalDate.now());
            taxZoneDTO.setCreatedById(1L);
            taxZoneDTO.setCreatedByName("Abhijit");
            taxZoneDTO.setTaxZoneUuid(UUID.randomUUID());
        } else {
            taxZoneDTO.setUpdatedDate(LocalDate.now());
            taxZoneDTO.setUpdatedById(1L);
            taxZoneDTO.setUpdatedByName("Abhijit");
        }
        TaxZoneDTO savedTaxZoneDTO = taxZoneMapper.toDto(
            taxZoneRepositoryExtended.save(taxZoneMapper.toEntity(taxZoneDTO))
        );
        return new ResponseDTO(true, "Successfully Saved.", savedTaxZoneDTO,200);
    }

    @Override
    public List<TaxZoneDTO> getAllTaxZoneInfo() {
        List<TaxZone> data = taxZoneRepositoryExtended.findByStatusIgnoreCaseOrderByStateNameAsc("active");
        //data.sort(Comparator.comparing(TaxZone::getStateName));
        return taxZoneMapper.toDto(data);
    }

    @Override
    public TaxZoneDTO getTaxZoneInfoByID(Long taxZoneId) {
        Optional<TaxZone> data = taxZoneRepositoryExtended.findBytaxZoneIdAndStatusIgnoreCase(taxZoneId,"active");
        if (data.isPresent()) {
            return taxZoneMapper.toDto(data.get());
        } else return new TaxZoneDTO();
    }

    @Override
    public ResponseDTO getTaxZoneForDropdown() {
        List<TaxZoneDTO> taxZoneDtoList = getAllTaxZoneInfo();
        List<TaxZoneExtendedResponseDto> responseDtos = taxZoneDtoList.stream().map(row -> new TaxZoneExtendedResponseDto(row.getTaxZoneId(), row.getStateName(), row.getStateCode(), row.getTaxRate()))
            .collect(Collectors.toList());
        return (new ResponseDTO(responseDtos.size() > 0 ? true : false, responseDtos.size() > 0 ? "" : "Data Not Found.", responseDtos,200));
    }

    @Override
    public ResponseDTO setTaxZoneStatusByUuid(UUID uuid, String status) {
        if(status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
            try{
                Optional<TaxZone> obj = taxZoneRepositoryExtended.findByTaxZoneUuid(uuid);
                if(obj.isPresent()){
                    obj.get().setStatus(status);
                    obj.get().setUpdatedById(1l);
                    obj.get().setUpdatedByName("Updated Test");
                    obj.get().setUpdatedDate(LocalDate.now());
                    taxZoneRepositoryExtended.save(obj.get());
                    return (new ResponseDTO(Boolean.TRUE, "Successfully Saved", obj.get(),200));
                }else{
                    return (new ResponseDTO(Boolean.FALSE, "Data Not Found",null,200));
                }
            }catch (Exception e){
                log.error("=====>> Error : "+e);
                return (new ResponseDTO(Boolean.FALSE, "Failed to Save :: Data Error",null,200));
            }
        }else{
            return (new ResponseDTO(Boolean.FALSE, "Status must be active or inactive ", null,200));
        }
    }
}
