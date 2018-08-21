package com.sf.shiva.oms.ht.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author 01369626
 * 
 */
public class AccessStatisticExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessStatisticExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andStatisticDateIsNull() {
            addCriterion("statistic_date is null");
            return (Criteria) this;
        }

        public Criteria andStatisticDateIsNotNull() {
            addCriterion("statistic_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticDateEqualTo(Date value) {
            addCriterionForJDBCDate("statistic_date =", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("statistic_date <>", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateGreaterThan(Date value) {
            addCriterionForJDBCDate("statistic_date >", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("statistic_date >=", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateLessThan(Date value) {
            addCriterionForJDBCDate("statistic_date <", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("statistic_date <=", value, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateIn(List<Date> values) {
            addCriterionForJDBCDate("statistic_date in", values, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("statistic_date not in", values, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("statistic_date between", value1, value2, "statisticDate");
            return (Criteria) this;
        }

        public Criteria andStatisticDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("statistic_date not between", value1, value2, "statisticDate");
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

        public Criteria andUserNumIsNull() {
            addCriterion("user_num is null");
            return (Criteria) this;
        }

        public Criteria andUserNumIsNotNull() {
            addCriterion("user_num is not null");
            return (Criteria) this;
        }

        public Criteria andUserNumEqualTo(Integer value) {
            addCriterion("user_num =", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotEqualTo(Integer value) {
            addCriterion("user_num <>", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThan(Integer value) {
            addCriterion("user_num >", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_num >=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThan(Integer value) {
            addCriterion("user_num <", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumLessThanOrEqualTo(Integer value) {
            addCriterion("user_num <=", value, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumIn(List<Integer> values) {
            addCriterion("user_num in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotIn(List<Integer> values) {
            addCriterion("user_num not in", values, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumBetween(Integer value1, Integer value2) {
            addCriterion("user_num between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUserNumNotBetween(Integer value1, Integer value2) {
            addCriterion("user_num not between", value1, value2, "userNum");
            return (Criteria) this;
        }

        public Criteria andUsageCountIsNull() {
            addCriterion("usage_count is null");
            return (Criteria) this;
        }

        public Criteria andUsageCountIsNotNull() {
            addCriterion("usage_count is not null");
            return (Criteria) this;
        }

        public Criteria andUsageCountEqualTo(Integer value) {
            addCriterion("usage_count =", value, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountNotEqualTo(Integer value) {
            addCriterion("usage_count <>", value, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountGreaterThan(Integer value) {
            addCriterion("usage_count >", value, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("usage_count >=", value, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountLessThan(Integer value) {
            addCriterion("usage_count <", value, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountLessThanOrEqualTo(Integer value) {
            addCriterion("usage_count <=", value, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountIn(List<Integer> values) {
            addCriterion("usage_count in", values, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountNotIn(List<Integer> values) {
            addCriterion("usage_count not in", values, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountBetween(Integer value1, Integer value2) {
            addCriterion("usage_count between", value1, value2, "usageCount");
            return (Criteria) this;
        }

        public Criteria andUsageCountNotBetween(Integer value1, Integer value2) {
            addCriterion("usage_count not between", value1, value2, "usageCount");
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