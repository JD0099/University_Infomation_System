/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.DeptInfoGetterAgent;

/**
 *
 * @author yun
 */
public class DeptInfoGetterHandler {
    DeptInfoGetterAgent digAgent;
    
    public DeptInfoGetterHandler(){
        digAgent = new DeptInfoGetterAgent();
    }
    public ArrayList<String> getDeptList(){
        return digAgent.getDeptList();
    }
}
