package com.cjwest13.donor.integration.actblue;

import com.cjwest13.donor.integration.model.ActBlueCreateCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueGetCVSResponse;
import com.cjwest13.donor.integration.model.ActBlueDonation;

import java.util.Date;
import java.util.List;

public interface ActBlueAPI {

    public ActBlueCreateCVSResponse createActBlueCSV(Date dateStart, Date dateEnd, String cvsType) throws Exception;

    public ActBlueGetCVSResponse getActBlueCSV(ActBlueCreateCVSResponse createCVSResponse) throws Exception;

    public List<ActBlueDonation> getActBlueDonations(String downloadUrl) throws Exception;
}
