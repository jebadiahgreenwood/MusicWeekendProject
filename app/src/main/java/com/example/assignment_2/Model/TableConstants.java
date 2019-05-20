package com.example.assignment_2.Model;

import android.provider.BaseColumns;

public class TableConstants {
    class VolumeInfo implements BaseColumns{

    }

    class ClassicTable implements BaseColumns{
        public static final String TABLE_NAME = "musicdetails";
        public static final String ARTIST_NAME_COLUMN = "artistName";
        public static final String TRACK_NAME_COLUMN = "trackName";
        public static final String TRACK_PRICE_COLUMN = "trackPrice";
        public static final String SAMPLE_URI_COLUMN = "sampleUri";
        public static final String ARTWORK_URI_COLUMN = "artworkUri";
    }


}

