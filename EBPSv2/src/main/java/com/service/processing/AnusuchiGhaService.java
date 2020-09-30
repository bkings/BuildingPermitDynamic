/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.AnusuchiGha;

public interface AnusuchiGhaService {

public Object getAll(String Authorization, String applicationNo);

public Object save(AnusuchiGha obj, String Authorization);

public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);

}
