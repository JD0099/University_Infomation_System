/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.PersonalInfoAgent;

/**
 *로그인과 관련된 Control 클래스
 * @author Sungrae
 */
public class LoginHandler {

    PersonalInfoAgent uAgent = new PersonalInfoAgent();

    public LoginHandler() {
    }

   

    public boolean setStuInfo(String stu, String pwd) {
        return uAgent.searchStudentInfo(stu, pwd);
    }

    public boolean setProfInfo(String prof, String pwd) {
        return uAgent.searchProfInfo(prof, pwd);
    }

    public boolean setSubInfo(String sub, String pwd) {
        return uAgent.searchSubInfo(sub, pwd);
    }

    public boolean setHakInfo(String hak, String pwd) {
        return uAgent.searchHakInfo(hak, pwd);
    }

    public String getName() {
        return uAgent.getNameInfo();
    }

}
