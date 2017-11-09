package com.ichinait.food.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andJmypsIsNull() {
            addCriterion("jmyps is null");
            return (Criteria) this;
        }

        public Criteria andJmypsIsNotNull() {
            addCriterion("jmyps is not null");
            return (Criteria) this;
        }

        public Criteria andJmypsEqualTo(String value) {
            addCriterion("jmyps =", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsNotEqualTo(String value) {
            addCriterion("jmyps <>", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsGreaterThan(String value) {
            addCriterion("jmyps >", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsGreaterThanOrEqualTo(String value) {
            addCriterion("jmyps >=", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsLessThan(String value) {
            addCriterion("jmyps <", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsLessThanOrEqualTo(String value) {
            addCriterion("jmyps <=", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsLike(String value) {
            addCriterion("jmyps like", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsNotLike(String value) {
            addCriterion("jmyps not like", value, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsIn(List<String> values) {
            addCriterion("jmyps in", values, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsNotIn(List<String> values) {
            addCriterion("jmyps not in", values, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsBetween(String value1, String value2) {
            addCriterion("jmyps between", value1, value2, "jmyps");
            return (Criteria) this;
        }

        public Criteria andJmypsNotBetween(String value1, String value2) {
            addCriterion("jmyps not between", value1, value2, "jmyps");
            return (Criteria) this;
        }

        public Criteria andMxzcfsIsNull() {
            addCriterion("mxzcfs is null");
            return (Criteria) this;
        }

        public Criteria andMxzcfsIsNotNull() {
            addCriterion("mxzcfs is not null");
            return (Criteria) this;
        }

        public Criteria andMxzcfsEqualTo(String value) {
            addCriterion("mxzcfs =", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsNotEqualTo(String value) {
            addCriterion("mxzcfs <>", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsGreaterThan(String value) {
            addCriterion("mxzcfs >", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsGreaterThanOrEqualTo(String value) {
            addCriterion("mxzcfs >=", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsLessThan(String value) {
            addCriterion("mxzcfs <", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsLessThanOrEqualTo(String value) {
            addCriterion("mxzcfs <=", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsLike(String value) {
            addCriterion("mxzcfs like", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsNotLike(String value) {
            addCriterion("mxzcfs not like", value, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsIn(List<String> values) {
            addCriterion("mxzcfs in", values, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsNotIn(List<String> values) {
            addCriterion("mxzcfs not in", values, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsBetween(String value1, String value2) {
            addCriterion("mxzcfs between", value1, value2, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andMxzcfsNotBetween(String value1, String value2) {
            addCriterion("mxzcfs not between", value1, value2, "mxzcfs");
            return (Criteria) this;
        }

        public Criteria andRmscvIsNull() {
            addCriterion("rmscv is null");
            return (Criteria) this;
        }

        public Criteria andRmscvIsNotNull() {
            addCriterion("rmscv is not null");
            return (Criteria) this;
        }

        public Criteria andRmscvEqualTo(String value) {
            addCriterion("rmscv =", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvNotEqualTo(String value) {
            addCriterion("rmscv <>", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvGreaterThan(String value) {
            addCriterion("rmscv >", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvGreaterThanOrEqualTo(String value) {
            addCriterion("rmscv >=", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvLessThan(String value) {
            addCriterion("rmscv <", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvLessThanOrEqualTo(String value) {
            addCriterion("rmscv <=", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvLike(String value) {
            addCriterion("rmscv like", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvNotLike(String value) {
            addCriterion("rmscv not like", value, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvIn(List<String> values) {
            addCriterion("rmscv in", values, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvNotIn(List<String> values) {
            addCriterion("rmscv not in", values, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvBetween(String value1, String value2) {
            addCriterion("rmscv between", value1, value2, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRmscvNotBetween(String value1, String value2) {
            addCriterion("rmscv not between", value1, value2, "rmscv");
            return (Criteria) this;
        }

        public Criteria andRRIsNull() {
            addCriterion("r_r is null");
            return (Criteria) this;
        }

        public Criteria andRRIsNotNull() {
            addCriterion("r_r is not null");
            return (Criteria) this;
        }

        public Criteria andRREqualTo(String value) {
            addCriterion("r_r =", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRNotEqualTo(String value) {
            addCriterion("r_r <>", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRGreaterThan(String value) {
            addCriterion("r_r >", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRGreaterThanOrEqualTo(String value) {
            addCriterion("r_r >=", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRLessThan(String value) {
            addCriterion("r_r <", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRLessThanOrEqualTo(String value) {
            addCriterion("r_r <=", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRLike(String value) {
            addCriterion("r_r like", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRNotLike(String value) {
            addCriterion("r_r not like", value, "rR");
            return (Criteria) this;
        }

        public Criteria andRRIn(List<String> values) {
            addCriterion("r_r in", values, "rR");
            return (Criteria) this;
        }

        public Criteria andRRNotIn(List<String> values) {
            addCriterion("r_r not in", values, "rR");
            return (Criteria) this;
        }

        public Criteria andRRBetween(String value1, String value2) {
            addCriterion("r_r between", value1, value2, "rR");
            return (Criteria) this;
        }

        public Criteria andRRNotBetween(String value1, String value2) {
            addCriterion("r_r not between", value1, value2, "rR");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdIsNull() {
            addCriterion("analysis_id is null");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdIsNotNull() {
            addCriterion("analysis_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdEqualTo(String value) {
            addCriterion("analysis_id =", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotEqualTo(String value) {
            addCriterion("analysis_id <>", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdGreaterThan(String value) {
            addCriterion("analysis_id >", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdGreaterThanOrEqualTo(String value) {
            addCriterion("analysis_id >=", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdLessThan(String value) {
            addCriterion("analysis_id <", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdLessThanOrEqualTo(String value) {
            addCriterion("analysis_id <=", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdLike(String value) {
            addCriterion("analysis_id like", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotLike(String value) {
            addCriterion("analysis_id not like", value, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdIn(List<String> values) {
            addCriterion("analysis_id in", values, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotIn(List<String> values) {
            addCriterion("analysis_id not in", values, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdBetween(String value1, String value2) {
            addCriterion("analysis_id between", value1, value2, "analysisId");
            return (Criteria) this;
        }

        public Criteria andAnalysisIdNotBetween(String value1, String value2) {
            addCriterion("analysis_id not between", value1, value2, "analysisId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdIsNull() {
            addCriterion("modal_attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdIsNotNull() {
            addCriterion("modal_attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdEqualTo(String value) {
            addCriterion("modal_attachment_id =", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdNotEqualTo(String value) {
            addCriterion("modal_attachment_id <>", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdGreaterThan(String value) {
            addCriterion("modal_attachment_id >", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("modal_attachment_id >=", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdLessThan(String value) {
            addCriterion("modal_attachment_id <", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdLessThanOrEqualTo(String value) {
            addCriterion("modal_attachment_id <=", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdLike(String value) {
            addCriterion("modal_attachment_id like", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdNotLike(String value) {
            addCriterion("modal_attachment_id not like", value, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdIn(List<String> values) {
            addCriterion("modal_attachment_id in", values, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdNotIn(List<String> values) {
            addCriterion("modal_attachment_id not in", values, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdBetween(String value1, String value2) {
            addCriterion("modal_attachment_id between", value1, value2, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andModalAttachmentIdNotBetween(String value1, String value2) {
            addCriterion("modal_attachment_id not between", value1, value2, "modalAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdIsNull() {
            addCriterion("data_attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdIsNotNull() {
            addCriterion("data_attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdEqualTo(String value) {
            addCriterion("data_attachment_id =", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdNotEqualTo(String value) {
            addCriterion("data_attachment_id <>", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdGreaterThan(String value) {
            addCriterion("data_attachment_id >", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("data_attachment_id >=", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdLessThan(String value) {
            addCriterion("data_attachment_id <", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdLessThanOrEqualTo(String value) {
            addCriterion("data_attachment_id <=", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdLike(String value) {
            addCriterion("data_attachment_id like", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdNotLike(String value) {
            addCriterion("data_attachment_id not like", value, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdIn(List<String> values) {
            addCriterion("data_attachment_id in", values, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdNotIn(List<String> values) {
            addCriterion("data_attachment_id not in", values, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdBetween(String value1, String value2) {
            addCriterion("data_attachment_id between", value1, value2, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andDataAttachmentIdNotBetween(String value1, String value2) {
            addCriterion("data_attachment_id not between", value1, value2, "dataAttachmentId");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(String value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(String value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(String value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(String value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(String value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(String value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLike(String value) {
            addCriterion("operator like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotLike(String value) {
            addCriterion("operator not like", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<String> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<String> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(String value1, String value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(String value1, String value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andCtIsNull() {
            addCriterion("ct is null");
            return (Criteria) this;
        }

        public Criteria andCtIsNotNull() {
            addCriterion("ct is not null");
            return (Criteria) this;
        }

        public Criteria andCtEqualTo(Date value) {
            addCriterion("ct =", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotEqualTo(Date value) {
            addCriterion("ct <>", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThan(Date value) {
            addCriterion("ct >", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtGreaterThanOrEqualTo(Date value) {
            addCriterion("ct >=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThan(Date value) {
            addCriterion("ct <", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtLessThanOrEqualTo(Date value) {
            addCriterion("ct <=", value, "ct");
            return (Criteria) this;
        }

        public Criteria andCtIn(List<Date> values) {
            addCriterion("ct in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotIn(List<Date> values) {
            addCriterion("ct not in", values, "ct");
            return (Criteria) this;
        }

        public Criteria andCtBetween(Date value1, Date value2) {
            addCriterion("ct between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andCtNotBetween(Date value1, Date value2) {
            addCriterion("ct not between", value1, value2, "ct");
            return (Criteria) this;
        }

        public Criteria andUtIsNull() {
            addCriterion("ut is null");
            return (Criteria) this;
        }

        public Criteria andUtIsNotNull() {
            addCriterion("ut is not null");
            return (Criteria) this;
        }

        public Criteria andUtEqualTo(Date value) {
            addCriterion("ut =", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtNotEqualTo(Date value) {
            addCriterion("ut <>", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtGreaterThan(Date value) {
            addCriterion("ut >", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtGreaterThanOrEqualTo(Date value) {
            addCriterion("ut >=", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtLessThan(Date value) {
            addCriterion("ut <", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtLessThanOrEqualTo(Date value) {
            addCriterion("ut <=", value, "ut");
            return (Criteria) this;
        }

        public Criteria andUtIn(List<Date> values) {
            addCriterion("ut in", values, "ut");
            return (Criteria) this;
        }

        public Criteria andUtNotIn(List<Date> values) {
            addCriterion("ut not in", values, "ut");
            return (Criteria) this;
        }

        public Criteria andUtBetween(Date value1, Date value2) {
            addCriterion("ut between", value1, value2, "ut");
            return (Criteria) this;
        }

        public Criteria andUtNotBetween(Date value1, Date value2) {
            addCriterion("ut not between", value1, value2, "ut");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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