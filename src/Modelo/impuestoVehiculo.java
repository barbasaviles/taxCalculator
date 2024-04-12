
package Modelo;

import java.util.Date;

/**
 *
 * @author Juan
 */
public class impuestoVehiculo {    

    public double calcularImpuesto(double avaluo, Date fecha) {
        double liquidacion = 0;
        
        double descuento = 0.15;
        
        
        if(avaluo <= 49470000){
            liquidacion = avaluo*0.015;
        }
        
        if(avaluo > 49470000 && avaluo<=111305000){
            liquidacion = avaluo*0.025;
        }
        
        if(avaluo > 11130500){
            liquidacion = avaluo*0.035;
        }        
        
        long fechaLimitDescuento = 1711843200000L;
        
        if (fecha.getTime() <= fechaLimitDescuento) {            
            liquidacion = liquidacion -(liquidacion * descuento);
        }       

        return liquidacion;
    }    

}
