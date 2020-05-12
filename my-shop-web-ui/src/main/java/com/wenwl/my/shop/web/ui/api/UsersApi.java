package com.wenwl.my.shop.web.ui.api;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.utils.HttpClientUtils;
import com.wenwl.my.shop.commons.utils.JacksonUtils;
import com.wenwl.my.shop.web.ui.dto.TbUserDTO;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * @author wenwl
 * @version 1.0.0
 * @className UsersApi
 * @description 会员管理接口
 * @date 2020/5/7 0:02
 */
public class UsersApi {

    public static TbUserDTO login(TbUserDTO tbUser) throws Exception {
        ArrayList<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));
        String json = HttpClientUtils.doPost(Api.API_USERS_LOGIN, params.toArray(new BasicNameValuePair[params.size()]));
        return JacksonUtils.json2pojoByTree(json, "data", TbUserDTO.class);
    }

    public static BaseResult register(TbUserDTO tbUser) throws Exception{
        ArrayList<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", tbUser.getUsername()));
        params.add(new BasicNameValuePair("password", tbUser.getPassword()));
        params.add(new BasicNameValuePair("phone", tbUser.getPhone()));
        params.add(new BasicNameValuePair("email", tbUser.getEmail()));
        String json = HttpClientUtils.doPost(Api.API_USERS_REGISTER, params.toArray(new BasicNameValuePair[params.size()]));
        return JacksonUtils.json2pojo(json,BaseResult.class);
    }

}
