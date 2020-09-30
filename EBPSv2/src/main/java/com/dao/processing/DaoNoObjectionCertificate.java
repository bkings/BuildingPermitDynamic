package com.dao.processing;

import java.util.List;

import com.model.processing.NoObjectionCertificate;
import com.model.processing.NoObjectionCertificateDetails;

/**
 * 
 * @author bkings_bjr
 *
 */
public interface DaoNoObjectionCertificate {

	public int save(NoObjectionCertificate obj);

    public int save(NoObjectionCertificateDetails obj);

    public List<NoObjectionCertificate> getAll(String hql);

    public String getMsg();
	
}
