package com.arav.fileTransfer.controller;

import java.util.ArrayList;

public interface ClientObserver {
    public void hostListUpdated(ArrayList<String> ips);
    public void fileSendRequestSent(); //not really needed
    public void fileSendReplyReceived(boolean fileAccepted);
    public void fileSent();
}
