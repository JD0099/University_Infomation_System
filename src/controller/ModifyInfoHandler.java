/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ModifyInfoAgent;

/**
 *비밀번호 수정과 관련된 Control 클래스
 * @author Sungrae
 */
public class ModifyInfoHandler {

    ModifyInfoAgent mAgent = new ModifyInfoAgent();

    public ModifyInfoHandler() {
    }

    public boolean setStuModifyInfoHandler(String id, String curPwd, String newPwd, String newPwd1) {
        return mAgent.stuModifyInfo(id, curPwd, newPwd, newPwd1);
    }

    public boolean setProfModifyInfoHandler(String id, String curPwd, String newPwd, String newPwd1) {
        return mAgent.profModifyInfo(id, curPwd, newPwd, newPwd1);
    }

    public boolean setHakModifyInfoHandler(String id, String curPwd, String newPwd, String newPwd1) {
        return mAgent.hakModifyInfo(id, curPwd, newPwd, newPwd1);
    }

    public boolean setSubModifyInfoHandler(String id, String curPwd, String newPwd, String newPwd1) {
        return mAgent.subModifyInfo(id, curPwd, newPwd, newPwd1);
    }
}
