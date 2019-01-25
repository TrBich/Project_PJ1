package com.example.congduy.landmonitoring;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.enums.Anchor;
import com.anychart.enums.MarkerType;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ViewHolder> {
    List<ChartItem> item = new ArrayList<>();
    Date date = new Date();
    List<Set> sets = new ArrayList<>();
    Set set;

    public ChartAdapter(List<ChartItem> item) {
        this.item = item;
    }

    public void setDateDisplay(Date dateDisplay) {
        this.date = dateDisplay;
//        for (int i = 0; i < item.size(); i++) {
//            updateData(i);
//        }
          notifyDataSetChanged();
    }

    public void replace(List<ChartItem> item) {
        if (item == null || item.isEmpty()) return;
        this.item.clear();
        this.item.addAll(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chart, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        setData(viewHolder.chartView, viewHolder.progressBar, i);
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AnyChartView chartView;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chartView = itemView.findViewById(R.id.any_chart_view);
            progressBar = itemView.findViewById(R.id.progress_bar);


        }
    }

    public void updateData(int position) {
        if (position < 0 || position >= sets.size()) return;
        List<DataEntry> seriesData = item.get(position).getEntryDataByDate(date);
        sets.get(position).data(seriesData);
        Log.e("updateData: ", position + " ");
    }

    private void setData(AnyChartView anyChartView, ProgressBar progressBar, int position) {

        List<DataEntry> seriesData = item.get(position).getEntryDataByDate(date);
        anyChartView.setProgressBar(progressBar);
        Cartesian cartesian = AnyChart.cartesian();
        cartesian.animation(true);

        cartesian.padding(10d, 20d, 5d, 20d);

        cartesian.crosshair().enabled(true);
        cartesian.crosshair()
                .yLabel(true)
                // TODO ystroke
                .yStroke((Stroke) null, null, null, (String) null, (String) null);

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);

        cartesian.title("Ten Bieu do");

        cartesian.yAxis(0).title(item.get(position).getNameY());
        cartesian.xAxis(0).labels().padding(5d, 5d, 5d, 5d);
        cartesian.xAxis(0).title(item.get(position).getNameX());


        Set set = Set.instantiate();
        set.data(seriesData);
        if (position >= sets.size())
            sets.add(set);
        Log.e("setData: ", "data" + " " + position);
        Mapping series1Mapping = set.mapAs("{ x: 'x', value: 'value' }");
        Line series1 = cartesian.line(series1Mapping);
        series1.name(item.get(position).getNameY());
        series1.hovered().markers().enabled(true);
        series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4d);
        series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5d)
                .offsetY(5d);
        cartesian.legend().enabled(true);
        cartesian.legend().fontSize(13d);
        cartesian.legend().padding(0d, 0d, 10d, 0d);
        anyChartView.setChart(cartesian);
    }
}
