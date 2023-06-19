package com.tc.json.convert;

public interface Convert {
    Object convert(Object data, String keyStr);
    Object doConvert(Object data, String keyStr) throws Exception;
}
