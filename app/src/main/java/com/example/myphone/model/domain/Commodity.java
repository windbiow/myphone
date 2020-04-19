package com.example.myphone.model.domain;

import java.util.List;

public class Commodity {

    /**
     * success : true
     * code : 10000
     * message : 调用成功
     * data : [{"id":2,"commodityName":"雪碧","price":2.23,"categoryName":"饮料","commodityStatus":0,"picture":"/static/img/雪碧.png","comment":null,"count":16,"machineId":1},{"id":5,"commodityName":"乐事","price":6.32,"categoryName":"薯片","commodityStatus":0,"picture":"/static/img/乐事.png","comment":null,"count":20,"machineId":1},{"id":6,"commodityName":"闲趣","price":4.32,"categoryName":"饼干","commodityStatus":0,"picture":"/static/img/闲趣.png","comment":null,"count":9,"machineId":1},{"id":8,"commodityName":"可乐","price":8.2,"categoryName":"饮料","commodityStatus":0,"picture":"/static/img/可乐.png","comment":null,"count":9,"machineId":1},{"id":9,"commodityName":"美年达","price":1.24,"categoryName":"饮料","commodityStatus":0,"picture":"/static/img/美年达.png","comment":null,"count":10,"machineId":1}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * commodityName : 雪碧
         * price : 2.23
         * categoryName : 饮料
         * commodityStatus : 0
         * picture : /static/img/雪碧.png
         * comment : null
         * count : 16
         * machineId : 1
         */

        private int id;
        private String commodityName;
        private double price;
        private String categoryName;
        private int commodityStatus;
        private String picture;
        private Object comment;
        private int count;
        private int machineId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public int getCommodityStatus() {
            return commodityStatus;
        }

        public void setCommodityStatus(int commodityStatus) {
            this.commodityStatus = commodityStatus;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public Object getComment() {
            return comment;
        }

        public void setComment(Object comment) {
            this.comment = comment;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getMachineId() {
            return machineId;
        }

        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }
    }
}
