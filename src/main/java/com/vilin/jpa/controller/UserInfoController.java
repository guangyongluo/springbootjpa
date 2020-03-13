package com.vilin.jpa.controller;

import com.vilin.jpa.entity.UserInfoEntity;
import com.vilin.jpa.entity.UserReceivingAddressEntity;
import com.vilin.jpa.service.UserInfoService;
import com.vilin.jpa.vo.UserInfoRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/addUserInfo")
    @ResponseBody
    public UserInfoEntity addUserInfo(UserInfoRequestParam userParam){
        UserReceivingAddressEntity addressEntity = new UserReceivingAddressEntity();
        addressEntity.setAddressCity(userParam.getAddressCity());
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setFirstName(userParam.getFirstName());
        userInfoEntity.setLastName(userParam.getLastName());
        userInfoEntity.setTelephone(userParam.getTelephone());
        userInfoEntity.setCreateTime(userParam.getCreateTime());
        userInfoEntity.setVersion(userParam.getVersion());
        userInfoEntity.setAddressEntity(addressEntity);
        return userInfoService.addUserInfo(userInfoEntity);
    }

    @RequestMapping("/findByCondition")
    @ResponseBody
    public List<UserInfoEntity> findByCondition(UserInfoRequestParam userParam){
        Pageable pageable = PageRequest.of(1, 3);
        Page<UserInfoEntity> page = userInfoService.findByCondition(userParam, pageable);
        return page.getContent();
    }
}
