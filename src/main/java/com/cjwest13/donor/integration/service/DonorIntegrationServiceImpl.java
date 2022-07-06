package com.cjwest13.donor.integration.service;

import com.cjwest13.donor.integration.actblue.ActBlueAPI;
import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueDonation;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static com.cjwest13.donor.integration.DonorIntegrationServiceConstants.ACTBLUE_COMPLETE_STATUS;
import static java.lang.Thread.sleep;

@Component
public class DonorIntegrationServiceImpl implements DonorIntegrationService {

    @Autowired
    private ActBlueAPI actBlueAPI;

    @Value("${actblue.wait.period}")
    private long actBlueWaitPeriodMillis;

    @Override
    public Boolean actBlueToBloom(Date dateStart, Date dateEnd, String cvsType) throws Exception {

        ActBlueCreateCVSResponse createCVSResponse = actBlueAPI.createActBlueCSV(dateStart, dateEnd, cvsType);
        ActBlueGetCVSResponse getCVSResponse = getActBlueCvs(createCVSResponse);
        List<ActBlueDonation> donationsList = actBlueAPI.getActBlueDonations(getCVSResponse.getDownload_url());
        return true;
    }

    @Override
    public ActBlueGetCVSResponse getActBlueCvs(ActBlueCreateCVSResponse createCVSResponse) throws Exception {

        ActBlueGetCVSResponse getCVSResponse = actBlueAPI.getActBlueCSV(createCVSResponse);

        while (!getCVSResponse.getStatus().equals(ACTBLUE_COMPLETE_STATUS)) {

            sleep(actBlueWaitPeriodMillis);
            getCVSResponse = actBlueAPI.getActBlueCSV(createCVSResponse);

        }

        return getCVSResponse;

    }

}
