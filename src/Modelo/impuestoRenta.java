
package Modelo;

import java.util.Date;

/**
 *
 * @author Juan
 */
public class impuestoRenta {    

    public double calcularImpuesto(double UVT) {
        double liquidacion = 0;        
        
        if(UVT > 1090 && UVT <= 1700){
            liquidacion = (UVT - 1090)*0.19;
        }
        
        if(UVT > 1700 && UVT <= 4100){
            liquidacion = ((UVT-1700)*0.28)+116;
        }
        
        if(UVT > 4100 && UVT <= 8670){
            liquidacion = ((UVT-4100)*0.33)+788;
        }
        
        if(UVT > 8670 && UVT <= 18970){
            liquidacion = ((UVT-8670)*0.35)+2296;
        }
        
        if(UVT > 18970 && UVT <= 31000){
            liquidacion = ((UVT-18970)*0.37)+5901;
        }
        
        if(UVT > 31000){
            liquidacion = ((UVT-31000)*0.39)+10352;
        }              

        return liquidacion;
    }    

}
