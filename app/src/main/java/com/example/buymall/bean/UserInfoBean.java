package com.example.buymall.bean;

public class UserInfoBean {

    /**
     * code : ok
     * user : {"user":{"id":0,"username":"烧雏鸡","phone":"13658170135","password":"e10adc3949ba59abbe56e057f20f883e","salt":"c5727","userPhoto":"https://wx3.sinaimg.cn/large/008gYSn8gy1hpgzzw8yfdj37c04w0he2.jpg","email":null,"state":1,"joinTime":"2024-05-15 11:49:05","lastLogin":"2024-05-15 11:49:05","tag":1,"token":"81b97454-bf37-476b-bab6-3ca588a035df"},"status":"2"}
     */

    private String code;
    private UserBeanX user;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserBeanX getUser() {
        return user;
    }

    public void setUser(UserBeanX user) {
        this.user = user;
    }

    public static class UserBeanX {
        /**
         * user : {"id":0,"username":"烧雏鸡","phone":"13658170135","password":"e10adc3949ba59abbe56e057f20f883e","salt":"c5727","userPhoto":"https://wx3.sinaimg.cn/large/008gYSn8gy1hpgzzw8yfdj37c04w0he2.jpg","email":null,"state":1,"joinTime":"2024-05-15 11:49:05","lastLogin":"2024-05-15 11:49:05","tag":1,"token":"81b97454-bf37-476b-bab6-3ca588a035df"}
         * status : 2
         */

        private UserBean user;
        private String status;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class UserBean {
            /**
             * id : 0
             * username : 烧雏鸡
             * phone : 13658170135
             * password : e10adc3949ba59abbe56e057f20f883e
             * salt : c5727
             * userPhoto : https://wx3.sinaimg.cn/large/008gYSn8gy1hpgzzw8yfdj37c04w0he2.jpg
             * email : null
             * state : 1
             * joinTime : 2024-05-15 11:49:05
             * lastLogin : 2024-05-15 11:49:05
             * tag : 1
             * token : 81b97454-bf37-476b-bab6-3ca588a035df
             */

            private int id;
            private String username;
            private String phone;
            private String password;
            private String salt;
            private String userPhoto;
            private Object email;
            private int state;
            private String joinTime;
            private String lastLogin;
            private int tag;
            private String token;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getSalt() {
                return salt;
            }

            public void setSalt(String salt) {
                this.salt = salt;
            }

            public String getUserPhoto() {
                return userPhoto;
            }

            public void setUserPhoto(String userPhoto) {
                this.userPhoto = userPhoto;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getJoinTime() {
                return joinTime;
            }

            public void setJoinTime(String joinTime) {
                this.joinTime = joinTime;
            }

            public String getLastLogin() {
                return lastLogin;
            }

            public void setLastLogin(String lastLogin) {
                this.lastLogin = lastLogin;
            }

            public int getTag() {
                return tag;
            }

            public void setTag(int tag) {
                this.tag = tag;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
