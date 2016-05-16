package com.siebel.md5.bindings;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.siebel.md5.soap.convert.Md5Converter;

import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

@Singleton
public class SoapEndpointBindings extends CXFNonSpringServlet {
	private static final long serialVersionUID = 5974086750414017871L;
	private final Md5Converter m_soapService;

    @Inject
    SoapEndpointBindings(final Md5Converter soapService) {
        m_soapService = soapService;
    }

    @Override
    protected void loadBus(final ServletConfig sc) {
        super.loadBus(sc);
        BusFactory.setDefaultBus(getBus());
        Endpoint.publish("/convertermd5", m_soapService);
    }
}
