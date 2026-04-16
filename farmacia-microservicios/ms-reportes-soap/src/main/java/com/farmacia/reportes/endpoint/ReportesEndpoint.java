package com.farmacia.reportes.endpoint;

import com.farmacia.reportes.service.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Endpoint
public class ReportesEndpoint {

    private static final String NAMESPACE_URI = "http://farmacia.com/reportes";

    @Autowired
    private ReportesService reportesService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReporteRequest")
    @ResponsePayload
    public Element getReporte(@RequestPayload Element request) throws ParserConfigurationException {
        var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        var response = doc.createElementNS(NAMESPACE_URI, "getReporteResponse");
        var resumen = reportesService.generarResumen();

        var totalVentas = doc.createElementNS(NAMESPACE_URI, "totalVentas");
        totalVentas.setTextContent(String.valueOf(resumen.get("totalVentas")));
        response.appendChild(totalVentas);

        var ingresoTotal = doc.createElementNS(NAMESPACE_URI, "ingresoTotal");
        ingresoTotal.setTextContent(String.valueOf(resumen.get("ingresoTotal")));
        response.appendChild(ingresoTotal);

        var totalUnidades = doc.createElementNS(NAMESPACE_URI, "totalUnidades");
        totalUnidades.setTextContent(String.valueOf(resumen.get("totalUnidades")));
        response.appendChild(totalUnidades);

        doc.appendChild(response);
        return doc.getDocumentElement();
    }
}
