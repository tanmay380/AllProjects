package com.example.attendanceapp;

public class ClassItem {

    private  long cid;

    public ClassItem(long cid, String className, String subjectName) {
        this.cid = cid;
        this.className = className;
        SubjectName = subjectName;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    String className;
    String SubjectName;

    public ClassItem(String className, String subjectName) {
        this.className = className;
        SubjectName = subjectName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }


}
