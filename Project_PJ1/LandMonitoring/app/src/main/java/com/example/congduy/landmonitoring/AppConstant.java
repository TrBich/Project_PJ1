package com.example.congduy.landmonitoring;

public interface AppConstant {

    boolean isDebug = BuildConfig.DEBUG;

    String PREF_USER = "login";
    String PREF_SIGNED = "signature";
    String PREF_PROFILES = "profile";
    String PREF_HISTORY = "history";
    String PREF_LIST_TYPE = "type";

    int PERMISSION_CAMERA = 10101;
    int PERMISSION_PHOTO = 10102;
    int PERMISSION_STORAGE = 10103;
    int REQUEST_GALLERY = 10104;
    int REQUEST_PHOTO = 10105;

    String IMAGE_PATH = "MFO";
    String IMAGE_FILE_NAME = "%s_image.jpg";

    String FRONT_TYPE = "mattruoc";
    String BACK_TYPE = "matsau";
    String SERIAL_TYPE = "thesim";
    String PROFILE_TYPE = "khachhang";
    String SIGN_TYPE = "chuky";
    String SAMPLE_SIGN_TYPE = "chukymau";

    String[] numbers = {"Tất cả", "090", "093", "089", "0120", "0121", "0122", "0126", "0128"};

    int DEFAULT_FIRST_PAGE = 1;
    int DEFAULT_PAGE_SIZE = 10;

    String STATUS_ALL = "-1";
    String STATUS_IN_PROCESS = "0";
    String STATUS_SUCCESS = "1";
    String STATUS_ERROR = "3";
    String STATUS_NOT_PAY = "8";
    String STATUS_PAID = "9";

    String REPORT_URL = "http://137.59.45.35/app/home.jsp?ez=";
    String FAQ_URL = "http://mfo-admin.paditech.com/app/faq.html";
    String TERM_URL = "file:///android_asset/terms.htm";

    AppState app = AppState.getInstance();
}
