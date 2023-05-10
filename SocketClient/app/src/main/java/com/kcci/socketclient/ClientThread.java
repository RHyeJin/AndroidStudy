package com.kcci.socketclient;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Handler;

public class ClientThread extends Thread{
    Socket socket = null;
    String serverIp;
    String serverPort;

    ClientThread(){}
    ClientThread(String serverIp, String serverPort){
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }
    @Override
    public void run() {
        try {
            socket = new Socket();
            println("[연결 요청]");
            Log.d("run()", "serverIp : "+this.serverIp+" clientId : "+this.serverPort);
            socket.connect(new InetSocketAddress(this.serverIp, Integer.parseInt(this.serverPort)));
            println("[연결 성공]");
            byte[] bytes = null;
            String message = null;

            while(true){
                InputStream is = socket.getInputStream();
                bytes = new byte[100];
                int readByteCount = is.read(bytes);
                if(readByteCount <= 0) {
                    break;
                }
                message = new String(bytes, 0, readByteCount, "UTF-8");
                println("[데이터 받기 성공]: " + message);
                sendMainActivity(message);
            }
        }catch(Exception e){println("서버가 중지되었습니다");}
        if (!socket.isClosed()) {
            try {
                socket.close();
                println("클라이언트가 종료되었습니다");
            } catch (Exception e) {
            }
        }
    }
    void stopClient(){
        if(socket != null && !socket.isClosed()) {
            println("Client Stop");
            try { socket.close();
            }catch (IOException e) {throw new RuntimeException(e);}
        }
    }
    synchronized void sendMessage(String string) { // final data
        println("string : "+string);
        Thread sendThread = new Thread() {
            @Override
            public void run() {
                try {
                    //if(socket == null) return;
                    byte[] bytes = string.getBytes("UTF-8");
                    OutputStream os = socket.getOutputStream();
                    os.write(bytes);
                    os.flush();
                    println("데이터 보내기 성공");
                } catch (Exception e) {
                    println("서버를 확인하세요");
                }
            }
        };
        sendThread.start();
    }
    synchronized void println(String string){
        Log.d("ClientThread", string);
    }
    synchronized void sendMainActivity(String string){
        Message message = MainActivity.mainHandler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString("msg", string);
        message.setData(bundle);
        MainActivity.mainHandler.sendMessage(message);
    }
}

