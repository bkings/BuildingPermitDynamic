///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package com.service.processing;

import com.model.application.ApplicationApproved;
import com.model.processing.SuperStructureBuild;

public interface SuperStructureBuildService {

   public Object getAll(long applicationNo);

    public Object save(SuperStructureBuild obj, String Authorization);

    public Object doApprove(Long applicationNo, ApplicationApproved obj, String Authorization);
}
