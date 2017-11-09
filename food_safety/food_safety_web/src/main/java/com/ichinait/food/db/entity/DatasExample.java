package com.ichinait.food.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatasExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DatasExample() {
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

        public Criteria andInstrumentIdIsNull() {
            addCriterion("instrument_id is null");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdIsNotNull() {
            addCriterion("instrument_id is not null");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdEqualTo(String value) {
            addCriterion("instrument_id =", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotEqualTo(String value) {
            addCriterion("instrument_id <>", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdGreaterThan(String value) {
            addCriterion("instrument_id >", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdGreaterThanOrEqualTo(String value) {
            addCriterion("instrument_id >=", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdLessThan(String value) {
            addCriterion("instrument_id <", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdLessThanOrEqualTo(String value) {
            addCriterion("instrument_id <=", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdLike(String value) {
            addCriterion("instrument_id like", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotLike(String value) {
            addCriterion("instrument_id not like", value, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdIn(List<String> values) {
            addCriterion("instrument_id in", values, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotIn(List<String> values) {
            addCriterion("instrument_id not in", values, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdBetween(String value1, String value2) {
            addCriterion("instrument_id between", value1, value2, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andInstrumentIdNotBetween(String value1, String value2) {
            addCriterion("instrument_id not between", value1, value2, "instrumentId");
            return (Criteria) this;
        }

        public Criteria andSampleIdIsNull() {
            addCriterion("sample_id is null");
            return (Criteria) this;
        }

        public Criteria andSampleIdIsNotNull() {
            addCriterion("sample_id is not null");
            return (Criteria) this;
        }

        public Criteria andSampleIdEqualTo(String value) {
            addCriterion("sample_id =", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotEqualTo(String value) {
            addCriterion("sample_id <>", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdGreaterThan(String value) {
            addCriterion("sample_id >", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdGreaterThanOrEqualTo(String value) {
            addCriterion("sample_id >=", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdLessThan(String value) {
            addCriterion("sample_id <", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdLessThanOrEqualTo(String value) {
            addCriterion("sample_id <=", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdLike(String value) {
            addCriterion("sample_id like", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotLike(String value) {
            addCriterion("sample_id not like", value, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdIn(List<String> values) {
            addCriterion("sample_id in", values, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotIn(List<String> values) {
            addCriterion("sample_id not in", values, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdBetween(String value1, String value2) {
            addCriterion("sample_id between", value1, value2, "sampleId");
            return (Criteria) this;
        }

        public Criteria andSampleIdNotBetween(String value1, String value2) {
            addCriterion("sample_id not between", value1, value2, "sampleId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNull() {
            addCriterion("attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNotNull() {
            addCriterion("attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdEqualTo(String value) {
            addCriterion("attachment_id =", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotEqualTo(String value) {
            addCriterion("attachment_id <>", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThan(String value) {
            addCriterion("attachment_id >", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("attachment_id >=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThan(String value) {
            addCriterion("attachment_id <", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThanOrEqualTo(String value) {
            addCriterion("attachment_id <=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLike(String value) {
            addCriterion("attachment_id like", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotLike(String value) {
            addCriterion("attachment_id not like", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIn(List<String> values) {
            addCriterion("attachment_id in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotIn(List<String> values) {
            addCriterion("attachment_id not in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdBetween(String value1, String value2) {
            addCriterion("attachment_id between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotBetween(String value1, String value2) {
            addCriterion("attachment_id not between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andResolutionIsNull() {
            addCriterion("resolution is null");
            return (Criteria) this;
        }

        public Criteria andResolutionIsNotNull() {
            addCriterion("resolution is not null");
            return (Criteria) this;
        }

        public Criteria andResolutionEqualTo(String value) {
            addCriterion("resolution =", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotEqualTo(String value) {
            addCriterion("resolution <>", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionGreaterThan(String value) {
            addCriterion("resolution >", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionGreaterThanOrEqualTo(String value) {
            addCriterion("resolution >=", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLessThan(String value) {
            addCriterion("resolution <", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLessThanOrEqualTo(String value) {
            addCriterion("resolution <=", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLike(String value) {
            addCriterion("resolution like", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotLike(String value) {
            addCriterion("resolution not like", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionIn(List<String> values) {
            addCriterion("resolution in", values, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotIn(List<String> values) {
            addCriterion("resolution not in", values, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionBetween(String value1, String value2) {
            addCriterion("resolution between", value1, value2, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotBetween(String value1, String value2) {
            addCriterion("resolution not between", value1, value2, "resolution");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeIsNull() {
            addCriterion("wavelength_range is null");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeIsNotNull() {
            addCriterion("wavelength_range is not null");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeEqualTo(String value) {
            addCriterion("wavelength_range =", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeNotEqualTo(String value) {
            addCriterion("wavelength_range <>", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeGreaterThan(String value) {
            addCriterion("wavelength_range >", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeGreaterThanOrEqualTo(String value) {
            addCriterion("wavelength_range >=", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeLessThan(String value) {
            addCriterion("wavelength_range <", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeLessThanOrEqualTo(String value) {
            addCriterion("wavelength_range <=", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeLike(String value) {
            addCriterion("wavelength_range like", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeNotLike(String value) {
            addCriterion("wavelength_range not like", value, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeIn(List<String> values) {
            addCriterion("wavelength_range in", values, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeNotIn(List<String> values) {
            addCriterion("wavelength_range not in", values, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeBetween(String value1, String value2) {
            addCriterion("wavelength_range between", value1, value2, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andWavelengthRangeNotBetween(String value1, String value2) {
            addCriterion("wavelength_range not between", value1, value2, "wavelengthRange");
            return (Criteria) this;
        }

        public Criteria andScanningTimesIsNull() {
            addCriterion("scanning_times is null");
            return (Criteria) this;
        }

        public Criteria andScanningTimesIsNotNull() {
            addCriterion("scanning_times is not null");
            return (Criteria) this;
        }

        public Criteria andScanningTimesEqualTo(Integer value) {
            addCriterion("scanning_times =", value, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesNotEqualTo(Integer value) {
            addCriterion("scanning_times <>", value, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesGreaterThan(Integer value) {
            addCriterion("scanning_times >", value, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("scanning_times >=", value, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesLessThan(Integer value) {
            addCriterion("scanning_times <", value, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesLessThanOrEqualTo(Integer value) {
            addCriterion("scanning_times <=", value, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesIn(List<Integer> values) {
            addCriterion("scanning_times in", values, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesNotIn(List<Integer> values) {
            addCriterion("scanning_times not in", values, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesBetween(Integer value1, Integer value2) {
            addCriterion("scanning_times between", value1, value2, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("scanning_times not between", value1, value2, "scanningTimes");
            return (Criteria) this;
        }

        public Criteria andScanningDurationIsNull() {
            addCriterion("scanning_duration is null");
            return (Criteria) this;
        }

        public Criteria andScanningDurationIsNotNull() {
            addCriterion("scanning_duration is not null");
            return (Criteria) this;
        }

        public Criteria andScanningDurationEqualTo(String value) {
            addCriterion("scanning_duration =", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationNotEqualTo(String value) {
            addCriterion("scanning_duration <>", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationGreaterThan(String value) {
            addCriterion("scanning_duration >", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationGreaterThanOrEqualTo(String value) {
            addCriterion("scanning_duration >=", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationLessThan(String value) {
            addCriterion("scanning_duration <", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationLessThanOrEqualTo(String value) {
            addCriterion("scanning_duration <=", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationLike(String value) {
            addCriterion("scanning_duration like", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationNotLike(String value) {
            addCriterion("scanning_duration not like", value, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationIn(List<String> values) {
            addCriterion("scanning_duration in", values, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationNotIn(List<String> values) {
            addCriterion("scanning_duration not in", values, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationBetween(String value1, String value2) {
            addCriterion("scanning_duration between", value1, value2, "scanningDuration");
            return (Criteria) this;
        }

        public Criteria andScanningDurationNotBetween(String value1, String value2) {
            addCriterion("scanning_duration not between", value1, value2, "scanningDuration");
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