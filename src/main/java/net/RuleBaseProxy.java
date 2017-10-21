package net;

public class RuleBaseProxy implements net.RuleBase {
  private String _endpoint = null;
  private net.RuleBase ruleBase = null;
  
  public RuleBaseProxy() {
    _initRuleBaseProxy();
  }
  
  public RuleBaseProxy(String endpoint) {
    _endpoint = endpoint;
    _initRuleBaseProxy();
  }
  
  private void _initRuleBaseProxy() {
    try {
      ruleBase = (new net.RuleBaseServiceLocator()).getRuleBasePort();
      if (ruleBase != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ruleBase)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ruleBase)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ruleBase != null)
      ((javax.xml.rpc.Stub)ruleBase)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public net.RuleBase getRuleBase() {
    if (ruleBase == null)
      _initRuleBaseProxy();
    return ruleBase;
  }

  public String[] getBanks(int creditScore) throws java.rmi.RemoteException{
    if (ruleBase == null)
      _initRuleBaseProxy();
    return ruleBase.getBanks(creditScore);
  }
  
  
}