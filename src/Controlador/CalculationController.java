package Controlador;

import Modelo.impuestoPropiedad;
import Modelo.liquidacion;
import Vista.taxInitial;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gabriel
 */
public class CalculationController implements ActionListener {

    taxInitial view;
    ArrayList<liquidacion> listLiquidaciones;

    public CalculationController() {

        listLiquidaciones = new ArrayList<liquidacion>();
        view = new taxInitial();
        view.getAgregarPropiedad().addActionListener(this);
        view.getLiquidar().addActionListener(this);
    }

    public void Run() {
        view.setResizable(false);
        view.setLocationRelativeTo(null);
        view.setTitle("Calculo Impuestos UNICOR");
        view.setVisible(true);
    }

    private void avaluoCatastral() {
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
        System.out.println();
        System.out.println(e);
        if (e.getSource() == view.getAgregarPropiedad()) {
            avaluoCatastral();
        }

        if (e.getSource() == view.getLiquidar()) {
            liquidarImpuestos();
        }

        imprimirConsola();
    }

}
