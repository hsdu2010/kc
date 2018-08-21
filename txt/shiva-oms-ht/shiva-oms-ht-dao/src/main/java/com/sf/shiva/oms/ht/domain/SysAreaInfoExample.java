package com.sf.shiva.oms.ht.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 01139932
 * 
 */
public class SysAreaInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysAreaInfoExample() {
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

        public Criteria andSysAreaUidIsNull() {
            addCriterion("sys_area_uid is null");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidIsNotNull() {
            addCriterion("sys_area_uid is not null");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidEqualTo(String value) {
            addCriterion("sys_area_uid =", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidNotEqualTo(String value) {
            addCriterion("sys_area_uid <>", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidGreaterThan(String value) {
            addCriterion("sys_area_uid >", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidGreaterThanOrEqualTo(String value) {
            addCriterion("sys_area_uid >=", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidLessThan(String value) {
            addCriterion("sys_area_uid <", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidLessThanOrEqualTo(String value) {
            addCriterion("sys_area_uid <=", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidLike(String value) {
            addCriterion("sys_area_uid like", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidNotLike(String value) {
            addCriterion("sys_area_uid not like", value, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidIn(List<String> values) {
            addCriterion("sys_area_uid in", values, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidNotIn(List<String> values) {
            addCriterion("sys_area_uid not in", values, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidBetween(String value1, String value2) {
            addCriterion("sys_area_uid between", value1, value2, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaUidNotBetween(String value1, String value2) {
            addCriterion("sys_area_uid not between", value1, value2, "sysAreaUid");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameIsNull() {
            addCriterion("sys_area_name is null");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameIsNotNull() {
            addCriterion("sys_area_name is not null");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameEqualTo(String value) {
            addCriterion("sys_area_name =", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameNotEqualTo(String value) {
            addCriterion("sys_area_name <>", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameGreaterThan(String value) {
            addCriterion("sys_area_name >", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("sys_area_name >=", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameLessThan(String value) {
            addCriterion("sys_area_name <", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameLessThanOrEqualTo(String value) {
            addCriterion("sys_area_name <=", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameLike(String value) {
            addCriterion("sys_area_name like", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameNotLike(String value) {
            addCriterion("sys_area_name not like", value, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameIn(List<String> values) {
            addCriterion("sys_area_name in", values, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameNotIn(List<String> values) {
            addCriterion("sys_area_name not in", values, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameBetween(String value1, String value2) {
            addCriterion("sys_area_name between", value1, value2, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaNameNotBetween(String value1, String value2) {
            addCriterion("sys_area_name not between", value1, value2, "sysAreaName");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelIsNull() {
            addCriterion("sys_area_level is null");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelIsNotNull() {
            addCriterion("sys_area_level is not null");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelEqualTo(Integer value) {
            addCriterion("sys_area_level =", value, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelNotEqualTo(Integer value) {
            addCriterion("sys_area_level <>", value, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelGreaterThan(Integer value) {
            addCriterion("sys_area_level >", value, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("sys_area_level >=", value, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelLessThan(Integer value) {
            addCriterion("sys_area_level <", value, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelLessThanOrEqualTo(Integer value) {
            addCriterion("sys_area_level <=", value, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelIn(List<Integer> values) {
            addCriterion("sys_area_level in", values, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelNotIn(List<Integer> values) {
            addCriterion("sys_area_level not in", values, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelBetween(Integer value1, Integer value2) {
            addCriterion("sys_area_level between", value1, value2, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysAreaLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("sys_area_level not between", value1, value2, "sysAreaLevel");
            return (Criteria) this;
        }

        public Criteria andSysParentUidIsNull() {
            addCriterion("sys_parent_uid is null");
            return (Criteria) this;
        }

        public Criteria andSysParentUidIsNotNull() {
            addCriterion("sys_parent_uid is not null");
            return (Criteria) this;
        }

        public Criteria andSysParentUidEqualTo(String value) {
            addCriterion("sys_parent_uid =", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidNotEqualTo(String value) {
            addCriterion("sys_parent_uid <>", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidGreaterThan(String value) {
            addCriterion("sys_parent_uid >", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidGreaterThanOrEqualTo(String value) {
            addCriterion("sys_parent_uid >=", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidLessThan(String value) {
            addCriterion("sys_parent_uid <", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidLessThanOrEqualTo(String value) {
            addCriterion("sys_parent_uid <=", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidLike(String value) {
            addCriterion("sys_parent_uid like", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidNotLike(String value) {
            addCriterion("sys_parent_uid not like", value, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidIn(List<String> values) {
            addCriterion("sys_parent_uid in", values, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidNotIn(List<String> values) {
            addCriterion("sys_parent_uid not in", values, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidBetween(String value1, String value2) {
            addCriterion("sys_parent_uid between", value1, value2, "sysParentUid");
            return (Criteria) this;
        }

        public Criteria andSysParentUidNotBetween(String value1, String value2) {
            addCriterion("sys_parent_uid not between", value1, value2, "sysParentUid");
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