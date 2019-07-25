package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListModel {
    @SerializedName("error")
    private Boolean error;
    @SerializedName("data")
    private List<Datum> data = null;
    @SerializedName("message")
    private String message;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Datum {

        @SerializedName("user_id")
        private String userId;
        @SerializedName("user_name")
        private String userName;
        @SerializedName("mail_address")
        private String mailAddress;
        @SerializedName("position_id")
        private String positionId;
        @SerializedName("authority_id")
        private String authorityId;
        @SerializedName("retirement")
        private String retirement;
        @SerializedName("authority_description")
        private String authorityDescription;
        @SerializedName("position_description")
        private String positionDescription;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMailAddress() {
            return mailAddress;
        }

        public void setMailAddress(String mailAddress) {
            this.mailAddress = mailAddress;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getAuthorityId() {
            return authorityId;
        }

        public void setAuthorityId(String authorityId) {
            this.authorityId = authorityId;
        }

        public String getRetirement() {
            return retirement;
        }

        public void setRetirement(String retirement) {
            this.retirement = retirement;
        }

        public String getAuthorityDescription() {
            return authorityDescription;
        }

        public void setAuthorityDescription(String authorityDescription) {
            this.authorityDescription = authorityDescription;
        }

        public String getPositionDescription() {
            return positionDescription;
        }

        public void setPositionDescription(String positionDescription) {
            this.positionDescription = positionDescription;
        }

    }
}
