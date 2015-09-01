package org.balaji.samples.jsf.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import javax.annotation.PostConstruct;

//@SuppressWarnings("restriction")
@ManagedBean
@ViewScoped
public class MyViewScopedManagedBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineChartModel model;
	private String year;
	private Boolean isMale;
	private int boys;
	private int girls;

	//@PostConstruct
	public MyViewScopedManagedBean() {

		LineChartModel cartesianChartModel = new LineChartModel();
		LineChartSeries males = new LineChartSeries();
		males.setLabel("Males");
		males.setFill(true);
		// Map<Object, Number> maleData = new HashMap<Object, Number>();
		males.set("2010", 23);
		males.set("2011", 53);
		males.set("2012", 233);
		males.set("2013", 223);
		males.set("2014", 123);
		// males.setData(maleData);

		LineChartSeries females = new LineChartSeries("Females");
		females.setFill(true);
		// Map<Object, Number> femaleData = new HashMap<Object, Number>();
		females.set("2010", 28);
		females.set("2011", 123);
		females.set("2012", 214);
		females.set("2013", 206);
		females.set("2014", 111);

		cartesianChartModel.addSeries(males);
		cartesianChartModel.addSeries(females);

		cartesianChartModel.getAxis(AxisType.X).setLabel("Years");
		cartesianChartModel.getAxis(AxisType.Y).setTickInterval("100");
		
		cartesianChartModel.getAxis(AxisType.Y).setLabel("Strength");
		cartesianChartModel.getAxis(AxisType.Y).setMin(0);
		cartesianChartModel.getAxis(AxisType.Y).setMax(300);
		cartesianChartModel.setTitle("Staff Strength Chart");
		cartesianChartModel.setLegendPosition("ne");
		//cartesianChartModel.setStacked(true);
		cartesianChartModel.setShowPointLabels(true);
		model =  cartesianChartModel;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public LineChartModel getModel() {
		return model;
	}

	public void setModel(LineChartModel model) {
		this.model = (LineChartModel) model;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	public int getBoys() {
		return boys;
	}

	public void setBoys(int boys) {
		this.boys = boys;
	}

	public int getGirls() {
		return girls;
	}

	public void setGirls(int girls) {
		this.girls = girls;
	}



	public void save() {
		List<ChartSeries> series = model.getSeries();
		Map<Object, Number> origData = series.get(0).getData();
		origData.put(year, boys);
		origData = series.get(1).getData();
		origData.put(year, girls);
	}

}
