package com.cjwest13.donor.integration.service;

import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;

import java.util.Date;

public interface DonorIntegrationService {

    public Boolean actBlueToBloom(Date dateStart, Date dateEnd, String cvsType) throws Exception;

    public ActBlueGetCVSResponse getActBlueCvs(ActBlueCreateCVSResponse createCVSResponse) throws Exception;


}
