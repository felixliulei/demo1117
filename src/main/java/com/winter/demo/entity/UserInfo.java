package com.winter.demo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private Integer id;


    private Integer idNum;


    private String times;


    private String remark;

    private String name;


    private String delFlg;


    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getIdNum() {
        return idNum;
    }


    public void setIdNum(Integer idNum) {
        this.idNum = idNum;
    }


    public String getTimes() {
        return times;
    }


    public void setTimes(String times) {
        this.times = times;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDelFlg() {
        return delFlg;
    }


    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }
}