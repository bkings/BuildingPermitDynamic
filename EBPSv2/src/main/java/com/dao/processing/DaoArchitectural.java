package com.dao.processing;

import java.util.List;

import com.model.processing.ArchitecturalDesignB;
import com.model.processing.ArchitecturalDesignBDetails;
import com.model.processing.ArchitecturalDesignC;
import com.model.processing.ArchitecturalDesignCDetails;

public interface DaoArchitectural {

public int save(ArchitecturalDesignB obj);

public int save(ArchitecturalDesignBDetails obj);

public int save(ArchitecturalDesignC obj);

public int save(ArchitecturalDesignCDetails obj);

public List getAll(String hql);

public String getMsg();
}
