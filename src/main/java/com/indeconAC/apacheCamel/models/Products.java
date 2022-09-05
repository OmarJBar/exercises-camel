package com.indeconAC.apacheCamel.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Products {
    
    private Product cobre;
    private Product dolar;
    private Product plata;

    public Product getCobre() {
        return cobre;
    }
    public void setCobre(Product cobre) {
        this.cobre = cobre;
    }
    public Product getDolar() {
        return dolar;
    }
    public void setDolar(Product dolar) {
        this.dolar = dolar;
    }
    public Product getPlata() {
        return plata;
    }
    public void setPlata(Product plata) {
        this.plata = plata;
    }
    @Override
    public String toString() {
        return "Products [cobre=" + cobre + ", dolar=" + dolar + ", plata=" + plata + "]";
    }

    

}
