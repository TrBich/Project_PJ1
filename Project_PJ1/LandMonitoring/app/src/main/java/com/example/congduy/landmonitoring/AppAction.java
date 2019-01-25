package com.example.congduy.landmonitoring;

import com.google.gson.Gson;

public enum AppAction {
    TAB_CHANGE("change_tab"),
    PHONE_RESULT("phone_result"),
    BARCODE_SCAN("barcode_scan"),
    CROP_CONFIRM("crop_confirm"),
    EDIT_RESULT("edit_image_result"),
    PREVIEW_RESULT("preview_image_result"),
    SIGN_RESULT("signature_result"),
    PROFILE_RESULT("profile_result"),
    CANCEL_CREATE_PROFILE("cancel_create_profile");

    public String value;

    AppAction(String value) {
        this.value = value;
    }

    private String extraData;

    public AppAction setData(Object extraData) {
        this.extraData = new Gson().toJson(extraData);
        return this;
    }

    public <T> T getData(Class<T> target) {
        return new Gson().fromJson(extraData, target);
    }

    @Override
    public String toString() {
        return value;
    }
}
