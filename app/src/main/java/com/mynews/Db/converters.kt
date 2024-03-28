package com.mynews.Db

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.mynews.models.Source

class converters {

     @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}