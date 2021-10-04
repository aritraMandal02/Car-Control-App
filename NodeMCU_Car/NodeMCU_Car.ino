#define ENA   14          // Enable/speed motors Right        GPIO14(D5)
#define ENB   12          // Enable/speed motors Left         GPIO12(D6)
#define IN_1  15          // L298N in1 motors Right           GPIO15(D8)
#define IN_2  13          // L298N in2 motors Right           GPIO13(D7)
#define IN_3  2           // L298N in3 motors Left            GPIO2(D4)
#define IN_4  0           // L298N in4 motors Left            GPIO0(D3)

#include <ESP8266WiFi.h>
#include <WiFiClient.h> 
#include <ESP8266WebServer.h>

String command;             //String to store app command state.
int speedCar = 800;         // 400 - 1023.
int speed_Coeff = 3;

ESP8266WebServer server(80);

int car_mode = 0;  

void setup() {
 
  pinMode(ENA, OUTPUT);
  pinMode(ENB, OUTPUT);  
  pinMode(IN_1, OUTPUT);
  pinMode(IN_2, OUTPUT);
  pinMode(IN_3, OUTPUT);
  pinMode(IN_4, OUTPUT); 
  
  Serial.begin(115200);
  
// Connecting WiFi

  WiFi.mode(WIFI_STA);
  WiFi.begin("Galaxy M3107C0", "abcd1234");

  while (WiFi.status() != WL_CONNECTED){
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("IP Address:");
  Serial.println(WiFi.localIP());
 
 // Starting WEB-server 
  server.on("/", HTTP_GET, handle_OnConnect);
  server.on("/forward", HTTP_GET, goAhead);
  server.on("/backward", HTTP_GET, goBack);
  server.on("/left", HTTP_GET, goLeft);
  server.on("/right", HTTP_GET, goRight);
  server.on("/goAheadRight", HTTP_GET, goAheadRight);
  server.on("/goAheadLeft", HTTP_GET, goAheadLeft);
  server.on("/goBackRight", HTTP_GET, goBackRight);
  server.on("/goBackLeft", HTTP_GET, goBackLeft);
  server.on("/stop", HTTP_GET, stopRobot);
  server.onNotFound(handle_NotFound);   

  server.begin();
}

void loop() { 
  server.handleClient();
  car_control();
}

void handle_OnConnect() {
  Serial.println("Hello");
  car_mode = 0;
  Serial.println("Client connected");
  server.send(200, "text/html", "BOT ONLINE");
}

void goAhead(){ 
  car_mode = 1;
  Serial.println("Going forward...");
  server.send(200, "text/html", "1");
}

void goBack(){ 
  car_mode = 2;
  Serial.println("Going backward...");
  server.send(200, "text/html", "2");
}

void goRight(){ 
  car_mode = 3;
  Serial.println("Turn Right...");
  server.send(200, "text/html", "3");
}

void goLeft(){
car_mode = 4;
  Serial.println("Turn left...");
  server.send(200, "text/html", "4");
  }

void goAheadRight(){    
  car_mode = 5;
  Serial.println("Ahead Right...");
  server.send(200, "text/html", "5");
   }

void goAheadLeft(){   
  car_mode = 6;
  Serial.println("Ahead left...");
  server.send(200, "text/html", "6");
  }

void goBackRight(){ 
  car_mode = 7;
  Serial.println("Back right...");
  server.send(200, "text/html", "7");
}

void goBackLeft(){ 
  car_mode = 8;
  Serial.println("Back left...");
  server.send(200, "text/html", "8");
}

void stopRobot(){  
  car_mode = 0;
  Serial.println("Stopped");
  server.send(200, "text/html", "0");
}

void handle_NotFound(){
  car_mode = 0;
  Serial.println("Page error");
  server.send(404, "text/plain", "0");
}

void car_control() {
  switch (car_mode) {
    case 0: // stop car
      digitalWrite(IN_1, LOW);
      digitalWrite(IN_2, LOW);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, LOW);
      digitalWrite(IN_4, LOW);
      analogWrite(ENB, speedCar);
      break;
    case 1: // go forward
      digitalWrite(IN_1, LOW);
      digitalWrite(IN_2, HIGH);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, LOW);
      digitalWrite(IN_4, HIGH);
      analogWrite(ENB, speedCar);
      break;
    case 2: // go backward
      digitalWrite(IN_1, HIGH);
      digitalWrite(IN_2, LOW);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, HIGH);
      digitalWrite(IN_4, LOW);
      analogWrite(ENB, speedCar);
      break;
      
    case 3: // turn right
      digitalWrite(IN_1, HIGH);
      digitalWrite(IN_2, LOW);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, LOW);
      digitalWrite(IN_4, HIGH);
      analogWrite(ENB, speedCar);
      break;
      
    case 4: // turn left
      digitalWrite(IN_1, LOW);
      digitalWrite(IN_2, HIGH);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, HIGH);
      digitalWrite(IN_4, LOW);
      analogWrite(ENB, speedCar);
      break;
      
    case 5: // go ahead right
      digitalWrite(IN_1, LOW);
      digitalWrite(IN_2, HIGH);
      analogWrite(ENA, speedCar/speed_Coeff);
 
      digitalWrite(IN_3, LOW);
      digitalWrite(IN_4, HIGH);
      analogWrite(ENB, speedCar);
      break;
      
    case 6: // go ahead left
      digitalWrite(IN_1, LOW);
      digitalWrite(IN_2, HIGH);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, LOW);
      digitalWrite(IN_4, HIGH);
      analogWrite(ENB, speedCar/speed_Coeff);
      break;

    case 7: // go back right
      digitalWrite(IN_1, HIGH);
      digitalWrite(IN_2, LOW);
      analogWrite(ENA, speedCar/speed_Coeff);

      digitalWrite(IN_3, HIGH);
      digitalWrite(IN_4, LOW);
      analogWrite(ENB, speedCar);
      break;

    case 8: // go back left
      digitalWrite(IN_1, HIGH);
      digitalWrite(IN_2, LOW);
      analogWrite(ENA, speedCar);

      digitalWrite(IN_3, HIGH);
      digitalWrite(IN_4, LOW);
      analogWrite(ENB, speedCar/speed_Coeff);
      break;

  }
}
