package com.dao.processing;

import java.util.List;

import com.model.processing.NoticePeriodFor15Days;
import com.model.processing.SanitaryDesign;
import com.model.processing.StructureDesignB;
import com.model.processing.StructureDesignC;

public interface DaoStructureDesign {

public List getAll(String hql);

public int save(StructureDesignB obj);

public int save(StructureDesignC obj);

public int save(SanitaryDesign obj);

public int save(NoticePeriodFor15Days obj);

public String getMsg();
}
