package com.future.service;

public interface FileServer {
    /**
     *
     * @param file
     * @param filePath
     * @param fileName
     */
    void uploadFile(byte[] file,String filePath,String fileName);
}
