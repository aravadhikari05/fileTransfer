public interface ClientObserver {
    public void newHostOnline(String ip);
    public void fileSendRequestWasSent();
    public void fileSendReplyWasReceived(boolean fileAccepted);
    public void fileWasSent();
}
