package com.example.buymall.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class OrderBean  {



    /**
     * msg : 获取成功
     * code : ok
     * goods : [{"id":6,"price":"128","name":"牛肉小火锅","description":"牛肉是补气血的佳品，可治疗由气血虚弱引起的脾胃虚弱都，健脾养胃，对面黄肌瘦以及虚胖的人群都有好处。","sales":26,"pic":"http://localhost:8081/static/goods/niurouhuoguo.jpg","categoryId":2,"tagsId":9,"status":1}]
     */

    private String msg;
    private String code;
    private List<GoodsBean> goods;



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class GoodsBean implements MultiItemEntity {
        /**
         * id : 6
         * price : 128
         * name : 牛肉小火锅
         * description : 牛肉是补气血的佳品，可治疗由气血虚弱引起的脾胃虚弱都，健脾养胃，对面黄肌瘦以及虚胖的人群都有好处。
         * sales : 26
         * pic : http://localhost:8081/static/goods/niurouhuoguo.jpg
         * categoryId : 2
         * tagsId : 9
         * status : 1
         */

        private int id;
        private String price;
        private String name;
        private String description;
        private int sales;
        private String pic;
        private int categoryId;
        private int tagsId;
        private int status;
        private int good_num = 0;
        public int ItemType = 1;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getTagsId() {
            return tagsId;
        }

        public void setTagsId(int tagsId) {
            this.tagsId = tagsId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public int getItemType() {
            return ItemType;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }
    }
}
