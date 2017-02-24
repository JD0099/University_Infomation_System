/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.regex.Pattern;

/**
 *
 * @author yun
 */
public class UserInfoChecker implements ErrorMessage {

    private UserInfo uInfo;
    private String Error_Message = "";
    
    public UserInfoChecker(UserInfo uInfo) {
        this.uInfo = uInfo;
    }
    
    public boolean isAble(){
        if(uInfo.getId() == null || uInfo.getId().equals("") || !IdCheck(uInfo.getId())){
            this.Error_Message += "id를 확인하세요.";
            return false;
        }
        else if(uInfo.getName()== null || uInfo.getName().equals("")){
            this.Error_Message = "이름을 확인하세요.";
            return false;
        }
        else if(uInfo.getIntDept()==-1){
            this.Error_Message = "학과를 확인하세요.";
            return false;
        }
        else if(uInfo.getResnum()== null || uInfo.getResnum().equals("") || !ResNumCheck(uInfo.getResnum())){
            this.Error_Message = "주민등록번호를 확인하세요.";
            return false;
        }
        return true;
    }
    
    private boolean ResNumCheck(String resNum){
        String regEx = "\\d{6}\\-[1-4]\\d{6}";
        return Pattern.matches(regEx, resNum);
    }
    private boolean IdCheck(String id){
        char identify = id.charAt(0);
        
        if(id.length()!=4){
            this.Error_Message = "id는 4자리만 가능합니다.\n";
            return false;
        }
        
        if(!isNumber(id.substring(1, 4))){
            this.Error_Message = "뒷 3자리는 숫자로 입력하세요\n";
            return false;
        }
            
        if(uInfo.getPosition() == "student" && identify !='S'){
            this.Error_Message = "학생의 id는 S로 시작해야 합니다.\n";
            return false;
        }
        else if(uInfo.getPosition() == "professor" && identify !='P'){
            this.Error_Message = "교수의 id는 P로 시작해야 합니다.\n";
            return false;
        }
        
        return true;
        
    }
    
    //ID의 일부가 숫자로만 이루어지는지 검사
    private boolean isNumber(String str) { 
   
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);     
            if(ch<'0' || ch>'9') {
                return false;
            }
        }
        return true;
    }
     
    @Override
    public String getError_Message() {
        return this.Error_Message;
    }
    
}
