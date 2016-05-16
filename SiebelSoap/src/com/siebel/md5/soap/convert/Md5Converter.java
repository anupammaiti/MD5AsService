package com.siebel.md5.soap.convert;

import javax.jws.WebService;

@WebService
public interface Md5Converter {
    String convertToMd5(final String inputString);
}
