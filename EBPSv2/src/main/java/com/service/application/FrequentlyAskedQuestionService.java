/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.application;


public interface FrequentlyAskedQuestionService {

    public Object getAll();

    public Object getById(Integer id);

    public Object save( String jsonData,String Authorization);
    
}
