/*Copyright (c) 2016-2017 vcstest4.com All Rights Reserved.
 This software is the confidential and proprietary information of vcstest4.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with vcstest4.com*/
package com.testallservicesforvcs.adventureworks2014;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class EmployeeDepartmentHistoryId implements Serializable {

    private Integer businessEntityId;
    private Date startDate;
    private Short departmentId;
    private Short shiftId;

    public Integer getBusinessEntityId() {
        return this.businessEntityId;
    }

    public void setBusinessEntityId(Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Short getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(Short departmentId) {
        this.departmentId = departmentId;
    }

    public Short getShiftId() {
        return this.shiftId;
    }

    public void setShiftId(Short shiftId) {
        this.shiftId = shiftId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDepartmentHistory)) return false;
        final EmployeeDepartmentHistory employeeDepartmentHistory = (EmployeeDepartmentHistory) o;
        return Objects.equals(getBusinessEntityId(), employeeDepartmentHistory.getBusinessEntityId()) &&
                Objects.equals(getStartDate(), employeeDepartmentHistory.getStartDate()) &&
                Objects.equals(getDepartmentId(), employeeDepartmentHistory.getDepartmentId()) &&
                Objects.equals(getShiftId(), employeeDepartmentHistory.getShiftId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBusinessEntityId(),
                getStartDate(),
                getDepartmentId(),
                getShiftId());
    }
}
