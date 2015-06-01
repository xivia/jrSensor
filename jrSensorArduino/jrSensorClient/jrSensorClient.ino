/*
  jrSensor arduino client
  
  created by	joerg herzig / roman born
  date		17.04.2015

 */
//Ethernet
#include <SPI.h>
#include <Ethernet.h>
//DHT Sensor
#include <dht.h>

#define DHT11_PIN 2
dht DHT;

// client
byte MAC[] = {0x90, 0xA2, 0xDA, 0x0D, 0x4E, 0x71 };
byte IP[] = { 192, 168, 79, 20 };

// webserver
byte SERVER[] = { 192, 168, 79, 11 }; 
int PORT = 8080;

EthernetClient client;
boolean clientConnected = false;
double temperatur;

boolean DEBUG = true;

void setup() {
  Serial.begin(9600);
  delay(5000);
}

void loop()
{
  while(!clientConnected)
  {
    connectServer();
  }
  if(clientConnected)
  {
    jrSensorServicePOST();
    checkStatus();
    delay(30000);
  }
  
  
}

void connectServer(){

  Ethernet.begin(MAC,IP);

  if (DEBUG){
    Serial.println("connecting...");
    Serial.print("Obtaining local IP: --> ");
    Serial.println(Ethernet.localIP());
  }
  
  delay(1000);
  if (client.connect(SERVER, PORT)) {
    if (DEBUG){
      Serial.println("connected");
    }
    clientConnected = true;
  } 
  else {
    Serial.println("connection failed");
  }
}

void jrSensorServicePOST()
{  
  String temperatur;
  getTemp(temperatur);

  String data = "{ \"station\" : \"Station2\", \"sensor\" : \"Sensor3\", \"value\" : \" "+ temperatur + "\", \"unit\":\"C\"}";
  
  if (DEBUG){
    Serial.print("data POST String --> ");
    Serial.println(data);
  }

  client.println("POST /jrSensorWeb/ws/sensor/ HTTP/1.1"); 
  client.println("Content-Type: application/json"); 
  client.print("Content-Length: "); 
  client.println(data.length()); 
  client.println(); 
  client.print(data);
  client.println();
  
  if (DEBUG){
    delay(1000);
    Serial.print("nach dem POST --> client.available() --> ");
    Serial.println(client.available());
  }  
}

void checkStatus()
{
  if (clientConnected)
  {    
    while (client.available())
    {
      char c = client.read();
      if (DEBUG) {
        Serial.print(c);
      }
    }

    client.stop();
    
    if (!client.connected()) 
    {
      if (DEBUG){
        Serial.println("disconnecting.");
        Serial.println();
      }
    clientConnected = false;
    }
  }
}

void getTemp(String &dest)
{
   // READ DATA
  int chk = DHT.read33(DHT11_PIN);
  switch (chk)
  {
    case DHTLIB_OK:  
		Serial.print("OK,\t"); 
		break;
    case DHTLIB_ERROR_CHECKSUM: 
		Serial.print("Checksum error,\t"); 
		break;
    case DHTLIB_ERROR_TIMEOUT: 
		Serial.print("Time out error,\t"); 
		break;
    case DHTLIB_ERROR_CONNECT:
        Serial.print("Connect error,\t");
        break;
    case DHTLIB_ERROR_ACK_L:
        Serial.print("Ack Low error,\t");
        break;
    case DHTLIB_ERROR_ACK_H:
        Serial.print("Ack High error,\t");
        break;
    default: 
		Serial.print("Unknown error,\t"); 
		break;
  }
  // DISPLAY DATA
  String data;
  //data = String(DHT.humidity, 1);
  //data = String(DHT.humidity, 1) + ",Temp: ";
  data = data + String(DHT.temperature, 1);
  dest = data;
}


