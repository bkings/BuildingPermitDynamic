package com.controller.rest.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.utility.AnusuchiKaMaster;

@RestController
@CrossOrigin
@RequestMapping("api/Utility/AnusuchiKaMaster")
public class AnusuchiKaMasterRestController {

    /*
List list=new ArrayList();
Map map = new HashMap();
org.codehaus.jackson.map.ObjectMapper mapper = new  org.codehaus.jackson.map.ObjectMapper();
     */
    model.Message message = new model.Message();
    String msg = "", sql;
    int row;

    @GetMapping
    public Object index() {
        List<AnusuchiKaMaster> list = new ArrayList<AnusuchiKaMaster>();
        list.add(new AnusuchiKaMaster("A", "(International State of Art Class \"A\")", "इन्टरनेशनल स्टेट अफ आर्ट"));
        list.add(new AnusuchiKaMaster("B", "(Professionaly Engineered Building)-Class \"B\"", "प्रोफेसनली इन्जिनियर्ड विल्डिङ"));
        list.add(new AnusuchiKaMaster("C", "(Mandatory Rules of Thumb)-Class \"C\"", "म्यान्डेटरी रुल्स अफ थम्ब"));
        list.add(new AnusuchiKaMaster("D", "(Guideline for Remote Rural Buildings - Low Strength Masonary/Earthen Buildings)-Class \"D\"", "ग्रामिण क्षेत्रको लागि भवन निर्देशिका"));
        return list;
    }

}
