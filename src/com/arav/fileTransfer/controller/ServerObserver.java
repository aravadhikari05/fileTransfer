package com.arav.fileTransfer.controller;

public interface ServerObserver {
    public void hostWentOnline(String ip);
    public void fileSaveRequestWasReceived(String filename, String ip);
    public void fileWasSaved();
    public void hostWentOffline(String ip);
}
