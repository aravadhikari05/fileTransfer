public interface ServerObserver {
    public void newHostOnline(String ip);
    public void fileSaveRequestWasReceived(String filename, String ip);
    public void fileWasSaved();
}
