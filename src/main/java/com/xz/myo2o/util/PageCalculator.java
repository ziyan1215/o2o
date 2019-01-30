package com.xz.myo2o.util;

public class PageCalculator {
    //计算分页数
    public static int calculatorPageCount(int totalCount,int pageSize){
       //计算所得的页数
        int idealPage =totalCount / pageSize;
        //真实的页数
        int totalPage = (totalCount % pageSize == 0)? idealPage:(idealPage+1);
        return totalPage;
    }
    //计算rowIndex
    public static int calculateRowIndex(int pageIndex,int pageSize){
        return (pageIndex > 0)?(pageIndex-1)*pageSize:0;
    }

}
