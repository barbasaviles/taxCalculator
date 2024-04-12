/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class impuestoPropiedad {

    float[] tipoPropiedad = {1};

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
        
        System.out.println();
        System.out.printf("Fecha Java: %s", fecha.getTime());        

        System.out.println();
        System.out.printf("Salario Minimo: %f", salarioMinimo);

        System.out.println();
        System.out.printf("Avaluo: %f", avaluo);

        System.out.println();
        System.out.printf("SMLV135: %f", SMLV135);

        System.out.println();
        System.out.printf("SMLV136: %f", SMLV136);

        System.out.println();
        System.out.printf("Porcentaje: %f", porcentaje);        
        
        System.out.println();
        System.out.printf("Descuento: %f", descuento);    

        System.out.println();
        System.out.printf("Liquidacion impuesto Propiedad: %f", liquidacion);

        return liquidacion;
    }

    private double calculoCantidadSMLV(double salarioMinimo, int cantidadSMLV) {
        return salarioMinimo * cantidadSMLV;
    }

}
