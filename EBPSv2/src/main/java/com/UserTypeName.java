/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author MS
 */
public class UserTypeName {

    public String userTypeName(String type) {
        if (type.equalsIgnoreCase("A")) {
            return "Engineer";
        } else if (type.equalsIgnoreCase("B")) {
            return "Sub Engineer";
        } else if (type.equalsIgnoreCase("C")) {
            return "Chief";
        } else if (type.equalsIgnoreCase("D")) {
            return "Designer";
        } else if (type.equalsIgnoreCase("AD")) {
            return "Amin";
        } else if (type.equalsIgnoreCase("R")) {
            return "Rajasow";
        } else if (type.equalsIgnoreCase("E")) {
        	return "Asst. Sub Engineer";
        } else if (type.equalsIgnoreCase("F")) {
        	return "Administration";
        } else if (type.equalsIgnoreCase("G")) {
        	return "Ward Engineer";
        }
        return "Invalid";
    }
}
