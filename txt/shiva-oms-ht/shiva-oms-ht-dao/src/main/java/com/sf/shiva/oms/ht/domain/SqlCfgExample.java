package com.sf.shiva.oms.ht.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 01369626
 * 
 */
public class SqlCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SqlCfgExample() {
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

        public Criteria andSqlIdIsNull() {
            addCriterion("sql_id is null");
            return (Criteria) this;
        }

        public Criteria andSqlIdIsNotNull() {
            addCriterion("sql_id is not null");
            return (Criteria) this;
        }

        public Criteria andSqlIdEqualTo(String value) {
            addCriterion("sql_id =", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdNotEqualTo(String value) {
            addCriterion("sql_id <>", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdGreaterThan(String value) {
            addCriterion("sql_id >", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdGreaterThanOrEqualTo(String value) {
            addCriterion("sql_id >=", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdLessThan(String value) {
            addCriterion("sql_id <", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdLessThanOrEqualTo(String value) {
            addCriterion("sql_id <=", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdLike(String value) {
            addCriterion("sql_id like", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdNotLike(String value) {
            addCriterion("sql_id not like", value, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdIn(List<String> values) {
            addCriterion("sql_id in", values, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdNotIn(List<String> values) {
            addCriterion("sql_id not in", values, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdBetween(String value1, String value2) {
            addCriterion("sql_id between", value1, value2, "sqlId");
            return (Criteria) this;
        }

        public Criteria andSqlIdNotBetween(String value1, String value2) {
            addCriterion("sql_id not between", value1, value2, "sqlId");
            return (Criteria) this;
        }

        public Criteria andDbIdIsNull() {
            addCriterion("db_id is null");
            return (Criteria) this;
        }

        public Criteria andDbIdIsNotNull() {
            addCriterion("db_id is not null");
            return (Criteria) this;
        }

        public Criteria andDbIdEqualTo(String value) {
            addCriterion("db_id =", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotEqualTo(String value) {
            addCriterion("db_id <>", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdGreaterThan(String value) {
            addCriterion("db_id >", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdGreaterThanOrEqualTo(String value) {
            addCriterion("db_id >=", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLessThan(String value) {
            addCriterion("db_id <", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLessThanOrEqualTo(String value) {
            addCriterion("db_id <=", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdLike(String value) {
            addCriterion("db_id like", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotLike(String value) {
            addCriterion("db_id not like", value, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdIn(List<String> values) {
            addCriterion("db_id in", values, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotIn(List<String> values) {
            addCriterion("db_id not in", values, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdBetween(String value1, String value2) {
            addCriterion("db_id between", value1, value2, "dbId");
            return (Criteria) this;
        }

        public Criteria andDbIdNotBetween(String value1, String value2) {
            addCriterion("db_id not between", value1, value2, "dbId");
            return (Criteria) this;
        }

        public Criteria andSqlStatementIsNull() {
            addCriterion("sql_statement is null");
            return (Criteria) this;
        }

        public Criteria andSqlStatementIsNotNull() {
            addCriterion("sql_statement is not null");
            return (Criteria) this;
        }

        public Criteria andSqlStatementEqualTo(String value) {
            addCriterion("sql_statement =", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotEqualTo(String value) {
            addCriterion("sql_statement <>", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementGreaterThan(String value) {
            addCriterion("sql_statement >", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementGreaterThanOrEqualTo(String value) {
            addCriterion("sql_statement >=", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementLessThan(String value) {
            addCriterion("sql_statement <", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementLessThanOrEqualTo(String value) {
            addCriterion("sql_statement <=", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementLike(String value) {
            addCriterion("sql_statement like", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotLike(String value) {
            addCriterion("sql_statement not like", value, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementIn(List<String> values) {
            addCriterion("sql_statement in", values, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotIn(List<String> values) {
            addCriterion("sql_statement not in", values, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementBetween(String value1, String value2) {
            addCriterion("sql_statement between", value1, value2, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlStatementNotBetween(String value1, String value2) {
            addCriterion("sql_statement not between", value1, value2, "sqlStatement");
            return (Criteria) this;
        }

        public Criteria andSqlNameIsNull() {
            addCriterion("sql_name is null");
            return (Criteria) this;
        }

        public Criteria andSqlNameIsNotNull() {
            addCriterion("sql_name is not null");
            return (Criteria) this;
        }

        public Criteria andSqlNameEqualTo(String value) {
            addCriterion("sql_name =", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameNotEqualTo(String value) {
            addCriterion("sql_name <>", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameGreaterThan(String value) {
            addCriterion("sql_name >", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameGreaterThanOrEqualTo(String value) {
            addCriterion("sql_name >=", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameLessThan(String value) {
            addCriterion("sql_name <", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameLessThanOrEqualTo(String value) {
            addCriterion("sql_name <=", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameLike(String value) {
            addCriterion("sql_name like", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameNotLike(String value) {
            addCriterion("sql_name not like", value, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameIn(List<String> values) {
            addCriterion("sql_name in", values, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameNotIn(List<String> values) {
            addCriterion("sql_name not in", values, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameBetween(String value1, String value2) {
            addCriterion("sql_name between", value1, value2, "sqlName");
            return (Criteria) this;
        }

        public Criteria andSqlNameNotBetween(String value1, String value2) {
            addCriterion("sql_name not between", value1, value2, "sqlName");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andParamDescIsNull() {
            addCriterion("param_desc is null");
            return (Criteria) this;
        }

        public Criteria andParamDescIsNotNull() {
            addCriterion("param_desc is not null");
            return (Criteria) this;
        }

        public Criteria andParamDescEqualTo(String value) {
            addCriterion("param_desc =", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotEqualTo(String value) {
            addCriterion("param_desc <>", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescGreaterThan(String value) {
            addCriterion("param_desc >", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescGreaterThanOrEqualTo(String value) {
            addCriterion("param_desc >=", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLessThan(String value) {
            addCriterion("param_desc <", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLessThanOrEqualTo(String value) {
            addCriterion("param_desc <=", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescLike(String value) {
            addCriterion("param_desc like", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotLike(String value) {
            addCriterion("param_desc not like", value, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescIn(List<String> values) {
            addCriterion("param_desc in", values, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotIn(List<String> values) {
            addCriterion("param_desc not in", values, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescBetween(String value1, String value2) {
            addCriterion("param_desc between", value1, value2, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andParamDescNotBetween(String value1, String value2) {
            addCriterion("param_desc not between", value1, value2, "paramDesc");
            return (Criteria) this;
        }

        public Criteria andCreateTmIsNull() {
            addCriterion("create_tm is null");
            return (Criteria) this;
        }

        public Criteria andCreateTmIsNotNull() {
            addCriterion("create_tm is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTmEqualTo(Date value) {
            addCriterion("create_tm =", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotEqualTo(Date value) {
            addCriterion("create_tm <>", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmGreaterThan(Date value) {
            addCriterion("create_tm >", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmGreaterThanOrEqualTo(Date value) {
            addCriterion("create_tm >=", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmLessThan(Date value) {
            addCriterion("create_tm <", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmLessThanOrEqualTo(Date value) {
            addCriterion("create_tm <=", value, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmIn(List<Date> values) {
            addCriterion("create_tm in", values, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotIn(List<Date> values) {
            addCriterion("create_tm not in", values, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmBetween(Date value1, Date value2) {
            addCriterion("create_tm between", value1, value2, "createTm");
            return (Criteria) this;
        }

        public Criteria andCreateTmNotBetween(Date value1, Date value2) {
            addCriterion("create_tm not between", value1, value2, "createTm");
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

        public Criteria andModifyTmIsNull() {
            addCriterion("modify_tm is null");
            return (Criteria) this;
        }

        public Criteria andModifyTmIsNotNull() {
            addCriterion("modify_tm is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTmEqualTo(Date value) {
            addCriterion("modify_tm =", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotEqualTo(Date value) {
            addCriterion("modify_tm <>", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThan(Date value) {
            addCriterion("modify_tm >", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_tm >=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThan(Date value) {
            addCriterion("modify_tm <", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmLessThanOrEqualTo(Date value) {
            addCriterion("modify_tm <=", value, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmIn(List<Date> values) {
            addCriterion("modify_tm in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotIn(List<Date> values) {
            addCriterion("modify_tm not in", values, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmBetween(Date value1, Date value2) {
            addCriterion("modify_tm between", value1, value2, "modifyTm");
            return (Criteria) this;
        }

        public Criteria andModifyTmNotBetween(Date value1, Date value2) {
            addCriterion("modify_tm not between", value1, value2, "modifyTm");
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