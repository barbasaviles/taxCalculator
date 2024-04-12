package Controlador;

import Modelo.impuestoPropiedad;
import Modelo.impuestoRenta;
import Modelo.impuestoVehiculo;
import Modelo.liquidacion;
import Vista.taxInitial;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Juan
 */
public class CalculationController implements ActionListener {

    taxInitial view;
    ArrayList<liquidacion> listLiquidaciones;

    public CalculationController() {

        listLiquidaciones = new ArrayList<liquidacion>();
        view = new taxInitial();
        view.getAgregarPropiedad().addActionListener(this);
        view.getAgregarVehiculo().addActionListener(this);
        view.getAgregarRenta().addActionListener(this);
        view.getReiniciar().addActionListener(this);
        view.getLiquidar().addActionListener(this);
    }

    public void Run() {
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setTitle("Calculo Impuestos UNICOR");
        view.setVisible(true);
    }

    private void calcularImpuestoPropiedad() {
        double valor = 0;
        
        impuestoPropiedad impuesto = new impuestoPropiedad();
        
        double avaluo = Double.parseDouble(view.getValorAvaluoPropiedad().getText());
        double salario = Double.parseDouble(view.getSalarioMinimo().getText());

        Date date = (Date) view.getFecha().getValue();        

        valor = impuesto.calcularImpuesto(avaluo, salario, date);

        liquidacion objLiquidacion = new liquidacion();

        objLiquidacion.setTipo(view.getComboTipoPropiedad().getSelectedItem().toString());
        objLiquidacion.setDescripcion(view.getDireccionPropiedad().getText());
        objLiquidacion.setValor(valor);

        listLiquidaciones.add(objLiquidacion);
    }
    
    private void calcularImpuestoVehiculo(){
        double valor = 0;
        
        impuestoVehiculo impuesto = new impuestoVehiculo();
        
        double avaluo = Double.parseDouble(view.getValorAvaluoVehiculo().getText());       

        Date date = (Date) view.getFecha().getValue();        

        valor = impuesto.calcularImpuesto(avaluo, date);

        liquidacion objLiquidacion = new liquidacion();

        objLiquidacion.setTipo("VEHICULO");
        objLiquidacion.setDescripcion(view.getPlaca().getText());
        objLiquidacion.setValor(valor);

        listLiquidaciones.add(objLiquidacion);
    }
    
    private void calcularImpuestoRenta(){
        double valor = 0;
        
        impuestoRenta impuesto = new impuestoRenta();
        
        double UVT = Double.parseDouble(view.getValorUVT().getText());               

        valor = impuesto.calcularImpuesto(UVT);
        
        double calUVTtoMoney = valor * Double.parseDouble(view.getUVTAnio().getText());        
        
        liquidacion objLiquidacion = new liquidacion();

        objLiquidacion.setTipo("IMPUESTO RENTA");
        objLiquidacion.setDescripcion(view.getSueldoCiudadano().getText());
        objLiquidacion.setValor(calUVTtoMoney);

        listLiquidaciones.add(objLiquidacion);
    }

    private void liquidarImpuestos() {

        double totalLiquidacion = 0;

        for (int i = 0; i < listLiquidaciones.size(); i++) {
            totalLiquidacion += listLiquidaciones.get(i).getValor();
        }

        view.getTotalImpuesto().setText("TOTAL LIQUIDACION IMPUESTOS: " + String.format("%.2f", totalLiquidacion));
    }

    private void imprimirConsola() {
        view.getConsola().setText("");
        String infoList = "Sr(a) " + view.getPropiatario().getText() + "\n";
        for (int i = 0; i < listLiquidaciones.size(); i++) {
            infoList += listLiquidaciones.get(i).getTipo() + " "
                    + listLiquidaciones.get(i).getDescripcion() + " "
                    + String.format("%.2f", listLiquidaciones.get(i).getValor()) + "\n";
        }

        view.getConsola().setText(infoList);
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        if (e.getSource() == view.getAgregarPropiedad()) {
            calcularImpuestoPropiedad();
        }
        
        if (e.getSource() == view.getAgregarVehiculo()) {
            calcularImpuestoVehiculo();
        }
        
        if (e.getSource() == view.getAgregarRenta()) {
            calcularImpuestoRenta();
        }

        if (e.getSource() == view.getLiquidar()) {
            liquidarImpuestos();
        }
        
        if (e.getSource() == view.getReiniciar()) {
            listLiquidaciones.clear();
            view.getTotalImpuesto().setText("TOTAL LIQUIDACION IMPUESTOS: ");
        }

        imprimirConsola();
    }

}
