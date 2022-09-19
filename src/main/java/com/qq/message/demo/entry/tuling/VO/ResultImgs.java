package com.qq.message.demo.entry.tuling.VO;

import com.qq.message.demo.entry.tuling.DO.InputImage;
import com.qq.message.demo.entry.tuling.DO.InputText;

public class ResultImgs {
    String resultType;

    InputImage values;

    int groupType;

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public InputImage getValues() {
        return values;
    }

    public void setValues(InputImage values) {
        this.values = values;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }
}
