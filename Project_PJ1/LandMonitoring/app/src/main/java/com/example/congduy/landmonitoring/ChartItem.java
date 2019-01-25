package com.example.congduy.landmonitoring;

import com.anychart.chart.common.dataentry.DataEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChartItem {
    private String nameX;
    private String nameY;
    private List<DataEntry> dataEntries;

    public List<Entry> getEntries() {
        return entries;
    }

    private List<Entry> entries;


    public ChartItem(String nameX, String nameY, List<DataEntry> dataEntries) {
        this.nameX = nameX;
        this.nameY = nameY;
        this.dataEntries = dataEntries;
    }

    public ChartItem(List<Entry> entries, String nameX, String nameY) {
        this.nameX = nameX;
        this.nameY = nameY;
        this.entries = entries;
    }

    public String getNameX() {
        return nameX;
    }

    public void setNameX(String nameX) {
        this.nameX = nameX;
    }

    public String getNameY() {
        return nameY;
    }

    public void setNameY(String nameY) {
        this.nameY = nameY;
    }

    public List<DataEntry> getDataEntries() {
        return dataEntries;
    }

    public List<DataEntry> getEntryData() {
        List<DataEntry> results = new ArrayList<>();
        for (Entry entry : entries) {
            results.add(entry.getDataEntry());
        }
        return results;
    }

    public List<DataEntry> getEntryDataByDate(Date date) {
        List<DataEntry> results = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getDate().equals(date))
                results.add(entry.getDataEntry());
        }
        return results;
    }

    public void setDataEntries(List<DataEntry> dataEntries) {
        this.dataEntries = dataEntries;
    }

    public static class Entry {
        private Date date;

        public Date getDate() {
            return date;
        }

        public DataEntry getDataEntry() {
            return dataEntry;
        }

        private DataEntry dataEntry;

        public Entry(Date date, DataEntry dataEntry) {
            this.date = date;
            this.dataEntry = dataEntry;
        }
    }
}
