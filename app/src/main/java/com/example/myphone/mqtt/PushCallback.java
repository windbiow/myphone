package com.example.myphone.mqtt;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.myphone.MainActivity;
import com.example.myphone.R;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PushCallback implements MqttCallback {

    public void connectionLost(Throwable throwable) {
        // TODO: 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }

    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        //TODO : 此处应该写机器获取信息后的处理
//        callBack.getMessage("接收消息内容 : " + new String(mqttMessage.getPayload()));
        Log.d("text","接收消息主题 : " + topic);
        Log.d("text","接收消息Qos : " + mqttMessage.getQos());
        Log.d("text","接收消息内容 : " + new String(mqttMessage.getPayload()));

        Message msg = new Message();
        msg.what = MainActivity.PAY_FLAG;
        MainActivity.handler.sendMessage(msg);
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }
}
