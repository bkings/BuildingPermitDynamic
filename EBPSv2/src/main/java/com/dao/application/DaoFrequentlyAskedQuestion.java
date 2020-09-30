/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.application;

import java.util.List;

import com.model.application.FrequentlyAskedQuestion;


public interface DaoFrequentlyAskedQuestion {

    List getAll(String hql);

    List getById(String hql);

    int save(FrequentlyAskedQuestion obj);
    
}
