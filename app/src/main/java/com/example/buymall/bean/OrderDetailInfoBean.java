package com.example.buymall.bean;

import java.util.List;

public class OrderDetailInfoBean {

    /**
     * total : 2
     * code : 0
     * orderList : [{"id":164,"order_no":null,"table_number":"-1","number_of_diners":null,"sett_amount":414,"transac_status":null,"order_receiving":null,"order_time":"2024-05-16 23:22:10","goods_list":null,"is_appointment":null,"appointment_time":null,"is_takeout_order":0,"takeout_address":null},{"id":163,"order_no":null,"table_number":"-1","number_of_diners":null,"sett_amount":304,"transac_status":null,"order_receiving":null,"order_time":"2024-05-16 21:55:28","goods_list":null,"is_appointment":null,"appointment_time":null,"is_takeout_order":0,"takeout_address":null}]
     */

    private int total;
    private int code;
    private List<OrderListBean> orderList;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * id : 164
         * order_no : null
         * table_number : -1
         * number_of_diners : null
         * sett_amount : 414
         * transac_status : null
         * order_receiving : null
         * order_time : 2024-05-16 23:22:10
         * goods_list : null
         * is_appointment : null
         * appointment_time : null
         * is_takeout_order : 0
         * takeout_address : null
         */

        private int id;
        private Object order_no;
        private String table_number;
        private Object number_of_diners;
        private double sett_amount;
        private String transac_status;
        private Object order_receiving;
        private String order_time;
        private Object goods_list;
        private String is_appointment;
        private String appointment_time;
        private int is_takeout_order;
        private String takeout_address;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getOrder_no() {
            return order_no;
        }

        public void setOrder_no(Object order_no) {
            this.order_no = order_no;
        }

        public String getTable_number() {
            return table_number;
        }

        public void setTable_number(String table_number) {
            this.table_number = table_number;
        }

        public Object getNumber_of_diners() {
            return number_of_diners;
        }

        public void setNumber_of_diners(Object number_of_diners) {
            this.number_of_diners = number_of_diners;
        }

        public double getSett_amount() {
            return sett_amount;
        }

        public void setSett_amount(int sett_amount) {
            this.sett_amount = sett_amount;
        }

        public Object getTransac_status() {
            return transac_status;
        }

        public void setTransac_status(String transac_status) {
            this.transac_status = transac_status;
        }

        public Object getOrder_receiving() {
            return order_receiving;
        }

        public void setOrder_receiving(Object order_receiving) {
            this.order_receiving = order_receiving;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public Object getGoods_list() {
            return goods_list;
        }

        public void setGoods_list(Object goods_list) {
            this.goods_list = goods_list;
        }

        public Object getIs_appointment() {
            return is_appointment;
        }

        public void setIs_appointment(String is_appointment) {
            this.is_appointment = is_appointment;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

        public int getIs_takeout_order() {
            return is_takeout_order;
        }

        public void setIs_takeout_order(int is_takeout_order) {
            this.is_takeout_order = is_takeout_order;
        }

        public String getTakeout_address() {
            return takeout_address;
        }

        public void setTakeout_address(String takeout_address) {
            this.takeout_address = takeout_address;
        }
    }
}
