package com.test.webapp.resource;

import com.test.webapp.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/userdetails")
    public ResponseEntity<ResponseDTO> indexDetails() {
        return new ResponseEntity<>(new ResponseDTO("vishnu", "TVM"), HttpStatus.OK);
    }

}
