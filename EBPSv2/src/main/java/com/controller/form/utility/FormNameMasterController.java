
package com.controller.form.utility;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;@Controller
@ComponentScan
public class FormNameMasterController {

    @RequestMapping(value = "Utility/FormNameMaster", method = RequestMethod.GET)
    public String doGet() {
        return "Utility/FormNameMaster";
    } 

}