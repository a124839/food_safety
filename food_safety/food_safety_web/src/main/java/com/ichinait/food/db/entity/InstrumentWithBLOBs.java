package com.ichinait.food.db.entity;

public class InstrumentWithBLOBs extends Instrument {
    private String performances;

    private String memo;

    public String getPerformances() {
        return performances;
    }

    public void setPerformances(String performances) {
        this.performances = performances == null ? null : performances.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}