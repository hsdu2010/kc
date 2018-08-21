package com.sf.shiva.oms.ht.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 01369626
 * 
 */
public class HbaseQueryCfgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HbaseQueryCfgExample() {
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

        public Criteria andModuleNameIsNull() {
            addCriterion("module_name is null");
            return (Criteria) this;
        }

        public Criteria andModuleNameIsNotNull() {
            addCriterion("module_name is not null");
            return (Criteria) this;
        }

        public Criteria andModuleNameEqualTo(String value) {
            addCriterion("module_name =", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotEqualTo(String value) {
            addCriterion("module_name <>", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThan(String value) {
            addCriterion("module_name >", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("module_name >=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThan(String value) {
            addCriterion("module_name <", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLessThanOrEqualTo(String value) {
            addCriterion("module_name <=", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameLike(String value) {
            addCriterion("module_name like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotLike(String value) {
            addCriterion("module_name not like", value, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameIn(List<String> values) {
            addCriterion("module_name in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotIn(List<String> values) {
            addCriterion("module_name not in", values, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameBetween(String value1, String value2) {
            addCriterion("module_name between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andModuleNameNotBetween(String value1, String value2) {
            addCriterion("module_name not between", value1, value2, "moduleName");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIsNull() {
            addCriterion("query_type is null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIsNotNull() {
            addCriterion("query_type is not null");
            return (Criteria) this;
        }

        public Criteria andQueryTypeEqualTo(String value) {
            addCriterion("query_type =", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotEqualTo(String value) {
            addCriterion("query_type <>", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeGreaterThan(String value) {
            addCriterion("query_type >", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeGreaterThanOrEqualTo(String value) {
            addCriterion("query_type >=", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLessThan(String value) {
            addCriterion("query_type <", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLessThanOrEqualTo(String value) {
            addCriterion("query_type <=", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeLike(String value) {
            addCriterion("query_type like", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotLike(String value) {
            addCriterion("query_type not like", value, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeIn(List<String> values) {
            addCriterion("query_type in", values, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotIn(List<String> values) {
            addCriterion("query_type not in", values, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeBetween(String value1, String value2) {
            addCriterion("query_type between", value1, value2, "queryType");
            return (Criteria) this;
        }

        public Criteria andQueryTypeNotBetween(String value1, String value2) {
            addCriterion("query_type not between", value1, value2, "queryType");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyIsNull() {
            addCriterion("filter_family is null");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyIsNotNull() {
            addCriterion("filter_family is not null");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyEqualTo(String value) {
            addCriterion("filter_family =", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyNotEqualTo(String value) {
            addCriterion("filter_family <>", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyGreaterThan(String value) {
            addCriterion("filter_family >", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyGreaterThanOrEqualTo(String value) {
            addCriterion("filter_family >=", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyLessThan(String value) {
            addCriterion("filter_family <", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyLessThanOrEqualTo(String value) {
            addCriterion("filter_family <=", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyLike(String value) {
            addCriterion("filter_family like", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyNotLike(String value) {
            addCriterion("filter_family not like", value, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyIn(List<String> values) {
            addCriterion("filter_family in", values, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyNotIn(List<String> values) {
            addCriterion("filter_family not in", values, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyBetween(String value1, String value2) {
            addCriterion("filter_family between", value1, value2, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterFamilyNotBetween(String value1, String value2) {
            addCriterion("filter_family not between", value1, value2, "filterFamily");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIsNull() {
            addCriterion("filter_column is null");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIsNotNull() {
            addCriterion("filter_column is not null");
            return (Criteria) this;
        }

        public Criteria andFilterColumnEqualTo(String value) {
            addCriterion("filter_column =", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnNotEqualTo(String value) {
            addCriterion("filter_column <>", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnGreaterThan(String value) {
            addCriterion("filter_column >", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnGreaterThanOrEqualTo(String value) {
            addCriterion("filter_column >=", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnLessThan(String value) {
            addCriterion("filter_column <", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnLessThanOrEqualTo(String value) {
            addCriterion("filter_column <=", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnLike(String value) {
            addCriterion("filter_column like", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnNotLike(String value) {
            addCriterion("filter_column not like", value, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnIn(List<String> values) {
            addCriterion("filter_column in", values, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnNotIn(List<String> values) {
            addCriterion("filter_column not in", values, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnBetween(String value1, String value2) {
            addCriterion("filter_column between", value1, value2, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andFilterColumnNotBetween(String value1, String value2) {
            addCriterion("filter_column not between", value1, value2, "filterColumn");
            return (Criteria) this;
        }

        public Criteria andQueryParamIsNull() {
            addCriterion("query_param is null");
            return (Criteria) this;
        }

        public Criteria andQueryParamIsNotNull() {
            addCriterion("query_param is not null");
            return (Criteria) this;
        }

        public Criteria andQueryParamEqualTo(String value) {
            addCriterion("query_param =", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamNotEqualTo(String value) {
            addCriterion("query_param <>", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamGreaterThan(String value) {
            addCriterion("query_param >", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamGreaterThanOrEqualTo(String value) {
            addCriterion("query_param >=", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamLessThan(String value) {
            addCriterion("query_param <", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamLessThanOrEqualTo(String value) {
            addCriterion("query_param <=", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamLike(String value) {
            addCriterion("query_param like", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamNotLike(String value) {
            addCriterion("query_param not like", value, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamIn(List<String> values) {
            addCriterion("query_param in", values, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamNotIn(List<String> values) {
            addCriterion("query_param not in", values, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamBetween(String value1, String value2) {
            addCriterion("query_param between", value1, value2, "queryParam");
            return (Criteria) this;
        }

        public Criteria andQueryParamNotBetween(String value1, String value2) {
            addCriterion("query_param not between", value1, value2, "queryParam");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchIsNull() {
            addCriterion("is_fuzzy_search is null");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchIsNotNull() {
            addCriterion("is_fuzzy_search is not null");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchEqualTo(String value) {
            addCriterion("is_fuzzy_search =", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchNotEqualTo(String value) {
            addCriterion("is_fuzzy_search <>", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchGreaterThan(String value) {
            addCriterion("is_fuzzy_search >", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchGreaterThanOrEqualTo(String value) {
            addCriterion("is_fuzzy_search >=", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchLessThan(String value) {
            addCriterion("is_fuzzy_search <", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchLessThanOrEqualTo(String value) {
            addCriterion("is_fuzzy_search <=", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchLike(String value) {
            addCriterion("is_fuzzy_search like", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchNotLike(String value) {
            addCriterion("is_fuzzy_search not like", value, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchIn(List<String> values) {
            addCriterion("is_fuzzy_search in", values, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchNotIn(List<String> values) {
            addCriterion("is_fuzzy_search not in", values, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchBetween(String value1, String value2) {
            addCriterion("is_fuzzy_search between", value1, value2, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andIsFuzzySearchNotBetween(String value1, String value2) {
            addCriterion("is_fuzzy_search not between", value1, value2, "isFuzzySearch");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNull() {
            addCriterion("limit_num is null");
            return (Criteria) this;
        }

        public Criteria andLimitNumIsNotNull() {
            addCriterion("limit_num is not null");
            return (Criteria) this;
        }

        public Criteria andLimitNumEqualTo(Integer value) {
            addCriterion("limit_num =", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotEqualTo(Integer value) {
            addCriterion("limit_num <>", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThan(Integer value) {
            addCriterion("limit_num >", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_num >=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThan(Integer value) {
            addCriterion("limit_num <", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumLessThanOrEqualTo(Integer value) {
            addCriterion("limit_num <=", value, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumIn(List<Integer> values) {
            addCriterion("limit_num in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotIn(List<Integer> values) {
            addCriterion("limit_num not in", values, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumBetween(Integer value1, Integer value2) {
            addCriterion("limit_num between", value1, value2, "limitNum");
            return (Criteria) this;
        }

        public Criteria andLimitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_num not between", value1, value2, "limitNum");
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