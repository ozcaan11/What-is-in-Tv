package com.ozcaan11.l50.tvdenevar.Classes;

/**
 * Author : l50 - Özcan YARIMDÜNYA (@ozcaan11)
 * Date   : 02.07.2016 - 20:04
 */

public class Channel {
    String name;
    String program;
    String iconUrl;
    String channelUrl;
    String programTime;

    public Channel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getProgramTime() {
        return programTime;
    }

    public void setProgramTime(String programTime) {
        this.programTime = programTime;
    }
}
