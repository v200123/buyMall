package com.example.buymall.bean;

import java.util.Date;
import java.util.List;

public class createOrderBean {


    private Integer id; // 编号

    private String order_no; // 订单号

    private String table_number; // 桌号

    private String number_of_diners; // 用餐人数

    private Double sett_amount; // 计算总价

    private String transac_status; // 结账状态

    private String order_receiving;  // 接单状态

    private Date order_time; // 下单时间

    private List<OrderDetail> goods_list; // 订单详情

    private Integer is_appointment; // 是否预约单

    private Date appointment_time;  // 预约到店时间

    private Integer is_takeout_order;  // 是否外卖单

    //@TableField(select = false,exist = false)
    //private Address takeoutAddress  ;  // 外卖地址

    private String takeout_address; //外卖地址


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getNumber_of_diners() {
        return number_of_diners;
    }

    public void setNumber_of_diners(String number_of_diners) {
        this.number_of_diners = number_of_diners;
    }

    public Double getSett_amount() {
        return sett_amount;
    }

    public void setSett_amount(Double sett_amount) {
        this.sett_amount = sett_amount;
    }

    public String getTransac_status() {
        return transac_status;
    }

    public void setTransac_status(String transac_status) {
        this.transac_status = transac_status;
    }

    public String getOrder_receiving() {
        return order_receiving;
    }

    public void setOrder_receiving(String order_receiving) {
        this.order_receiving = order_receiving;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public List<OrderDetail> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<OrderDetail> goods_list) {
        this.goods_list = goods_list;
    }

    public Integer getIs_appointment() {
        return is_appointment;
    }

    public void setIs_appointment(Integer is_appointment) {
        this.is_appointment = is_appointment;
    }

    public Date getAppointment_time() {
        return appointment_time;
    }

    public void setAppointment_time(Date appointment_time) {
        this.appointment_time = appointment_time;
    }

    public Integer getIs_takeout_order() {
        return is_takeout_order;
    }

    public void setIs_takeout_order(Integer is_takeout_order) {
        this.is_takeout_order = is_takeout_order;
    }

    public String getTakeout_address() {
        return takeout_address;
    }

    public void setTakeout_address(String takeout_address) {
        this.takeout_address = takeout_address;
    }
}
