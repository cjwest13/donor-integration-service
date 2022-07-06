package com.cjwest13.donor.integration.api;

import com.cjwest13.donor.integration.service.DonorIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("donor")
@Validated
public class DonorController {

    @Autowired
    private DonorIntegrationService donorIntegrationService;

    @GetMapping("/actBlueToBloom")
    public ResponseEntity<?> actBlueToBloomerang(@RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date dateStart,
                                                 @RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date dateEnd,
                                                 @RequestParam @Valid String cvsType) throws Exception {

        Boolean check = donorIntegrationService.actBlueToBloom(dateStart, dateEnd, cvsType);
        if (check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
