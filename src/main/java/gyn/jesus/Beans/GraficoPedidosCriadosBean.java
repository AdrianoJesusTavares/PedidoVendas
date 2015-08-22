package gyn.jesus.Beans;

import gyn.jesus.anotations.UserLogado;
import gyn.jesus.model.Usuario;
import gyn.jesus.repository.Pedidos;
import gyn.jesus.seguranca.UsuarioSistema;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	@Inject
	private Pedidos pedidos;

	@Inject
	@UserLogado
	private UsuarioSistema usuarioLogado;

	private LineChartModel model;

	public void preRender() {

		this.model = new LineChartModel();
		this.model.setTitle("Grafico de Pedidos");
		this.model.setLegendPosition("e");
		this.model.setShowPointLabels(true);
		this.model.getAxes().put(AxisType.X, new CategoryAxis("Dias do Mes"));
		Axis yAxis = this.model.getAxis(AxisType.Y);
		yAxis.setLabel("Valores");
		yAxis.setMin(0);

		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
	}

	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = this.pedidos
				.valoresTotaisPorData(15, criadoPor);

		ChartSeries series = new ChartSeries();
		series.setLabel(rotulo);
		for (Date data : valoresPorData.keySet()) {

			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}
		
		this.model.addSeries(series);
	}

	public LineChartModel getLineModel1() {
		return this.model;
	}

}
