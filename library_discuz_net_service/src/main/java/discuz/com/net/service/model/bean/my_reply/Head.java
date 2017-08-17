/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.my_reply;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-08-11 9:55:33
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Head {

    @JsonProperty("errCode")
    private String errCode;
    @JsonProperty("errInfo")
    private String errInfo;
    private String version;
    private int alert;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }
}