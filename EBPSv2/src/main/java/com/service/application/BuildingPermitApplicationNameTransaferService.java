package com.service.application;

public interface BuildingPermitApplicationNameTransaferService {

    public Object index(Long applicationNo, Long year, String constructionType, String nibedakName, String kittaNo, String wardNo, String Authorization);

    public Object save(long applicationNo, int time, String Authorization);

    public Object index(Long applocationNo, String Authorization);

    public Object history(Long applicationNo, Long year, String constructionType, String nibedakName, String kittaNo, String wardNo, String Authorization);

}
