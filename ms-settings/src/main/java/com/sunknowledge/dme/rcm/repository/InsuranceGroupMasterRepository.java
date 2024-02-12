package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.InsuranceGroupMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the InsuranceGroupMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceGroupMasterRepository extends JpaRepository<InsuranceGroupMaster, Long> {}
