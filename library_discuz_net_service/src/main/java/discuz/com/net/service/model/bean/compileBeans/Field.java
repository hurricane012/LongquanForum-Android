/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.compileBeans;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Auto-generated: 2017-05-02 11:3:55
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Field {

    private String name;
    private String fieldid;
    private int required;
    private int unchangeable;
    private String description;
    private String type;
    private int size;
    @JsonProperty("nowSet")
    private Object nowSet;
    //private java.util.Lists<Choices> choices;



    public Object getNowSet() {
        return nowSet;
    }

    public void setNowSet(Object nowSet) {
        this.nowSet = nowSet;
    }

//    public Lists<Choices> getChoices() {
//        return choices;
//    }
//
//    public void setChoices(Lists<Choices> choices) {
//        this.choices = choices;
//    }


    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setFieldid(String fieldid) {
         this.fieldid = fieldid;
     }
     public String getFieldid() {
         return fieldid;
     }

    public void setRequired(int required) {
         this.required = required;
     }
     public int getRequired() {
         return required;
     }

    public void setUnchangeable(int unchangeable) {
         this.unchangeable = unchangeable;
     }
     public int getUnchangeable() {
         return unchangeable;
     }

    public void setDescription(String description) {
         this.description = description;
     }
     public String getDescription() {
         return description;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setSize(int size) {
         this.size = size;
     }
     public int getSize() {
         return size;
     }



}