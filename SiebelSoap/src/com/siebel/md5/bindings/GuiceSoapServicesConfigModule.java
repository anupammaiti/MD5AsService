package com.siebel.md5.bindings;

import com.google.inject.servlet.ServletModule;
import com.siebel.md5.soap.convert.Md5Converter;
import com.siebel.md5.soap.convert.Md5ConverterImpl;

public class GuiceSoapServicesConfigModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(Md5Converter.class).to(Md5ConverterImpl.class);
        serve("/*").with(SoapEndpointBindings.class);
    }
}
