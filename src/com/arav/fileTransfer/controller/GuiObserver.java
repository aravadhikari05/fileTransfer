package com.arav.fileTransfer.controller;

public interface GuiObserver {
    public void sendButtonPressed();
    public void acceptButtonPressed(String filePath);
    public void denyButtonPressed();
}
