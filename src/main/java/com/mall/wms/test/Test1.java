package com.mall.wms.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        String a = "是否可老师<666>速度快的";
        System.out.println(a.substring(a.indexOf("<")+1,a.indexOf(">")));





       /* String[] strVoid=new String[]{"11"};
        List<String> a = Arrays.asList(strVoid);
        int size = a.size();
        if(size < 3){
            if(size == 2){
                System.out.println(222);
            }else if(size == 1){
                System.out.println(111);
            }
        }else if(size > 3){
            a= a.subList(0,3);
        }
        System.out.println(a);*/

    }


    /**
     * 快速排序
     * @param strDate
     * @param left
     * @param right
     */
    public void quickSort(String[] strDate,int left,int right){
        String middle,tempDate;
        int i,j;
        i=left;
        j=right;
        middle=strDate[(i+j)/2];
        do{
            while(strDate[i].compareTo(middle)<0&& i<right)
                i++; //找出左边比中间值大的数
            while(strDate[j].compareTo(middle)>0&& j>left)
                j--; //找出右边比中间值小的数
            if(i<=j){ //将左边大的数和右边小的数进行替换
                tempDate=strDate[i];
                strDate[i]=strDate[j];
                strDate[j]=tempDate;
                i++;
                j--;
            }
        }while(i<=j); //当两者交错时停止

        if(i<right){
            quickSort(strDate,i,right);//从
        }
        if(j>left){
            quickSort(strDate,left,j);
        }
    }
}
