package mx.com.omnius.yolabor.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by UDIaz on 03/02/18.
 */

public class JobTypeModel {
    public String getIdJobType() {
        return idJobType;
    }

    public void setIdJobType(String idJobType) {
        this.idJobType = idJobType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @SerializedName("idJobType")
    private String idJobType;

    @SerializedName("service")
    private String service;

    @SerializedName("name")
    private String name;

    @SerializedName("order")
    private String order;

    @SerializedName("cost")
    private String cost;


}
