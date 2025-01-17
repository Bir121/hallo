package com.sunknowledge.dme.rcm.service.others;

import com.sunknowledge.dme.rcm.service.FunctionalityEndpointMappingService;
import com.sunknowledge.dme.rcm.service.dto.common.ResponseDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityEndpointMapParameterDTO;
import com.sunknowledge.dme.rcm.service.dto.others.FunctionalityEndpointMapUpdateParameterDTO;

import java.util.UUID;

public interface FunctionalityEndpointMappingServiceExtended extends FunctionalityEndpointMappingService {
    ResponseDTO saveFunctionalityEndpointMap(FunctionalityEndpointMapParameterDTO functionalityEndpointMapParameterDTO);

    ResponseDTO updateFunctionalityEndpointMap(FunctionalityEndpointMapUpdateParameterDTO functionalityEndpointMapUpdateParameterDTO);

    ResponseDTO getFunctionalityEndpointDetailsByNameOrNoOrUUID(String data, String operationType);

    ResponseDTO setFunctionalityEndpointStatusByUuid(UUID uuid, String status);

    ResponseDTO getAllFunctionalityEndpointMappingData();
}
