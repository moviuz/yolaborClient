package mx.com.omnius.yolabor.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by omnius on 15/03/18.
 */

public class HistoModel {


    public HistoModel() {
        this.fullName = fullName;
        this.servicio = servicio;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("type")
    private String servicio;
}
