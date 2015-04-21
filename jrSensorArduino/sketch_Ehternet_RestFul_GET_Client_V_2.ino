/*
  Web client
  20150419 J.Herzig 

 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13


 */

#include <SPI.h>
#include <Ethernet.h>


// Bei mehreren Arduinos muss die MAC unterschiedlich sein
byte MAC[] = {0x90, 0xA2, 0xDA, 0x0D, 0x4E, 0x71 };
byte SERVER[] = { 192, 168, 79, 10 }; // WebServer Rasspary Pi
int PORT = 8080;  // Port zu WildFly
EthernetClient client;
boolean DEBUGGER = true;
boolean clientConnected = false;
double temperatur;

void setup() {
  Serial.begin(9600);
  delay(5000);
  //connectServer();
  //testServiceGET();
  //checkStatus();
  //connectServer();
  //testService();
  //checkStatus();
  connectServer();
  testServicePOST();
  checkStatus();
}

void loop()
{
  
}

void connectServer(){
  // Ethernet verbindung mit DHCP aufbauen
  if (Ethernet.begin(MAC) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
  }
  if (DEBUGGER){
    Serial.println("connecting...");
    Serial.print("Obtaining local IP: --> ");
    Serial.println(Ethernet.localIP());
  }
  
  // Verbindung mit dem Server herstellen
  delay(5000);
  if (client.connect(SERVER, PORT)) {
    if (DEBUGGER){
      Serial.println("connected");
    }
    clientConnected = true;
  } 
  else {
    Serial.println("connection failed");
  }
}

void testService()
{
   client.println("GET /jrLinkWeb/ws/test HTTP/1.0");
   client.println();
   if (DEBUGGER){
     // Warten da sonst der Client nicht verfügbar ist
     delay(1000);
     Serial.print("nach dem GET --> client.available() --> ");
     Serial.println(client.available());
   }  
}

void testServiceGET()
{
   client.println("GET /jrLinkWeb/ws/link/?u=admin&p=123 HTTP/1.0");
   client.println();
   
   if (DEBUGGER){
     // Warten da sonst der Client nicht verfügbar ist
     delay(1000);
     Serial.print("nach dem GET --> client.available() --> ");
     Serial.println(client.available());
   }  
}

void testServicePOST()
{
  Serial.print("vor dem POST --> client.available() --> ");
  Serial.println(client.available());
  
  String data = "{ \"name\" : \"testJHE\", \"url\" : \"URL vom Arduino\" }";

  client.println("POST /jrLinkWeb/ws/link/?u=admin&p=123 HTTP/1.1"); 
  client.println("Content-Type: application/json"); 
  client.print("Content-Length: "); 
  client.println(data.length()); 
  client.println(); 
  client.print(data);
  client.println();
  
  if (DEBUGGER){
    // Warten da sonst der Client nicht verfügbar ist
    delay(1000);
    Serial.print("nach dem POST --> client.available() --> ");
    Serial.println(client.available());
  }  

}

void checkStatus()
{
  if (DEBUGGER){
    Serial.print("Test CheckStatus --> ");
    Serial.println(clientConnected);
  }
  
  if (clientConnected){
    if (DEBUGGER){
      Serial.print("client.available() --> ");
      Serial.println(client.available());
    }
    
    while (client.available()) {
      char c = client.read();
      Serial.print(c);
    }

    if (!client.connected()) {
      Serial.println();
      Serial.println("disconnecting.");
      client.stop();
      clientConnected = false;
    }
  }
}

