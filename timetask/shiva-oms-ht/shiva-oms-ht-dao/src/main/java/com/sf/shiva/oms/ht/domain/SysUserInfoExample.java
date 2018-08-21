package com.sf.shiva.oms.ht.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 01139932
 * 
 */
public class SysUserInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysUserInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSysUserUidIsNull() {
            addCriterion("sys_user_uid is null");
            return (Criteria) this;
        }

        public Criteria andSysUserUidIsNotNull() {
            addCriterion("sys_user_uid is not null");
            return (Criteria) this;
        }

        public Criteria andSysUserUidEqualTo(String value) {
            addCriterion("sys_user_uid =", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidNotEqualTo(String value) {
            addCriterion("sys_user_uid <>", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidGreaterThan(String value) {
            addCriterion("sys_user_uid >", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidGreaterThanOrEqualTo(String value) {
            addCriterion("sys_user_uid >=", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidLessThan(String value) {
            addCriterion("sys_user_uid <", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidLessThanOrEqualTo(String value) {
            addCriterion("sys_user_uid <=", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidLike(String value) {
            addCriterion("sys_user_uid like", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidNotLike(String value) {
            addCriterion("sys_user_uid not like", value, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidIn(List<String> values) {
            addCriterion("sys_user_uid in", values, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidNotIn(List<String> values) {
            addCriterion("sys_user_uid not in", values, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidBetween(String value1, String value2) {
            addCriterion("sys_user_uid between", value1, value2, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUserUidNotBetween(String value1, String value2) {
            addCriterion("sys_user_uid not between", value1, value2, "sysUserUid");
            return (Criteria) this;
        }

        public Criteria andSysUsernameIsNull() {
            addCriterion("sys_username is null");
            return (Criteria) this;
        }

        public Criteria andSysUsernameIsNotNull() {
            addCriterion("sys_username is not null");
            return (Criteria) this;
        }

        public Criteria andSysUsernameEqualTo(String value) {
            addCriterion("sys_username =", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameNotEqualTo(String value) {
            addCriterion("sys_username <>", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameGreaterThan(String value) {
            addCriterion("sys_username >", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_username >=", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameLessThan(String value) {
            addCriterion("sys_username <", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameLessThanOrEqualTo(String value) {
            addCriterion("sys_username <=", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameLike(String value) {
            addCriterion("sys_username like", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameNotLike(String value) {
            addCriterion("sys_username not like", value, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameIn(List<String> values) {
            addCriterion("sys_username in", values, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameNotIn(List<String> values) {
            addCriterion("sys_username not in", values, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameBetween(String value1, String value2) {
            addCriterion("sys_username between", value1, value2, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysUsernameNotBetween(String value1, String value2) {
            addCriterion("sys_username not between", value1, value2, "sysUsername");
            return (Criteria) this;
        }

        public Criteria andSysPwdIsNull() {
            addCriterion("sys_pwd is null");
            return (Criteria) this;
        }

        public Criteria andSysPwdIsNotNull() {
            addCriterion("sys_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andSysPwdEqualTo(String value) {
            addCriterion("sys_pwd =", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdNotEqualTo(String value) {
            addCriterion("sys_pwd <>", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdGreaterThan(String value) {
            addCriterion("sys_pwd >", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdGreaterThanOrEqualTo(String value) {
            addCriterion("sys_pwd >=", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdLessThan(String value) {
            addCriterion("sys_pwd <", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdLessThanOrEqualTo(String value) {
            addCriterion("sys_pwd <=", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdLike(String value) {
            addCriterion("sys_pwd like", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdNotLike(String value) {
            addCriterion("sys_pwd not like", value, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdIn(List<String> values) {
            addCriterion("sys_pwd in", values, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdNotIn(List<String> values) {
            addCriterion("sys_pwd not in", values, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdBetween(String value1, String value2) {
            addCriterion("sys_pwd between", value1, value2, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysPwdNotBetween(String value1, String value2) {
            addCriterion("sys_pwd not between", value1, value2, "sysPwd");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNull() {
            addCriterion("sys_name is null");
            return (Criteria) this;
        }

        public Criteria andSysNameIsNotNull() {
            addCriterion("sys_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysNameEqualTo(String value) {
            addCriterion("sys_name =", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotEqualTo(String value) {
            addCriterion("sys_name <>", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThan(String value) {
            addCriterion("sys_name >", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_name >=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThan(String value) {
            addCriterion("sys_name <", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLessThanOrEqualTo(String value) {
            addCriterion("sys_name <=", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameLike(String value) {
            addCriterion("sys_name like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotLike(String value) {
            addCriterion("sys_name not like", value, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameIn(List<String> values) {
            addCriterion("sys_name in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotIn(List<String> values) {
            addCriterion("sys_name not in", values, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameBetween(String value1, String value2) {
            addCriterion("sys_name between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysNameNotBetween(String value1, String value2) {
            addCriterion("sys_name not between", value1, value2, "sysName");
            return (Criteria) this;
        }

        public Criteria andSysBirthIsNull() {
            addCriterion("sys_birth is null");
            return (Criteria) this;
        }

        public Criteria andSysBirthIsNotNull() {
            addCriterion("sys_birth is not null");
            return (Criteria) this;
        }

        public Criteria andSysBirthEqualTo(String value) {
            addCriterion("sys_birth =", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthNotEqualTo(String value) {
            addCriterion("sys_birth <>", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthGreaterThan(String value) {
            addCriterion("sys_birth >", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthGreaterThanOrEqualTo(String value) {
            addCriterion("sys_birth >=", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthLessThan(String value) {
            addCriterion("sys_birth <", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthLessThanOrEqualTo(String value) {
            addCriterion("sys_birth <=", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthLike(String value) {
            addCriterion("sys_birth like", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthNotLike(String value) {
            addCriterion("sys_birth not like", value, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthIn(List<String> values) {
            addCriterion("sys_birth in", values, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthNotIn(List<String> values) {
            addCriterion("sys_birth not in", values, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthBetween(String value1, String value2) {
            addCriterion("sys_birth between", value1, value2, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysBirthNotBetween(String value1, String value2) {
            addCriterion("sys_birth not between", value1, value2, "sysBirth");
            return (Criteria) this;
        }

        public Criteria andSysSexIsNull() {
            addCriterion("sys_sex is null");
            return (Criteria) this;
        }

        public Criteria andSysSexIsNotNull() {
            addCriterion("sys_sex is not null");
            return (Criteria) this;
        }

        public Criteria andSysSexEqualTo(String value) {
            addCriterion("sys_sex =", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexNotEqualTo(String value) {
            addCriterion("sys_sex <>", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexGreaterThan(String value) {
            addCriterion("sys_sex >", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexGreaterThanOrEqualTo(String value) {
            addCriterion("sys_sex >=", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexLessThan(String value) {
            addCriterion("sys_sex <", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexLessThanOrEqualTo(String value) {
            addCriterion("sys_sex <=", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexLike(String value) {
            addCriterion("sys_sex like", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexNotLike(String value) {
            addCriterion("sys_sex not like", value, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexIn(List<String> values) {
            addCriterion("sys_sex in", values, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexNotIn(List<String> values) {
            addCriterion("sys_sex not in", values, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexBetween(String value1, String value2) {
            addCriterion("sys_sex between", value1, value2, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysSexNotBetween(String value1, String value2) {
            addCriterion("sys_sex not between", value1, value2, "sysSex");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeIsNull() {
            addCriterion("sys_cert_type is null");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeIsNotNull() {
            addCriterion("sys_cert_type is not null");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeEqualTo(String value) {
            addCriterion("sys_cert_type =", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeNotEqualTo(String value) {
            addCriterion("sys_cert_type <>", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeGreaterThan(String value) {
            addCriterion("sys_cert_type >", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sys_cert_type >=", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeLessThan(String value) {
            addCriterion("sys_cert_type <", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeLessThanOrEqualTo(String value) {
            addCriterion("sys_cert_type <=", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeLike(String value) {
            addCriterion("sys_cert_type like", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeNotLike(String value) {
            addCriterion("sys_cert_type not like", value, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeIn(List<String> values) {
            addCriterion("sys_cert_type in", values, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeNotIn(List<String> values) {
            addCriterion("sys_cert_type not in", values, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeBetween(String value1, String value2) {
            addCriterion("sys_cert_type between", value1, value2, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertTypeNotBetween(String value1, String value2) {
            addCriterion("sys_cert_type not between", value1, value2, "sysCertType");
            return (Criteria) this;
        }

        public Criteria andSysCertValIsNull() {
            addCriterion("sys_cert_val is null");
            return (Criteria) this;
        }

        public Criteria andSysCertValIsNotNull() {
            addCriterion("sys_cert_val is not null");
            return (Criteria) this;
        }

        public Criteria andSysCertValEqualTo(String value) {
            addCriterion("sys_cert_val =", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValNotEqualTo(String value) {
            addCriterion("sys_cert_val <>", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValGreaterThan(String value) {
            addCriterion("sys_cert_val >", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValGreaterThanOrEqualTo(String value) {
            addCriterion("sys_cert_val >=", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValLessThan(String value) {
            addCriterion("sys_cert_val <", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValLessThanOrEqualTo(String value) {
            addCriterion("sys_cert_val <=", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValLike(String value) {
            addCriterion("sys_cert_val like", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValNotLike(String value) {
            addCriterion("sys_cert_val not like", value, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValIn(List<String> values) {
            addCriterion("sys_cert_val in", values, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValNotIn(List<String> values) {
            addCriterion("sys_cert_val not in", values, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValBetween(String value1, String value2) {
            addCriterion("sys_cert_val between", value1, value2, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysCertValNotBetween(String value1, String value2) {
            addCriterion("sys_cert_val not between", value1, value2, "sysCertVal");
            return (Criteria) this;
        }

        public Criteria andSysUserProvIsNull() {
            addCriterion("sys_user_prov is null");
            return (Criteria) this;
        }

        public Criteria andSysUserProvIsNotNull() {
            addCriterion("sys_user_prov is not null");
            return (Criteria) this;
        }

        public Criteria andSysUserProvEqualTo(String value) {
            addCriterion("sys_user_prov =", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvNotEqualTo(String value) {
            addCriterion("sys_user_prov <>", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvGreaterThan(String value) {
            addCriterion("sys_user_prov >", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvGreaterThanOrEqualTo(String value) {
            addCriterion("sys_user_prov >=", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvLessThan(String value) {
            addCriterion("sys_user_prov <", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvLessThanOrEqualTo(String value) {
            addCriterion("sys_user_prov <=", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvLike(String value) {
            addCriterion("sys_user_prov like", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvNotLike(String value) {
            addCriterion("sys_user_prov not like", value, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvIn(List<String> values) {
            addCriterion("sys_user_prov in", values, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvNotIn(List<String> values) {
            addCriterion("sys_user_prov not in", values, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvBetween(String value1, String value2) {
            addCriterion("sys_user_prov between", value1, value2, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysUserProvNotBetween(String value1, String value2) {
            addCriterion("sys_user_prov not between", value1, value2, "sysUserProv");
            return (Criteria) this;
        }

        public Criteria andSysProvNameIsNull() {
            addCriterion("sys_prov_name is null");
            return (Criteria) this;
        }

        public Criteria andSysProvNameIsNotNull() {
            addCriterion("sys_prov_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysProvNameEqualTo(String value) {
            addCriterion("sys_prov_name =", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameNotEqualTo(String value) {
            addCriterion("sys_prov_name <>", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameGreaterThan(String value) {
            addCriterion("sys_prov_name >", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_prov_name >=", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameLessThan(String value) {
            addCriterion("sys_prov_name <", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameLessThanOrEqualTo(String value) {
            addCriterion("sys_prov_name <=", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameLike(String value) {
            addCriterion("sys_prov_name like", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameNotLike(String value) {
            addCriterion("sys_prov_name not like", value, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameIn(List<String> values) {
            addCriterion("sys_prov_name in", values, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameNotIn(List<String> values) {
            addCriterion("sys_prov_name not in", values, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameBetween(String value1, String value2) {
            addCriterion("sys_prov_name between", value1, value2, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysProvNameNotBetween(String value1, String value2) {
            addCriterion("sys_prov_name not between", value1, value2, "sysProvName");
            return (Criteria) this;
        }

        public Criteria andSysUserCityIsNull() {
            addCriterion("sys_user_city is null");
            return (Criteria) this;
        }

        public Criteria andSysUserCityIsNotNull() {
            addCriterion("sys_user_city is not null");
            return (Criteria) this;
        }

        public Criteria andSysUserCityEqualTo(String value) {
            addCriterion("sys_user_city =", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityNotEqualTo(String value) {
            addCriterion("sys_user_city <>", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityGreaterThan(String value) {
            addCriterion("sys_user_city >", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityGreaterThanOrEqualTo(String value) {
            addCriterion("sys_user_city >=", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityLessThan(String value) {
            addCriterion("sys_user_city <", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityLessThanOrEqualTo(String value) {
            addCriterion("sys_user_city <=", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityLike(String value) {
            addCriterion("sys_user_city like", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityNotLike(String value) {
            addCriterion("sys_user_city not like", value, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityIn(List<String> values) {
            addCriterion("sys_user_city in", values, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityNotIn(List<String> values) {
            addCriterion("sys_user_city not in", values, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityBetween(String value1, String value2) {
            addCriterion("sys_user_city between", value1, value2, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysUserCityNotBetween(String value1, String value2) {
            addCriterion("sys_user_city not between", value1, value2, "sysUserCity");
            return (Criteria) this;
        }

        public Criteria andSysCityNameIsNull() {
            addCriterion("sys_city_name is null");
            return (Criteria) this;
        }

        public Criteria andSysCityNameIsNotNull() {
            addCriterion("sys_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysCityNameEqualTo(String value) {
            addCriterion("sys_city_name =", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameNotEqualTo(String value) {
            addCriterion("sys_city_name <>", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameGreaterThan(String value) {
            addCriterion("sys_city_name >", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_city_name >=", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameLessThan(String value) {
            addCriterion("sys_city_name <", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameLessThanOrEqualTo(String value) {
            addCriterion("sys_city_name <=", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameLike(String value) {
            addCriterion("sys_city_name like", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameNotLike(String value) {
            addCriterion("sys_city_name not like", value, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameIn(List<String> values) {
            addCriterion("sys_city_name in", values, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameNotIn(List<String> values) {
            addCriterion("sys_city_name not in", values, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameBetween(String value1, String value2) {
            addCriterion("sys_city_name between", value1, value2, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysCityNameNotBetween(String value1, String value2) {
            addCriterion("sys_city_name not between", value1, value2, "sysCityName");
            return (Criteria) this;
        }

        public Criteria andSysUserImgIsNull() {
            addCriterion("sys_user_img is null");
            return (Criteria) this;
        }

        public Criteria andSysUserImgIsNotNull() {
            addCriterion("sys_user_img is not null");
            return (Criteria) this;
        }

        public Criteria andSysUserImgEqualTo(String value) {
            addCriterion("sys_user_img =", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgNotEqualTo(String value) {
            addCriterion("sys_user_img <>", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgGreaterThan(String value) {
            addCriterion("sys_user_img >", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgGreaterThanOrEqualTo(String value) {
            addCriterion("sys_user_img >=", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgLessThan(String value) {
            addCriterion("sys_user_img <", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgLessThanOrEqualTo(String value) {
            addCriterion("sys_user_img <=", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgLike(String value) {
            addCriterion("sys_user_img like", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgNotLike(String value) {
            addCriterion("sys_user_img not like", value, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgIn(List<String> values) {
            addCriterion("sys_user_img in", values, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgNotIn(List<String> values) {
            addCriterion("sys_user_img not in", values, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgBetween(String value1, String value2) {
            addCriterion("sys_user_img between", value1, value2, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysUserImgNotBetween(String value1, String value2) {
            addCriterion("sys_user_img not between", value1, value2, "sysUserImg");
            return (Criteria) this;
        }

        public Criteria andSysImgCountIsNull() {
            addCriterion("sys_img_count is null");
            return (Criteria) this;
        }

        public Criteria andSysImgCountIsNotNull() {
            addCriterion("sys_img_count is not null");
            return (Criteria) this;
        }

        public Criteria andSysImgCountEqualTo(Integer value) {
            addCriterion("sys_img_count =", value, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountNotEqualTo(Integer value) {
            addCriterion("sys_img_count <>", value, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountGreaterThan(Integer value) {
            addCriterion("sys_img_count >", value, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_img_count >=", value, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountLessThan(Integer value) {
            addCriterion("sys_img_count <", value, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountLessThanOrEqualTo(Integer value) {
            addCriterion("sys_img_count <=", value, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountIn(List<Integer> values) {
            addCriterion("sys_img_count in", values, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountNotIn(List<Integer> values) {
            addCriterion("sys_img_count not in", values, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountBetween(Integer value1, Integer value2) {
            addCriterion("sys_img_count between", value1, value2, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andSysImgCountNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_img_count not between", value1, value2, "sysImgCount");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(String value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(String value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(String value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(String value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(String value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLike(String value) {
            addCriterion("createtime like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotLike(String value) {
            addCriterion("createtime not like", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<String> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<String> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(String value1, String value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(String value1, String value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(String value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(String value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(String value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(String value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(String value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(String value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLike(String value) {
            addCriterion("updatetime like", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotLike(String value) {
            addCriterion("updatetime not like", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<String> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<String> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(String value1, String value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(String value1, String value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateuidIsNull() {
            addCriterion("updateuid is null");
            return (Criteria) this;
        }

        public Criteria andUpdateuidIsNotNull() {
            addCriterion("updateuid is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateuidEqualTo(String value) {
            addCriterion("updateuid =", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidNotEqualTo(String value) {
            addCriterion("updateuid <>", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidGreaterThan(String value) {
            addCriterion("updateuid >", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidGreaterThanOrEqualTo(String value) {
            addCriterion("updateuid >=", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidLessThan(String value) {
            addCriterion("updateuid <", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidLessThanOrEqualTo(String value) {
            addCriterion("updateuid <=", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidLike(String value) {
            addCriterion("updateuid like", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidNotLike(String value) {
            addCriterion("updateuid not like", value, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidIn(List<String> values) {
            addCriterion("updateuid in", values, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidNotIn(List<String> values) {
            addCriterion("updateuid not in", values, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidBetween(String value1, String value2) {
            addCriterion("updateuid between", value1, value2, "updateuid");
            return (Criteria) this;
        }

        public Criteria andUpdateuidNotBetween(String value1, String value2) {
            addCriterion("updateuid not between", value1, value2, "updateuid");
            return (Criteria) this;
        }

        public Criteria andDelStatusIsNull() {
            addCriterion("del_status is null");
            return (Criteria) this;
        }

        public Criteria andDelStatusIsNotNull() {
            addCriterion("del_status is not null");
            return (Criteria) this;
        }

        public Criteria andDelStatusEqualTo(Boolean value) {
            addCriterion("del_status =", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusNotEqualTo(Boolean value) {
            addCriterion("del_status <>", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusGreaterThan(Boolean value) {
            addCriterion("del_status >", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("del_status >=", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusLessThan(Boolean value) {
            addCriterion("del_status <", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("del_status <=", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusIn(List<Boolean> values) {
            addCriterion("del_status in", values, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusNotIn(List<Boolean> values) {
            addCriterion("del_status not in", values, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("del_status between", value1, value2, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("del_status not between", value1, value2, "delStatus");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements java.io.Serializable {
        private static final long serialVersionUID = 1L;

        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}