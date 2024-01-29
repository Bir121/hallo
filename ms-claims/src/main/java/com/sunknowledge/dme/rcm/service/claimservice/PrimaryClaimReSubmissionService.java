package com.sunknowledge.dme.rcm.service.claimservice;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.pojo.Resubmission.ClaimSubmissionInput;
import com.sunknowledge.dme.rcm.pojo.common.ResultClaimSubmissionOutcome;

public interface PrimaryClaimReSubmissionService {

	ServiceOutcome<ResultClaimSubmissionOutcome> accessProfessionalClaimsSubmission(String soId, String claimControlNo);
	ServiceOutcome<ResultClaimSubmissionOutcome> professionalClaimSubmission(String token, ClaimSubmissionInput claimSubmissionInput);
	ServiceOutcome<ResultClaimSubmissionOutcome> professionalClaimHealthCheck(String token);
}
