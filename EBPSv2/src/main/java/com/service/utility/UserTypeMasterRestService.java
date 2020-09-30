/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.utility;

import com.model.utility.UserTypeMaster;

/**
 *
 * @author dell
 */
public interface UserTypeMasterRestService {

    public Object index();

    public Object doUpdate(String Authorization, UserTypeMaster obj);

}
