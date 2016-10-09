package com.djlink.kelin.citys.entity;

import java.util.List;

/**
 * Created by kelin on 16/10/7.
 */

public class Response {


    /**
     * status : 0
     * msg : ok
     * result : []
     */

    private String status;
    private String msg;
    private List<City> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<City> getResult() {
        return result;
    }

    public void setResult(List<City> result) {
        this.result = result;
    }


}
