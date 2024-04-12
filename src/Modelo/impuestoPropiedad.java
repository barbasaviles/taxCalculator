
package Modelo;

import java.util.Date;

/**
 *
 * @author Juan
 */
public class impuestoPropiedad {    

    public double calcularImpuesto(double avaluo, double salarioMinimo, Date fecha) {
        double liquidacion = 0;
        double porcentaje = 0.04;
        double descuento = 0.15;

        double SMLV135 = calculoCantidadSMLV(salarioMinimo, 135);
        double SMLV136 = calculoCantidadSMLV(salarioMinimo, 136);        
        
        if (avaluo <= SMLV135) {
            porcentaje = 0.035;
        }

        if (avaluo >= SMLV136) {
            porcentaje = 0.05;
        }             
        
        liquidacion = avaluo * porcentaje;
        
        long fechaLimitDescuento = 1711843200000L;
        
        if (fecha.getTime() <= fechaLimitDescuento) {            
            liquidacion = liquidacion -(liquidacion * descuento);
        }        

        return liquidacion;
    }

    private double calculoCantidadSMLV(double salarioMinimo, int cantidadSMLV) {
        return salarioMinimo * cantidadSMLV;
    }

}
