package com.sf.shiva.oms.ht.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 01369626
 * 
 */
public class RpcManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RpcManagerExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateEmpIsNull() {
            addCriterion("create_emp is null");
            return (Criteria) this;
        }

        public Criteria andCreateEmpIsNotNull() {
            addCriterion("create_emp is not null");
            return (Criteria) this;
        }

        public Criteria andCreateEmpEqualTo(String value) {
            addCriterion("create_emp =", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotEqualTo(String value) {
            addCriterion("create_emp <>", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpGreaterThan(String value) {
            addCriterion("create_emp >", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpGreaterThanOrEqualTo(String value) {
            addCriterion("create_emp >=", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpLessThan(String value) {
            addCriterion("create_emp <", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpLessThanOrEqualTo(String value) {
            addCriterion("create_emp <=", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpLike(String value) {
            addCriterion("create_emp like", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotLike(String value) {
            addCriterion("create_emp not like", value, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpIn(List<String> values) {
            addCriterion("create_emp in", values, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotIn(List<String> values) {
            addCriterion("create_emp not in", values, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpBetween(String value1, String value2) {
            addCriterion("create_emp between", value1, value2, "createEmp");
            return (Criteria) this;
        }

        public Criteria andCreateEmpNotBetween(String value1, String value2) {
            addCriterion("create_emp not between", value1, value2, "createEmp");
            return (Criteria) this;
        }

        public Criteria andJarNameIsNull() {
            addCriterion("jar_name is null");
            return (Criteria) this;
        }

        public Criteria andJarNameIsNotNull() {
            addCriterion("jar_name is not null");
            return (Criteria) this;
        }

        public Criteria andJarNameEqualTo(String value) {
            addCriterion("jar_name =", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameNotEqualTo(String value) {
            addCriterion("jar_name <>", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameGreaterThan(String value) {
            addCriterion("jar_name >", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameGreaterThanOrEqualTo(String value) {
            addCriterion("jar_name >=", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameLessThan(String value) {
            addCriterion("jar_name <", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameLessThanOrEqualTo(String value) {
            addCriterion("jar_name <=", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameLike(String value) {
            addCriterion("jar_name like", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameNotLike(String value) {
            addCriterion("jar_name not like", value, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameIn(List<String> values) {
            addCriterion("jar_name in", values, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameNotIn(List<String> values) {
            addCriterion("jar_name not in", values, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameBetween(String value1, String value2) {
            addCriterion("jar_name between", value1, value2, "jarName");
            return (Criteria) this;
        }

        public Criteria andJarNameNotBetween(String value1, String value2) {
            addCriterion("jar_name not between", value1, value2, "jarName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNull() {
            addCriterion("application_name is null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIsNotNull() {
            addCriterion("application_name is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationNameEqualTo(String value) {
            addCriterion("application_name =", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotEqualTo(String value) {
            addCriterion("application_name <>", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThan(String value) {
            addCriterion("application_name >", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameGreaterThanOrEqualTo(String value) {
            addCriterion("application_name >=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThan(String value) {
            addCriterion("application_name <", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLessThanOrEqualTo(String value) {
            addCriterion("application_name <=", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameLike(String value) {
            addCriterion("application_name like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotLike(String value) {
            addCriterion("application_name not like", value, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameIn(List<String> values) {
            addCriterion("application_name in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotIn(List<String> values) {
            addCriterion("application_name not in", values, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameBetween(String value1, String value2) {
            addCriterion("application_name between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andApplicationNameNotBetween(String value1, String value2) {
            addCriterion("application_name not between", value1, value2, "applicationName");
            return (Criteria) this;
        }

        public Criteria andPackagePathIsNull() {
            addCriterion("package_path is null");
            return (Criteria) this;
        }

        public Criteria andPackagePathIsNotNull() {
            addCriterion("package_path is not null");
            return (Criteria) this;
        }

        public Criteria andPackagePathEqualTo(String value) {
            addCriterion("package_path =", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathNotEqualTo(String value) {
            addCriterion("package_path <>", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathGreaterThan(String value) {
            addCriterion("package_path >", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathGreaterThanOrEqualTo(String value) {
            addCriterion("package_path >=", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathLessThan(String value) {
            addCriterion("package_path <", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathLessThanOrEqualTo(String value) {
            addCriterion("package_path <=", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathLike(String value) {
            addCriterion("package_path like", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathNotLike(String value) {
            addCriterion("package_path not like", value, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathIn(List<String> values) {
            addCriterion("package_path in", values, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathNotIn(List<String> values) {
            addCriterion("package_path not in", values, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathBetween(String value1, String value2) {
            addCriterion("package_path between", value1, value2, "packagePath");
            return (Criteria) this;
        }

        public Criteria andPackagePathNotBetween(String value1, String value2) {
            addCriterion("package_path not between", value1, value2, "packagePath");
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

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
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

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andModifyEmpIsNull() {
            addCriterion("modify_emp is null");
            return (Criteria) this;
        }

        public Criteria andModifyEmpIsNotNull() {
            addCriterion("modify_emp is not null");
            return (Criteria) this;
        }

        public Criteria andModifyEmpEqualTo(String value) {
            addCriterion("modify_emp =", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotEqualTo(String value) {
            addCriterion("modify_emp <>", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpGreaterThan(String value) {
            addCriterion("modify_emp >", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpGreaterThanOrEqualTo(String value) {
            addCriterion("modify_emp >=", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpLessThan(String value) {
            addCriterion("modify_emp <", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpLessThanOrEqualTo(String value) {
            addCriterion("modify_emp <=", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpLike(String value) {
            addCriterion("modify_emp like", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotLike(String value) {
            addCriterion("modify_emp not like", value, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpIn(List<String> values) {
            addCriterion("modify_emp in", values, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotIn(List<String> values) {
            addCriterion("modify_emp not in", values, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpBetween(String value1, String value2) {
            addCriterion("modify_emp between", value1, value2, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andModifyEmpNotBetween(String value1, String value2) {
            addCriterion("modify_emp not between", value1, value2, "modifyEmp");
            return (Criteria) this;
        }

        public Criteria andZkUrlIsNull() {
            addCriterion("zk_url is null");
            return (Criteria) this;
        }

        public Criteria andZkUrlIsNotNull() {
            addCriterion("zk_url is not null");
            return (Criteria) this;
        }

        public Criteria andZkUrlEqualTo(String value) {
            addCriterion("zk_url =", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlNotEqualTo(String value) {
            addCriterion("zk_url <>", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlGreaterThan(String value) {
            addCriterion("zk_url >", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("zk_url >=", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlLessThan(String value) {
            addCriterion("zk_url <", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlLessThanOrEqualTo(String value) {
            addCriterion("zk_url <=", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlLike(String value) {
            addCriterion("zk_url like", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlNotLike(String value) {
            addCriterion("zk_url not like", value, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlIn(List<String> values) {
            addCriterion("zk_url in", values, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlNotIn(List<String> values) {
            addCriterion("zk_url not in", values, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlBetween(String value1, String value2) {
            addCriterion("zk_url between", value1, value2, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andZkUrlNotBetween(String value1, String value2) {
            addCriterion("zk_url not between", value1, value2, "zkUrl");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
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