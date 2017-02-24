/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author yun
 */
public class DeptNameChanger {
    public static String NumtoString(int num){
        switch(num){
            case 1:
                return "전산학과";
            case 2:
                return "전자공학과";
            case 3:
                return "화학공학과";
            case 4:
                return "기계공학과";
            case 5:
                return "항공우주공학과";
            default:
                return "알수없는 학과";
        }
    }
    public static int StringtoNum(String deptName){
        if(deptName.equals("전산학과"))
            return 1;
        else if(deptName.equals("전자공학과"))
            return 2;
        else if(deptName.equals("화학공학과"))
            return 3;
        else if(deptName.equals("기계공학과"))
            return 4;
        else if(deptName.equals("항공우주공학과"))
            return 5;
        else 
            return -1;
       
    }
}
