package com.cjwest13.donor.integration.bloomerang;

import com.cjwest13.donor.integration.model.BloomerangSearchListResponse;

public interface BloomerangAPI {

    public BloomerangSearchListResponse constituentsSearch(String firstName, String lastName) throws Exception;

}
