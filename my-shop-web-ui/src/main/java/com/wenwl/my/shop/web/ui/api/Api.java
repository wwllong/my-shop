package com.wenwl.my.shop.web.ui.api;

/**
 * @author wenwl
 * @className Api
 * @dsecription api管理类
 * @data 2020/4/6 15:43
 * @vserion 1.0.0
 */
public class Api {

    // 主机地址
    public static final String HOST = "http://localhost:8090/api/v1";

    // 内容管理 - 首页幻灯片
    public static final String API_CONTENTS_PPT = HOST + "/contents/89";

    // 会员管理接口 - 登录
    public static final String API_USERS_LOGIN = HOST + "/users/login";
}
