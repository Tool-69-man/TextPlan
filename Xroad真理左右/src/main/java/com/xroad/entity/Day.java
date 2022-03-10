package com.xroad.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Day implements Serializable {
    private Integer did;
    private Integer tid;

//    Duration:用于时、分、秒、毫秒的时间差值，即基于两个时间的差值。
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class) //jackjson
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime  start;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime  end;

    private String text;
    private String feel;

    private  Thing thing;


    public Day() {
    }

    public Day(Integer did, Integer tid, LocalDateTime start, LocalDateTime end, String text, String feel, Thing thing) {
        this.did = did;
        this.tid = tid;
        this.start = start;
        this.end = end;
        this.text = text;
        this.feel = feel;
        this.thing = thing;
    }


    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    @Override
    public String toString() {
        return "Day{" +
                "did=" + did +
                ", tid=" + tid +
                ", start=" + start +
                ", end=" + end +
                ", text='" + text + '\'' +
                ", feel='" + feel + '\'' +
                ", thing=" + thing +
                '}';
    }
}
