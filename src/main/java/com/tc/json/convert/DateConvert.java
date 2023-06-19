package com.tc.json.convert;

import com.tc.json.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换
 */
public class DateConvert extends AbstractConvert {

    private String parameterDateType;
    private String resultDateType;


    public DateConvert(String parameterDateType, String resultDateType) {
        this.parameterDateType = parameterDateType;
        this.resultDateType = resultDateType;
    }

    public Object doConvert(Object data, String keyStr) throws Exception {
        Date date = null;
        if (data instanceof Date) {
            date = (Date) data;
        } else if (StringUtils.isNotBlank(parameterDateType)) {
            String dateStr = data.toString().trim();
            parameterDateType = parameterDateType.replace("'", "").replace("\"", "");
            SimpleDateFormat sdf = new SimpleDateFormat(parameterDateType);
            date = sdf.parse(dateStr);
        }
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(resultDateType)) {
            resultDateType = "yyyy-MM-dd";
        } else {
            resultDateType = resultDateType.replace("'", "").replace("\"", "");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(resultDateType);
        return simpleDateFormat.format(date);
    }

}
