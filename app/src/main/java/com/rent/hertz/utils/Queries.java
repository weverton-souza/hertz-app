package com.rent.hertz.utils;


public class Queries {

    private String table;

    public Queries(String table) {
        this.table = table;
    }

    public String getQueryFindById(long id) {
        return "SELECT * FROM " + this.table + "WHERE id = " + id;
    }

    public String getQueryFindAll() {
        return "SELECT * FROM " + this.table;
    }

    public String getTable() {
        return this.table;
    }
}
