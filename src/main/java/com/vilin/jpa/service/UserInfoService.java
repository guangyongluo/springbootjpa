package com.vilin.jpa.service;

import com.vilin.jpa.entity.UserInfoEntity;
import com.vilin.jpa.repository.UserInfoRepository;
import com.vilin.jpa.vo.UserInfoRequestParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfoEntity addUserInfo(UserInfoEntity userInfoEntity){
        return userInfoRepository.save(userInfoEntity);
    }

    public Page<UserInfoEntity> findByCondition(UserInfoRequestParam userParam, Pageable pageable){
        return userInfoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<Predicate>();
            if (StringUtils.isNoneBlank(userParam.getFirstName())) {
                predicates.add(cb.like(root.get("firstName"), "%" + userParam.getFirstName() + "%"));
            }
            if (StringUtils.isNoneBlank(userParam.getTelephone())) {
                predicates.add(cb.equal(root.get("telephone"), userParam.getTelephone()));
            }
            if (StringUtils.isNoneBlank(userParam.getVersion())) {
                predicates.add(cb.greaterThan(root.get("version"), userParam.getVersion()));
            }
            if (userParam.getBeginCreateTime() != null && userParam.getEndCreateTime() != null) {
                predicates.add(cb.between(root.get("createTime"), userParam.getBeginCreateTime(), userParam.getEndCreateTime()));
            }
            if (StringUtils.isNotBlank(userParam.getAddressCity())) {
                predicates.add(cb.equal(root.join("addressEntity").get("addressCity"), userParam.getAddressCity()));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();

        }, pageable);
    }
}
