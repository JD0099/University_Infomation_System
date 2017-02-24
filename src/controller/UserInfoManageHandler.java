/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.UserInfo;
import model.UserInfoAgent;
import model.UserInfoChecker;

/**
 *
 * @author yun
 */
public class UserInfoManageHandler {
    
    private UserInfoAgent uiAgent;

    public UserInfoManageHandler() {
        uiAgent = new UserInfoAgent();
    }

    //userType을 받아 UserInfoAgent에게 전달하여 모든 리스트를 출력하도록 한다.
    public ArrayList<UserInfo> getUserList(String userType){
        return uiAgent.SelectUserAll(userType);
    }
    
    //UserInfo를 받아 UserInfoAgent에게 전달하여 정보 삽입을 하도록 한다.
    public String InsertUserInfo(UserInfo userInfo){
        System.out.println("insert Handler : "+userInfo.getPosition());
        
        UserInfoChecker uiChecker = new UserInfoChecker(userInfo);
        if(!uiChecker.isAble()){
            return uiChecker.getError_Message();
        };
        
        userInfo.CreatePassword();//패스워드 생성
        if(!uiAgent.UserInfoInsert(userInfo)){
            return uiAgent.getError_Message();
        }
        return "성공";
    }
    
    //UserInfoAgent에게 정보 삭제를 하도록 한다.
    public String DeleteUserInfo(ArrayList<String> deleteUsers, String userType){
        System.out.println("Delete Handler");
        
        if(deleteUsers.size()==0){
            return "삭제할 정보를 선택해 주세요.";
        }
        if(!uiAgent.UserInfoDelete(deleteUsers, userType)){
            return uiAgent.getError_Message();     
        }
        
        return "성공";
    }
    
    //UserInfoAgent에게 정보 수정을 하도록 한다.
    public String UpdateUserInfo(ArrayList<UserInfo> updateUsers, String userType){
        System.out.println("Update Handler");  
        
        for(int i=0;i<updateUsers.size();i++){
            UserInfoChecker uiChecker = new UserInfoChecker(updateUsers.get(i));
            if(!uiChecker.isAble()){
                return uiChecker.getError_Message();
            }
        }
        
        if(! uiAgent.UserInfoUpdate(updateUsers, userType)){
            return uiAgent.getError_Message();
        }
        
        return "성공";
    }
    
    //UserInfoAgent에게 정보 검색을 하도록 한다.
    public ArrayList<UserInfo> SelectUserInfo(UserInfo userInfo, String userType){
        System.out.println("Select Handler");
        return uiAgent.UserInfoSelect(userInfo, userType);
    }

    
}
