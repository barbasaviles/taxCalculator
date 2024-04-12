
package Modelo;

import java.util.Date;

/**
 *
 * @author Juan
 */
public class impuestoRenta {    

    public double calcularImpuesto(double UVT) {
        double liquidacion = 0;
        
        double descuento = 0.15;
        
        
        if(UVT > 1090 && UVT <= 1700){
            liquidacion = UVT*0.19;
        }
        
        if(UVT > 1700 && UVT <= 4100){
            liquidacion = UVT*0.28;
        }
        
        if(UVT > 4100 && UVT <= 8670){
            liquidacion = UVT*0.33;
        }
        
        if(UVT > 8670 && UVT <= 18970){
            liquidacion = UVT*0.35;
        }
        
        if(UVT > 18970 && UVT <= 31000){
            liquidacion = UVT*0.37;
        }
        
        if(UVT > 31000){
            liquidacion = UVT*0.39;
        }              

        return liquidacion;
    }    

}
