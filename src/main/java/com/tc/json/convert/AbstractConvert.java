package com.tc.json.convert;


/**
 * 日期转换
 *
 * ${data[0].abac}(case[1:000,2:001])  暂未实现
 *
 * ${data[0].abac}(substring[1,2])  暂未实现
 *
 * ${data[0].abac}(dateformat[YYYY-MMM-DD,YYYY-MMM])   {@link json.convert.DateConvert}
 *
 */
public abstract class AbstractConvert implements Convert {

    @Override
    public Object convert(Object data, String keyStr) {
        try {
            return this.doConvert(data,keyStr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
