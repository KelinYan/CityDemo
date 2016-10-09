package com.djlink.kelin.citys.entity;

/**
 * Created by kelin on 16/10/7.
 */

public class City {



    /**
     * id : 58
     * name : 三明
     * parentid : 3
     * parentname : 福建
     * areacode : 0598
     * zipcode : 365000
     * depth : 2
     */


    private String id;
    private String name;
    private String parentid;
    private String parentname;
    private String areacode;
    private String zipcode;
    private String depth;

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

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentname() {
        return parentname;
    }

    public void setParentname(String parentname) {
        this.parentname = parentname;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }
}
