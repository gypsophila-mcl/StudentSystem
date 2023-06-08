package com.mcl.entity;

import java.util.Arrays;
import java.util.List;

/**
 * @author: 马仓龙
 * @version: 1.0
 * @description: 封装分页数据
 */
public class PageInfo<E> {
    private int currentPage;//代表当前页
    private int pageSize;//每页显示个数
    private int total;//总记录数
    private List<E> list;//分页信息
    private int[] navigatePageNum;//分页数字
    private int maxNum;//最大页数
    private int manageType;//管理班级还是学生

    public PageInfo() {
    }

    public PageInfo(int currentPage, int pageSize, int total, List<E> list, int[] navigatePageNum, int maxNum) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
        this.navigatePageNum = navigatePageNum;
        this.maxNum = maxNum;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int[] getNavigatePageNum() {
        return navigatePageNum;
    }

    public void setNavigatePageNum(int[] navigatePageNum) {
        this.navigatePageNum = navigatePageNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getManageType() {
        return manageType;
    }

    public void setManageType(int manageType) {
        this.manageType = manageType;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", total=" + total +
                ", list=" + list +
                ", navigatePageNum=" + Arrays.toString(navigatePageNum) +
                '}';
    }
}
