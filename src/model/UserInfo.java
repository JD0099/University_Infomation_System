/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.StringTokenizer;

/**
 *
 * @author yun
 */
public class UserInfo {

    private String position, id, name, resnum, password;
    private int dept;
    
    public UserInfo(){   
    }
    
    public UserInfo(String position, String id, String name, int dept){
        this.position = position; 
        this.id = id; 
        this.name = name;
        this.dept = dept;
    }
    
    public UserInfo(String position, String id, String name, int dept, String resnum) {
        this(position, id, name, dept); //자신의 생성자 활용(DRY)
        this.resnum = resnum;             
    }
    public void CreatePassword(){
        StringTokenizer token =  new StringTokenizer(resnum, "-");//"-"기준으로 문자열 자름
        token.nextToken();
        this.password = token.nextToken();//"-"뒤편에 있는 문자열을 비밀번호로 지정    
    }
    ////////////////////////////////////////////////////////////////////////////
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getStringDept() {
        return DeptNameChanger.NumtoString(dept);
    }
    public int getIntDept() {
        return dept;
    }
    public void setDept(int dept) {
        this.dept = dept;
    }

    public String getResnum() {
        return resnum;
    }
    public void setResnum(String resnum) {
        this.resnum = resnum;
    }
    
    public String getPassword() {
        return password;
    }
    
}
