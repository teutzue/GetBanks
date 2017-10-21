/**
 * RuleBaseServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net;

public class RuleBaseServiceLocator extends org.apache.axis.client.Service implements net.RuleBaseService {

    public RuleBaseServiceLocator() {
    }


    public RuleBaseServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RuleBaseServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RuleBasePort
    private String RuleBasePort_address = "http://localhost:8080/RuleBaseSI/RuleBaseService";

    public String getRuleBasePortAddress() {
        return RuleBasePort_address;
    }

    // The WSDD service name defaults to the port name.
    private String RuleBasePortWSDDServiceName = "RuleBasePort";

    public String getRuleBasePortWSDDServiceName() {
        return RuleBasePortWSDDServiceName;
    }

    public void setRuleBasePortWSDDServiceName(String name) {
        RuleBasePortWSDDServiceName = name;
    }

    public net.RuleBase getRuleBasePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(RuleBasePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRuleBasePort(endpoint);
    }

    public net.RuleBase getRuleBasePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.RuleBasePortBindingStub _stub = new net.RuleBasePortBindingStub(portAddress, this);
            _stub.setPortName(getRuleBasePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRuleBasePortEndpointAddress(String address) {
        RuleBasePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.RuleBase.class.isAssignableFrom(serviceEndpointInterface)) {
                net.RuleBasePortBindingStub _stub = new net.RuleBasePortBindingStub(new java.net.URL(RuleBasePort_address), this);
                _stub.setPortName(getRuleBasePortWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("RuleBasePort".equals(inputPortName)) {
            return getRuleBasePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://net/", "RuleBaseService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://net/", "RuleBasePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {

if ("RuleBasePort".equals(portName)) {
            setRuleBasePortEndpointAddress(address);
        }
        else
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
