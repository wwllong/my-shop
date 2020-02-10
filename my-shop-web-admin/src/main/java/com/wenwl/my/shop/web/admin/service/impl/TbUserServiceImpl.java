package com.wenwl.my.shop.web.admin.service.impl;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.utils.RegexpUtils;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.dao.TbUserDao;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author wenwl
 * @className TbUserServiceImpl
 * @data 2020/1/16
 * @vserion 1.0.0
 */
@Service
public class TbUserServiceImpl implements TbUserService {

    @Autowired
    private TbUserDao tbUserDao;

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @Override
    public List<TbUser> selectAll() {
        return tbUserDao.selectAll();
    }

    /**
     * 保存用户信息
     *
     * @param tbUser
     * @return
     */
    @Override
    public BaseResult save(TbUser tbUser) {
        BaseResult baseResult = checkTbUser(tbUser);

        if(BaseResult.STATUS_SUCCESS == baseResult.getStatus()){
            tbUser.setUpdated(new Date());

            //新增用户
            if(tbUser.getId() == null){
                // 密码加密处理
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
                tbUser.setCreated(new Date());
                tbUserDao.insert(tbUser);
            }
            //编辑用户
            else{
                tbUserDao.update(tbUser);
            }

            baseResult.setMessage("保存信息成功");
        }

        return baseResult;
    }

    /**
     * 删除用户
     *
     * @param tbUser
     * @return
     */
    @Override
    public Integer delete(TbUser tbUser) {
        return tbUserDao.delete(tbUser);
    }

    /**
     * 查询用户
     *
     * @param id
     * @return
     */
    @Override
    public TbUser getById(Long id) {
        return tbUserDao.getById(id);
    }

    /**
     * 模糊查询
     *
     * @param username
     * @return
     */
    @Override
    public List<TbUser> selectByName(String username) {
        return tbUserDao.selectByName(username);
    }

    /**
     * 登陆
     *
     * @param email
     * @param password
     * @return
     */
    @Override
    public TbUser login(String email, String password) {
        TbUser tbUser = tbUserDao.getByEmail(email);
        if(tbUser != null){
            // 明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

            // 判断密码是否匹配
            if(md5Password.equals(tbUser.getPassword())){
                return tbUser;
            }

        }
        return null;
    }

    /**
     * 用户信息的有效检验
     * @param tbUser
     */
    private BaseResult checkTbUser(TbUser tbUser){
        BaseResult baseResult = BaseResult.success();

        if(!RegexpUtils.checkEmail(tbUser.getEmail())) {
            baseResult = BaseResult.fail("邮箱不正确，请重新输入");
        }else if(StringUtils.isBlank(tbUser.getPassword())){
            baseResult = BaseResult.fail("密码不能为空，请重新输入");
        }else if(StringUtils.isBlank(tbUser.getUsername())){
            baseResult = BaseResult.fail("姓名不能为空，请重新输入");
        }else if(!RegexpUtils.checkPhone(tbUser.getPhone())){
            baseResult = BaseResult.fail("手机不正确，请重新输入");
        }

        return baseResult;
    }

}
