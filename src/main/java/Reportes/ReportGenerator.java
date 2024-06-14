/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reportes;
import Model.DAO.DAOTask;
import java.math.BigDecimal;
import java.util.ArrayList;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
public class ReportGenerator {
    public void generarReporteTasks(int personId, String busqueda) {
    try {
        // Crear el diseño del reporte
        JasperDesign jasperDesign = new JasperDesign();

        // Configurar las propiedades básicas del reporte
        jasperDesign.setName("Reporte de tareas");
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(600);

        // Crear una banda para la cabecera
        JRDesignBand columnHeaderBand = new JRDesignBand();
        columnHeaderBand.setHeight(20);

        // Títulos de las columnas
        JRDesignStaticText columnTitleTask = new JRDesignStaticText();
        columnTitleTask.setX(0);
        columnTitleTask.setY(0);
        columnTitleTask.setWidth(150);
        columnTitleTask.setHeight(20);
        columnTitleTask.setText("Tarea:");
        columnTitleTask.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        columnHeaderBand.addElement(columnTitleTask);

        JRDesignStaticText columnTitleDescription = new JRDesignStaticText();
        columnTitleDescription.setX(150);
        columnTitleDescription.setY(0);
        columnTitleDescription.setWidth(200);
        columnTitleDescription.setHeight(20);
        columnTitleDescription.setText("Descripción:");
        columnTitleDescription.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        columnHeaderBand.addElement(columnTitleDescription);

        JRDesignStaticText columnTitleDate = new JRDesignStaticText();
        columnTitleDate.setX(300);
        columnTitleDate.setY(0);
        columnTitleDate.setWidth(100);
        columnTitleDate.setHeight(20);
        columnTitleDate.setText("Fecha:");
        columnTitleDate.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        columnHeaderBand.addElement(columnTitleDate);

        JRDesignStaticText columnTitleState = new JRDesignStaticText();
        columnTitleState.setX(400);
        columnTitleState.setY(0);
        columnTitleState.setWidth(100);
        columnTitleState.setHeight(20);
        columnTitleState.setText("Estado:");
        columnTitleState.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        columnHeaderBand.addElement(columnTitleState);

        JRDesignStaticText columnTitlePerson = new JRDesignStaticText();
        columnTitlePerson.setX(500);
        columnTitlePerson.setY(0);
        columnTitlePerson.setWidth(100);
        columnTitlePerson.setHeight(20);
        columnTitlePerson.setText("Persona:");
        columnTitlePerson.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        columnHeaderBand.addElement(columnTitlePerson);

        // Añadir la banda de cabecera a la sección de column header del reporte
        jasperDesign.setColumnHeader(columnHeaderBand);

        // Crear campos de datos
        JRDesignField fieldTaskName = new JRDesignField();
        fieldTaskName.setName("task_name");
        fieldTaskName.setValueClass(String.class);
        jasperDesign.addField(fieldTaskName);

        JRDesignField fieldDescription = new JRDesignField();
        fieldDescription.setName("task_description");
        fieldDescription.setValueClass(String.class);
        jasperDesign.addField(fieldDescription);

        JRDesignField fieldDate = new JRDesignField();
        fieldDate.setName("task_date");
        fieldDate.setValueClass(String.class);
        jasperDesign.addField(fieldDate);

        JRDesignField fieldStateName = new JRDesignField();
        fieldStateName.setName("state_name");
        fieldStateName.setValueClass(String.class);
        jasperDesign.addField(fieldStateName);

        JRDesignField fieldPersonName = new JRDesignField();
        fieldPersonName.setName("person_name");
        fieldPersonName.setValueClass(String.class);
        jasperDesign.addField(fieldPersonName);

        // Crear una banda para el título
        JRDesignBand titleBand = new JRDesignBand();
        titleBand.setHeight(50);

        JRDesignStaticText titleText = new JRDesignStaticText();
        titleText.setText("<<<<Reporte de tareas: "+busqueda+">>>>");
        titleText.setX(0);
        titleText.setY(0);
        titleText.setWidth(570);
        titleText.setHeight(50);
        titleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        titleText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        titleText.setFontSize(Float.parseFloat("24"));
        titleBand.addElement(titleText);

        jasperDesign.setTitle(titleBand);

        // Crear una banda para el detalle
        JRDesignBand detailBand = new JRDesignBand();
        detailBand.setHeight(20);

        JRDesignTextField textFieldTaskName = new JRDesignTextField();
        textFieldTaskName.setX(0);
        textFieldTaskName.setY(0);
        textFieldTaskName.setWidth(100);
        textFieldTaskName.setHeight(20);
        textFieldTaskName.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        textFieldTaskName.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        textFieldTaskName.setExpression(new JRDesignExpression("$F{task_name}"));
        detailBand.addElement(textFieldTaskName);

        JRDesignTextField textFieldDescription = new JRDesignTextField();
        textFieldDescription.setX(150);
        textFieldDescription.setY(0);
        textFieldDescription.setWidth(200);
        textFieldDescription.setHeight(20);
        textFieldDescription.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        textFieldDescription.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        textFieldDescription.setExpression(new JRDesignExpression("$F{task_description}"));
        detailBand.addElement(textFieldDescription);

        JRDesignTextField textFieldDate = new JRDesignTextField();
        textFieldDate.setX(300);
        textFieldDate.setY(0);
        textFieldDate.setWidth(100);
        textFieldDate.setHeight(20);
        textFieldDate.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        textFieldDate.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        textFieldDate.setExpression(new JRDesignExpression("$F{task_date}"));
        detailBand.addElement(textFieldDate);

        JRDesignTextField textFieldStateName = new JRDesignTextField();
        textFieldStateName.setX(400);
        textFieldStateName.setY(0);
        textFieldStateName.setWidth(100);
        textFieldStateName.setHeight(20);
        textFieldStateName.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        textFieldStateName.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        textFieldStateName.setExpression(new JRDesignExpression("$F{state_name}"));
        detailBand.addElement(textFieldStateName);

        JRDesignTextField textFieldPersonName = new JRDesignTextField();
        textFieldPersonName.setX(500);
        textFieldPersonName.setY(0);
        textFieldPersonName.setWidth(100);
        textFieldPersonName.setHeight(20);
        textFieldPersonName.setHorizontalTextAlign(HorizontalTextAlignEnum.LEFT);
        textFieldPersonName.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        textFieldPersonName.setExpression(new JRDesignExpression("$F{person_name}"));
        detailBand.addElement(textFieldPersonName);

        // Añadir la banda de detalle a la sección de detalles del reporte
        ((JRDesignSection) jasperDesign.getDetailSection()).addBand(detailBand);

        // Compilar el diseño del reporte
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        // Obtener los datos de la base de datos
        DAOTask DAOtask = new DAOTask();
        ArrayList<Map<String, Object>> datos = DAOtask.seleccionarReport(personId, busqueda);
        // Llenar el reporte con los datos de la base de datos
        JRDataSource dataSource = new JRBeanCollectionDataSource(datos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        // Exportar el reporte a un archivo PDF
        JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/ReporteTasks.pdf");

        // Visualizar el reporte (opcional)
        JasperViewer.viewReport(jasperPrint, false);

        System.out.println("Reporte generado exitosamente!");
    } catch (JRException e) {
        e.printStackTrace();
    }
  }
}
