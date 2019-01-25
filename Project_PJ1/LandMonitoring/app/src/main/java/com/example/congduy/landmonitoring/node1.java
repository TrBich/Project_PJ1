package com.example.congduy.landmonitoring;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.example.congduy.landmonitoring.models.DataNode;
import com.example.congduy.landmonitoring.network.AppClient;
import com.example.congduy.landmonitoring.utils.DateUtil;
import com.paditech.core.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class node1 extends BaseActivity {
    ChartAdapter adapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    DateAdapter dateAdapter;
    private List<Date> dataSpin = new ArrayList<>();
    Spinner spinner;


    @Override
    protected int getLayoutResource() {
        return R.layout.line_chart_activity;
    }

    @Override
    public void initView() {
        super.initView();
        RecyclerView rcvList = findViewById(R.id.rcvList);
        spinner = findViewById(R.id.spinner);
        rcvList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChartAdapter(dummy());
        rcvList.setAdapter(adapter);
        dateAdapter = new DateAdapter(getContext(), 0, dataSpin);
        spinner.setAdapter(dateAdapter);
        Log.e("initView: ", DateUtil.convertTimeToHour(2, 30, 60) + " ");
        initData();
        dateAdapter.notifyDataSetChanged();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) view).setText(dateAdapter.getTextSelected(i));
                ((TextView) view).setTextColor(Color.BLACK);
                Date date = dateAdapter.getItem(i);
                adapter.setDateDisplay(date);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private List<ChartItem> dummy() {
        List<ChartItem> results = new ArrayList<>();
//        results.add(new ChartItem("Thoi gian", "Do am", getListDoam1()));
//        results.add(new ChartItem("Thoi gian", "PH", getListPH1()));
//        results.add(new ChartItem("Thoi gian", "Anh Sang", getListAnhSang1()));
//        results.add(new ChartItem("Thoi gian", "DA_Dat", getListDA_Dat1()));
//        results.add(new ChartItem("Thoi gian", "Pin", getListPin1()));
        return results;
    }


    private void initData() {
        dataSpin.clear();
        showProgressDialog(true);
        Disposable disposable = AppClient.newInstance().getService().getAllData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<DataNode>, List<ChartItem>>() {

                    @Override
                    public List<ChartItem> apply(List<DataNode> dataNodes) throws Exception {
                        List<ChartItem> results = new ArrayList<>();

                        List<ChartItem.Entry> entries = new ArrayList<>();
                        List<ChartItem.Entry> entries2 = new ArrayList<>();
                        List<ChartItem.Entry> entries3 = new ArrayList<>();
                        List<ChartItem.Entry> entries4 = new ArrayList<>();
                        List<ChartItem.Entry> entries5 = new ArrayList<>();
                        List<ChartItem.Entry> entries6 = new ArrayList<>();
                        List<ChartItem.Entry> entries7 = new ArrayList<>();
                        List<ChartItem.Entry> entries8 = new ArrayList<>();
                        if (dataNodes != null && !dataNodes.isEmpty()) {
                            for (DataNode dataNode : dataNodes) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                                Date date = sdf.parse(dataNode.getDate());
                                Date date2 = sdf2.parse(dataNode.getDate());
                                if (!dataSpin.contains(date2)) {
                                    dataSpin.add(date2);
                                }
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(date);
                                double time = DateUtil.convertTimeToHour(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
                                entries.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getNhietdo())));
                                entries2.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getDoam())));
                                entries3.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getPh())));
                                entries4.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getDA_dat())));
                                entries5.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getPIR())));
                                entries6.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getEC_meter())));
                                entries7.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getAnhsang())));
                                entries8.add(new ChartItem.Entry(date2, new ValueDataEntry(Math.round(time * 100.0) / 100.0, dataNode.getPin())));

                            }
                            results.add(new ChartItem(entries, "Thoi gian", "Nhiet do"));
                            results.add(new ChartItem(entries2, "Thoi gian", "Do am"));
                            results.add(new ChartItem(entries3, "Thoi gian", "PH"));
                            results.add(new ChartItem(entries4, "Thoi gian", "DA_dat"));
                            results.add(new ChartItem(entries5, "Thoi gian", "PIR"));
                            results.add(new ChartItem(entries6, "Thoi gian", "EC_meter"));
                            results.add(new ChartItem(entries6, "Thoi gian", "anh sang"));
                            results.add(new ChartItem(entries6, "Thoi gian", "Pin "));
                        }
                        Log.e("apply: ", dataSpin.size() + " ");
                        return results;
                    }
                }).subscribe(new Consumer<List<ChartItem>>() {

                    @Override
                    public void accept(List<ChartItem> items) throws Exception {
                        showProgressDialog(false);
                        adapter.replace(items);
                        dateAdapter.notifyDataSetChanged();
                        if (dataSpin != null && !dataSpin.isEmpty()) spinner.setSelection(0);
                        Log.e("accept: ", spinner.getSelectedItemPosition() + " ");
                        Date date = dateAdapter.getItem(spinner.getSelectedItemPosition());
                        adapter.setDateDisplay(date);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        showProgressDialog(false);
                        Log.e("accept Error: ", throwable.getMessage());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) compositeDisposable.dispose();
    }

    protected List<DataEntry> getListNhietDo1() {
        List<DataEntry> seriesData = new ArrayList<>();
        seriesData.add(new ValueDataEntry(1.2, 23));
        seriesData.add(new ValueDataEntry(2.3, 24));
        seriesData.add(new ValueDataEntry(3.4, 25));
        seriesData.add(new ValueDataEntry(4.5, 26));
        seriesData.add(new ValueDataEntry(5.6, 31));
        seriesData.add(new ValueDataEntry(6.7, 28));
        seriesData.add(new ValueDataEntry(7.8, 29));
        seriesData.add(new ValueDataEntry(8.9, 27));
        seriesData.add(new ValueDataEntry(9.5, 31));
        seriesData.add(new ValueDataEntry(10.5, 30));
        seriesData.add(new ValueDataEntry(12, 32));
        seriesData.add(new ValueDataEntry(13, 28));
        seriesData.add(new ValueDataEntry(14, 32));
        seriesData.add(new ValueDataEntry(15, 28));
        seriesData.add(new ValueDataEntry(16, 32));
        seriesData.add(new ValueDataEntry(17, 28));
        seriesData.add(new ValueDataEntry(18, 32));
        seriesData.add(new ValueDataEntry(19, 28));
        seriesData.add(new ValueDataEntry(19.2, 32));
        seriesData.add(new ValueDataEntry(20, 28));
        seriesData.add(new ValueDataEntry(21, 32));
        seriesData.add(new ValueDataEntry(22, 28));
        seriesData.add(new ValueDataEntry(23, 32));
        seriesData.add(new ValueDataEntry(23.2, 28));
        seriesData.add(new ValueDataEntry(23.3, 32));
        seriesData.add(new ValueDataEntry(23.4, 28));
        return seriesData;
    }
}
